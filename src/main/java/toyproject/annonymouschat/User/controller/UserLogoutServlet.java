package toyproject.annonymouschat.User.controller;

import toyproject.annonymouschat.User.session.UserSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "logout", urlPatterns = "/logout")
public class UserLogoutServlet extends HttpServlet {
    private UserSession userSession = new UserSession();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        userSession.expire(request, response);
        response.sendRedirect("/");
    }
}
