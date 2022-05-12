package toyproject.annonymouschat.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.chat.dto.ChatDeleteDto;
import toyproject.annonymouschat.chat.dto.ChatPostSaveDeleteResponseDto;
import toyproject.annonymouschat.chat.service.ChatService;
import toyproject.annonymouschat.config.controller.ControllerResponseJson;
import toyproject.annonymouschat.config.controller.ReturnType;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.util.Map;

@Slf4j
public class ChatPostDeleteServlet implements ControllerResponseJson {

    ObjectMapper objectMapper = new ObjectMapper();
    ChatService chatService = new ChatService();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.JSON)
    public ChatPostSaveDeleteResponseDto process(Map<String, Object> requestParameters) {
        ServletInputStream requestBody = (ServletInputStream) requestParameters.get("requestBody");
        try {
            ChatDeleteDto dto = objectMapper.readValue(requestBody, ChatDeleteDto.class);
            chatService.delete(dto);

            return new ChatPostSaveDeleteResponseDto(true, "okok", "/v/chat/mypostbox");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
