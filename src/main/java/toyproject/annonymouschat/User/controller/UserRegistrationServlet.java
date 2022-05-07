package toyproject.annonymouschat.User.controller;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.dto.UserRegistrationDto;
import toyproject.annonymouschat.User.service.UserService;
import toyproject.annonymouschat.config.controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebServlet(name = "registration", urlPatterns = "/login/registration")
public class UserRegistrationServlet implements Controller {
    private UserService userService = new UserService();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("userEmail");
        String password = request.getParameter("password");

        UserRegistrationDto registrationDto = new UserRegistrationDto(userEmail, password);
        String savedEmail = userService.registration(registrationDto);

        request.setAttribute("savedEmail", savedEmail);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login/login-form");
        requestDispatcher.forward(request, response);
    }
}
