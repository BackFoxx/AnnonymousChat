package toyproject.annonymouschat.config.controller;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.controller.UserLoginServlet;
import toyproject.annonymouschat.User.controller.UserLogoutServlet;
import toyproject.annonymouschat.User.controller.UserRegistrationServlet;
import toyproject.annonymouschat.chat.controller.ChatGetRandomServlet;
import toyproject.annonymouschat.chat.controller.ChatPostDeleteServlet;
import toyproject.annonymouschat.chat.controller.ChatPostSaveServlet;
import toyproject.annonymouschat.replychat.controller.RepliesByChatIdServlet;
import toyproject.annonymouschat.replychat.controller.ReplyDeleteServlet;
import toyproject.annonymouschat.replychat.controller.ReplySaveServlet;
import toyproject.annonymouschat.web.controller.href.*;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@WebServlet(urlPatterns = {"/v/*"})
public class FrontController extends HttpServlet {
    Map<String, Controller> controllerMap = new HashMap<>();

    public FrontController() {
        //User
        controllerMap.put("/v/login", new UserLoginServlet());
        controllerMap.put("/v/logout", new UserLogoutServlet());
        controllerMap.put("/v/login/registration", new UserRegistrationServlet());

        //Chat
        controllerMap.put("/v/chat/postbox/random", new ChatGetRandomServlet());
        controllerMap.put("/v/chat/post/delete", new ChatPostDeleteServlet());
        controllerMap.put("/v/chat/post/save", new ChatPostSaveServlet());

        //ReplyChat
        controllerMap.put("/v/reply/find", new RepliesByChatIdServlet());
        controllerMap.put("/v/reply/delete", new ReplyDeleteServlet());
        controllerMap.put("/v/reply/save", new ReplySaveServlet());

        //Web
        controllerMap.put("/v/", new IndexServlet());
        controllerMap.put("/v/chat/postbox", new ChatPostBoxServlet());
        controllerMap.put("/v/chat/post", new ChatPostServlet());
        controllerMap.put("/v/login/login-form", new LoginFormServlet());
        controllerMap.put("/v/chat/mypostbox", new MyChatPostBoxServlet());
        controllerMap.put("/v/chat/myreply", new MyRepliesServlet());
        controllerMap.put("/v/login/registration-form", new RegistrationFormServlet());
        controllerMap.put("/v/replyForm", new ReplyChatFormServlet());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        log.info("FrontController 호출, URI = {}", requestURI);

        Controller controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, Object> requestParameters = setRequestParametersToMap(request, response);
        ModelView modelView = controller.process(requestParameters);

        Object result = viewResolver(controller, modelView);

        if (result instanceof MyForwardView) ((MyForwardView) result).render(modelView.getModel(), request, response);
        else if (result instanceof MyJson) ((MyJson) result).render(response);
        else if (result instanceof MyRedirectView) ((MyRedirectView) result).render(modelView.getModel(), request, response);

        log.info("--------- 호출 완료! ------------");
    }

    private Map<String, Object> setRequestParametersToMap(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> requestParameters = new HashMap<>();
        /*
        * request의 attributes를 Map 객체에 담는다.
        * */
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            requestParameters.put(attributeName, request.getAttribute(attributeName));
        }

        /*
        * request의 파라미터를 Map 객체에 담는다,
        * */
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            requestParameters.put(parameterName, request.getParameter(parameterName));
        }

        /*
        * request Body의 내용을 Map 객체의 requestBody 값으로 담는다.
        * */
        try {
            ServletInputStream inputStream = request.getInputStream();
            requestParameters.put("requestBody", inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
        * request, response 객체도 넣어 선택적으로 쓸 수 있게 해주었다.
        * */
        requestParameters.put("httpServletRequest", request);
        requestParameters.put("httpServletResponse", response);

        return requestParameters;
    }

    private Object viewResolver(Controller controller, ModelView modelView) {
        /*
        * 컨트롤러의 @ReturnType 어노테이션을 분석하여
        * 컨트롤러가 redirect 하는지, forward 하는지, Json을 응답하는지 판별하여
        * 그에 맞는 다음 단계를 지정한다.
        * */

        try {
            ReturnType.ReturnTypes returnType = controller.getClass().getMethod("process", Map.class)
                    .getAnnotation(ReturnType.class).type();

            if (returnType == ReturnType.ReturnTypes.FORWARD) {
                return new MyForwardView(modelView.getViewName());
            }
            else if (returnType == ReturnType.ReturnTypes.REDIRECT)
                return new MyRedirectView(modelView.getViewName());
            else if (returnType == ReturnType.ReturnTypes.JSON) {
                return new MyJson(modelView.getModel().get("response"));
            } else {
                throw new RuntimeException("어노테이션 설정 안됨");
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
