package toyproject.annonymouschat.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import toyproject.annonymouschat.chat.dto.ChatDeleteDto;
import toyproject.annonymouschat.chat.dto.ChatPostSaveDeleteResponseDto;
import toyproject.annonymouschat.chat.service.ChatService;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@WebServlet(name = "chat/post/delete", urlPatterns = "/chat/post/delete")
public class ChatPostDeleteServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();
    ChatService chatService = new ChatService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String message = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        ChatDeleteDto dto = objectMapper.readValue(message, ChatDeleteDto.class);
        chatService.delete(dto);

        ChatPostSaveDeleteResponseDto responseDto = new ChatPostSaveDeleteResponseDto(true, "okok", "/chat/mypostbox");
        String result = objectMapper.writeValueAsString(responseDto);
        response.getWriter().write(result);

        log.info("result = {}", result);
    }
}
