package toyproject.annonymouschat.replychat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.MyJson;
import toyproject.annonymouschat.replychat.dto.RepliesByChatIdDto;
import toyproject.annonymouschat.replychat.dto.RepliesByChatIdResponseDto;
import toyproject.annonymouschat.replychat.service.ReplyChatService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "findAllByChatId", urlPatterns = "/v/reply/find")
public class RepliesByChatIdServlet implements Controller {
    private ReplyChatService replyChatService = new ReplyChatService();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public MyJson process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RepliesByChatIdDto dto = new RepliesByChatIdDto();
        dto.setChatId(Long.valueOf(request.getParameter("chatId")));

        List<RepliesByChatIdResponseDto> findChats = replyChatService.findAllByChatId(dto);
        return new MyJson(findChats);
    }
}
