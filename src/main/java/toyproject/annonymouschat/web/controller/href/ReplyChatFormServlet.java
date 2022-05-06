package toyproject.annonymouschat.web.controller.href;

import toyproject.annonymouschat.chat.model.Chat;
import toyproject.annonymouschat.chat.service.ChatService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "replyForm", urlPatterns = "/replyForm")
public class ReplyChatFormServlet extends HttpServlet {
    ChatService chatService = new ChatService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        Chat chat = chatService.findbyChatId(id);
        request.setAttribute("chat", chat);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/chat/replychat/replychat-form.jsp");
        requestDispatcher.forward(request, response);
    }
}
