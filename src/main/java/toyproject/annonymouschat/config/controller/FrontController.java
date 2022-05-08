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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        controller.process(request, response);
    }
}
