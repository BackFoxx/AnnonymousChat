package toyproject.annonymouschat.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.chat.model.Chat;
import toyproject.annonymouschat.chat.service.ChatService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "/chat/postbox/random", urlPatterns = "/chat/postbox/random")
public class ChatGetRandomServlet extends HttpServlet {
    private ChatService chatService = new ChatService();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Chat randomChat = chatService.getRandom();
        String json = objectMapper.writeValueAsString(randomChat);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        log.info("랜덤 편지 가져옴");
    }
}
