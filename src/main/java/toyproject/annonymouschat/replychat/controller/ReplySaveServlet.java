package toyproject.annonymouschat.replychat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.model.User;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.replychat.dto.ReplyChatSaveDto;
import toyproject.annonymouschat.replychat.dto.ReplyChatSaveDeleteResponseDto;
import toyproject.annonymouschat.replychat.service.ReplyChatService;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebServlet(name = "reply", urlPatterns = "/v/reply/save")
public class ReplySaveServlet implements Controller {
    private ReplyChatService replyChatService = new ReplyChatService();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        ReplyChatSaveDto dto = objectMapper.readValue(inputStream, ReplyChatSaveDto.class);
        dto.setUserId(((User) request.getAttribute("user")).getId());

        replyChatService.saveReply(dto);

        log.info("reply 저장 완료");
        ReplyChatSaveDeleteResponseDto responseDto = new ReplyChatSaveDeleteResponseDto(true, "저장 완료되었습니다", "/v/chat/postbox");
        String result = objectMapper.writeValueAsString(responseDto);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    }
}
