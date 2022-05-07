package toyproject.annonymouschat.web.controller.href;

import toyproject.annonymouschat.chat.model.Chat;
import toyproject.annonymouschat.chat.service.ChatService;
import toyproject.annonymouschat.config.controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "replyForm", urlPatterns = "/v/replyForm")
public class ReplyChatFormServlet implements Controller {
    ChatService chatService = new ChatService();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        Chat chat = chatService.findbyChatId(id);
        request.setAttribute("chat", chat);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/chat/replychat/replychat-form.jsp");
        requestDispatcher.forward(request, response);
    }
}
