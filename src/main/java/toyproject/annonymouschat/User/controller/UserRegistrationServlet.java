package toyproject.annonymouschat.User.controller;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.dto.UserRegistrationDto;
import toyproject.annonymouschat.User.service.UserService;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.MyForwardView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebServlet(name = "registration", urlPatterns = "/login/registration")
public class UserRegistrationServlet implements Controller {
    private UserService userService = new UserService();

    @Override
    public MyForwardView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("userEmail");
        String password = request.getParameter("password");

        UserRegistrationDto registrationDto = new UserRegistrationDto(userEmail, password);
        String savedEmail = userService.registration(registrationDto);

        request.setAttribute("savedEmail", savedEmail);
        return new MyForwardView("/v/login/login-form");
    }
}
