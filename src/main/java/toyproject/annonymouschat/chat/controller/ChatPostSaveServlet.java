package toyproject.annonymouschat.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.model.User;
import toyproject.annonymouschat.chat.dto.ChatPostSaveDeleteResponseDto;
import toyproject.annonymouschat.chat.dto.ChatSaveDto;
import toyproject.annonymouschat.chat.service.ChatService;
import toyproject.annonymouschat.config.controller.ControllerResponseJson;
import toyproject.annonymouschat.config.controller.ReturnType;

import javax.servlet.ServletInputStream;
import java.io.IOException;
import java.util.Map;

@Slf4j
//@WebServlet(name = "chat/post/save", urlPatterns = "/v/chat/post/save")
public class ChatPostSaveServlet implements ControllerResponseJson {
    private ChatService chatService = new ChatService();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.JSON)
    public ChatPostSaveDeleteResponseDto process(Map<String, Object> requestParameters) {
        ServletInputStream requestBody = (ServletInputStream) requestParameters.get("requestBody");
        try {
            ChatSaveDto dto = objectMapper.readValue(requestBody, ChatSaveDto.class);
            dto.setUserId(((User) requestParameters.get("user")).getId());

            chatService.save(dto);

            return new ChatPostSaveDeleteResponseDto(true, "저장 완료되었습니다.", "/v/chat/mypostbox");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
