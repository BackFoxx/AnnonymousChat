package toyproject.annonymouschat.replychat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.MyJson;
import toyproject.annonymouschat.replychat.dto.ReplyChatSaveDeleteResponseDto;
import toyproject.annonymouschat.replychat.dto.ReplyDeleteDto;
import toyproject.annonymouschat.replychat.service.ReplyChatService;

import javax.naming.ldap.Control;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "replyDelete", urlPatterns = "/v/reply/delete")
public class ReplyDeleteServlet implements Controller {
    private ObjectMapper objectMapper = new ObjectMapper();
    private ReplyChatService replyChatService = new ReplyChatService();

    @Override
    public MyJson process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        ReplyDeleteDto dto = objectMapper.readValue(inputStream, ReplyDeleteDto.class);
        replyChatService.deleteReply(dto);

        ReplyChatSaveDeleteResponseDto responseDto = new ReplyChatSaveDeleteResponseDto(true, "삭제 완료되었습니다", "/v/chat/myreply");

        return new MyJson(responseDto);
    }
}
