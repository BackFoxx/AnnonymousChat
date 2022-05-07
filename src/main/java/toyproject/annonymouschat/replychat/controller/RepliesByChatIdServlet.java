package toyproject.annonymouschat.replychat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.replychat.dto.RepliesByChatIdDto;
import toyproject.annonymouschat.replychat.service.ReplyChatService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "findAllByChatId", urlPatterns = "/v/reply/find")
public class RepliesByChatIdServlet implements Controller {
    private ReplyChatService replyChatService = new ReplyChatService();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RepliesByChatIdDto dto = new RepliesByChatIdDto();
        dto.setChatId(Long.valueOf(request.getParameter("chatId")));

        String result = objectMapper.writeValueAsString(replyChatService.findAllByChatId(dto));
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    }
}
