package toyproject.annonymouschat.User.controller;

import toyproject.annonymouschat.User.session.UserSession;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.MyRedirectView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserLogoutServlet implements Controller {
    private UserSession userSession = new UserSession();

    @Override
    public MyRedirectView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userSession.expire(request, response);
        return new MyRedirectView("/");
    }
}
