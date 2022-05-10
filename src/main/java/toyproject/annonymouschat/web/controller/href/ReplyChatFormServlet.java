package toyproject.annonymouschat.web.controller.href;

import toyproject.annonymouschat.chat.model.Chat;
import toyproject.annonymouschat.chat.service.ChatService;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.ModelView;
import toyproject.annonymouschat.config.controller.MyForwardView;
import toyproject.annonymouschat.config.controller.ReturnType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//@WebServlet(name = "replyForm", urlPatterns = "/v/replyForm")
public class ReplyChatFormServlet implements Controller {
    ChatService chatService = new ChatService();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.FORWARD)
    public ModelView process(Map<String, Object> requestParameters) {
        Long id = Long.valueOf((String) requestParameters.get("id"));
        Chat chat = chatService.findbyChatId(id);

        ModelView modelView = new ModelView("/chat/replychat/replychat-form.jsp");
        modelView.getModel().put("chat", chat);
        return modelView;
    }
}
