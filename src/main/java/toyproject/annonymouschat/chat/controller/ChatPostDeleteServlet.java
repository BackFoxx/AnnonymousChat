package toyproject.annonymouschat.chat.controller;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.chat.dto.ChatDeleteDto;
import toyproject.annonymouschat.chat.service.ChatService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "chat/post/delete", urlPatterns = "/chat/post/delete")
public class ChatPostDeleteServlet extends HttpServlet {

    ChatService chatService = new ChatService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChatDeleteDto chatDeleteDto = new ChatDeleteDto();
        chatDeleteDto.setId(Long.valueOf(request.getParameter("id")));
        chatService.delete(chatDeleteDto);
    }
}
