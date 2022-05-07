package toyproject.annonymouschat.config.controller;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.controller.UserLoginServlet;
import toyproject.annonymouschat.User.controller.UserLogoutServlet;
import toyproject.annonymouschat.User.controller.UserRegistrationServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@WebServlet(urlPatterns = {"/v/*", "/logout", "/login/registration"})
public class FrontController extends HttpServlet {
    Map<String, Controller> controllerMap = new HashMap<>();

    public FrontController() {
        controllerMap.put("/v/login", new UserLoginServlet());
        controllerMap.put("/logout", new UserLogoutServlet());
        controllerMap.put("/login/registration", new UserRegistrationServlet());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        log.info("FrontController 호출, URI = {}", requestURI);

        Controller controller = controllerMap.get(requestURI);
        controller.process(request, response);
    }
}
