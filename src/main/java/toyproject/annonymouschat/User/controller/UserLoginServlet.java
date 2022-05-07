package toyproject.annonymouschat.User.controller;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.dto.UserLoginDto;
import toyproject.annonymouschat.User.model.User;
import toyproject.annonymouschat.User.service.UserService;
import toyproject.annonymouschat.User.session.UserSession;
import toyproject.annonymouschat.config.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UserLoginServlet implements Controller {
    UserService userService = new UserService();
    UserSession userSession = new UserSession();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("userEmail");
        String password = request.getParameter("password");
        UserLoginDto userLoginDto = new UserLoginDto(userEmail, password);

        User loginUser = userService.login(userLoginDto);
        if (loginUser != null) {
            userSession.createSession(loginUser, response);
            response.sendRedirect("/");
        } else {
            log.info("로그인 실패");
            response.sendRedirect("/v/login/login-form");
        }
    }
}
