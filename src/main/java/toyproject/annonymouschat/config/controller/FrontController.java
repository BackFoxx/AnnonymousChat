package toyproject.annonymouschat.config.controller;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.controller.UserLoginServlet;
import toyproject.annonymouschat.User.controller.UserLogoutServlet;
import toyproject.annonymouschat.User.controller.UserRegistrationServlet;
import toyproject.annonymouschat.chat.controller.ChatGetRandomServlet;
import toyproject.annonymouschat.chat.controller.ChatPostDeleteServlet;
import toyproject.annonymouschat.chat.controller.ChatPostSaveServlet;
import toyproject.annonymouschat.config.controlleradaptor.ControllerAdaptor;
import toyproject.annonymouschat.config.controlleradaptor.ControllerResponseJsonAdaptor;
import toyproject.annonymouschat.config.controlleradaptor.ControllerWithMapAdaptor;
import toyproject.annonymouschat.config.controlleradaptor.ControllerWithTwoMapAdaptor;
import toyproject.annonymouschat.replychat.controller.RepliesByChatIdServlet;
import toyproject.annonymouschat.replychat.controller.ReplyDeleteServlet;
import toyproject.annonymouschat.replychat.controller.ReplySaveServlet;
import toyproject.annonymouschat.web.controller.href.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
@WebServlet(urlPatterns = {"/v/*"})
public class FrontController extends HttpServlet {
    Map<String, Object> controllerMap = new HashMap<>();
    List<ControllerAdaptor> controllerAdaptorList = new ArrayList<>();

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

        //Adaptor
        controllerAdaptorList.add(new ControllerWithMapAdaptor());
        controllerAdaptorList.add(new ControllerWithTwoMapAdaptor());
        controllerAdaptorList.add(new ControllerResponseJsonAdaptor());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        log.info("FrontController 호출, URI = {}", requestURI);

        Object controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        ControllerAdaptor adaptor = assignControllerAdaptor(controller);

        log.info("ControllerAdaptor = {}", adaptor.getClass());
        log.info("Controller = {}", controller.getClass());

        ModelView modelView = adaptor.handle(request, response, controller);

        Object result = ReturnTypeResolver(controller, modelView);

        if (result instanceof MyForwardView) ((MyForwardView) result).render(modelView.getModel(), request, response);
        else if (result instanceof MyJson) ((MyJson) result).render(response);
        else if (result instanceof MyRedirectView) ((MyRedirectView) result).render(modelView.getModel(), request, response);

        log.info("--------- 호출 완료! ------------");
    }

    private ControllerAdaptor assignControllerAdaptor(Object controller) {
        for (ControllerAdaptor adaptor : controllerAdaptorList) {
            if (adaptor.supports(controller)) return adaptor;
        }
        throw new RuntimeException("잘못된 형식의 컨트롤러");
    }

    private Object ReturnTypeResolver(Object controller, ModelView modelView) {
        /*
        * 컨트롤러의 @ReturnType 어노테이션을 분석하여
        * 컨트롤러가 redirect 하는지, forward 하는지, Json을 응답하는지 판별하여
        * 그에 맞는 다음 단계를 지정한다.
        * */

        try {
            Method process = Arrays.stream(controller.getClass().getMethods())
                    .filter(method -> method.getName().equals("process")).findAny()
                    .orElseThrow(() -> new NoSuchMethodException("잘못된 컨트롤러 형식"));

            ReturnType.ReturnTypes returnType = process.getAnnotation(ReturnType.class).type();

            if (returnType == ReturnType.ReturnTypes.FORWARD) {
                return new MyForwardView(viewResolver(modelView.getViewName()));
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

    private String viewResolver(String viewName) {
        return  "/" + viewName + ".jsp";
    }
}
