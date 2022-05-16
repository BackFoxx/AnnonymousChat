package toyproject.annonymouschat.chat.controller;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.chat.dto.ChatDeleteDto;
import toyproject.annonymouschat.chat.dto.ChatPostSaveDeleteResponseDto;
import toyproject.annonymouschat.chat.service.ChatService;
import toyproject.annonymouschat.config.controller.controller.ControllerAutoJson;
import toyproject.annonymouschat.config.controller.RequestBody;
import toyproject.annonymouschat.config.controller.ReturnType;

@Slf4j
public class ChatPostDeleteServlet implements ControllerAutoJson<ChatDeleteDto> {

    ChatService chatService = new ChatService();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.JSON)
    public Object process(@RequestBody ChatDeleteDto requestBody) {
        chatService.delete(requestBody);
        return new ChatPostSaveDeleteResponseDto(true, "okok", "/v/chat/mypostbox");
    }
}
