package toyproject.annonymouschat.replychat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.replychat.dto.ReplyChatSaveDto;
import toyproject.annonymouschat.replychat.dto.ReplyChatSaveResponseDto;
import toyproject.annonymouschat.replychat.service.ReplyChatService;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "reply", urlPatterns = "/reply")
public class ReplyChatSaveServlet extends HttpServlet {
    private ReplyChatService replyChatService = new ReplyChatService();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        ReplyChatSaveDto dto = objectMapper.readValue(inputStream, ReplyChatSaveDto.class);
        replyChatService.saveReply(dto);

        log.info("reply 저장 완료");
        ReplyChatSaveResponseDto responseDto = new ReplyChatSaveResponseDto(true, "저장 완료되었습니다", "/chat/postbox");
        String result = objectMapper.writeValueAsString(responseDto);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(result);
    }
}
