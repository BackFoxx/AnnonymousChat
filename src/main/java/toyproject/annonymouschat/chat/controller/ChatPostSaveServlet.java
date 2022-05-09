package toyproject.annonymouschat.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import toyproject.annonymouschat.User.model.User;
import toyproject.annonymouschat.chat.dto.ChatPostSaveDeleteResponseDto;
import toyproject.annonymouschat.chat.dto.ChatSaveDto;
import toyproject.annonymouschat.chat.service.ChatService;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.MyJson;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
//@WebServlet(name = "chat/post/save", urlPatterns = "/v/chat/post/save")
public class ChatPostSaveServlet implements Controller {
    private ChatService chatService = new ChatService();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public MyJson process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        ChatSaveDto chat = objectMapper.readValue(messageBody, ChatSaveDto.class);
        chat.setUserId(((User) request.getAttribute("user")).getId());

        chatService.save(chat);

        ChatPostSaveDeleteResponseDto responseDto = new ChatPostSaveDeleteResponseDto(true, "저장 완료되었습니다.", "/v/chat/mypostbox");
        return new MyJson(responseDto);
    }
}
