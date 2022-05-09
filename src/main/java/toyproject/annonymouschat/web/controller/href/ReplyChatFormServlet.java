package toyproject.annonymouschat.web.controller.href;

import toyproject.annonymouschat.chat.model.Chat;
import toyproject.annonymouschat.chat.service.ChatService;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.MyForwardView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "replyForm", urlPatterns = "/v/replyForm")
public class ReplyChatFormServlet implements Controller {
    ChatService chatService = new ChatService();

    @Override
    public MyForwardView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.valueOf(request.getParameter("id"));
        Chat chat = chatService.findbyChatId(id);
        request.setAttribute("chat", chat);

        return new MyForwardView("/chat/replychat/replychat-form.jsp");
    }
}
