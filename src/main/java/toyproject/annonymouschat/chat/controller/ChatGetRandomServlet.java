package toyproject.annonymouschat.chat.controller;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.model.User;
import toyproject.annonymouschat.chat.model.Chat;
import toyproject.annonymouschat.chat.service.ChatService;
import toyproject.annonymouschat.config.controller.ControllerResponseJson;
import toyproject.annonymouschat.config.controller.ReturnType;

import java.util.Map;

@Slf4j
public class ChatGetRandomServlet implements ControllerResponseJson {
    private ChatService chatService = new ChatService();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.JSON)
    public Chat process(Map<String, Object> requestParameters) {
        Long userId = ((User) requestParameters.get("user")).getId();
        Chat randomChat = chatService.getRandom(userId);
        return randomChat;
    }
}
