package toyproject.annonymouschat.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import toyproject.annonymouschat.chat.dto.ChatDeleteDto;
import toyproject.annonymouschat.chat.dto.ChatPostSaveDeleteResponseDto;
import toyproject.annonymouschat.chat.service.ChatService;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.ModelView;
import toyproject.annonymouschat.config.controller.MyJson;
import toyproject.annonymouschat.config.controller.ReturnType;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
//@WebServlet(name = "chat/post/delete", urlPatterns = "/v/chat/post/delete")
public class ChatPostDeleteServlet implements Controller {

    ObjectMapper objectMapper = new ObjectMapper();
    ChatService chatService = new ChatService();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.JSON)
    public ModelView process(Map<String, Object> requestParameters) {
        ServletInputStream requestBody = (ServletInputStream) requestParameters.get("requestBody");
        try {
            ChatDeleteDto dto = objectMapper.readValue(requestBody, ChatDeleteDto.class);
            chatService.delete(dto);

            ChatPostSaveDeleteResponseDto responseDto = new ChatPostSaveDeleteResponseDto(true, "okok", "/v/chat/mypostbox");

            ModelView modelView = new ModelView();
            modelView.getModel().put("response", responseDto);
            return modelView;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
