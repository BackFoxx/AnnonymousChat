package toyproject.annonymouschat.replychat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.ModelView;
import toyproject.annonymouschat.config.controller.MyJson;
import toyproject.annonymouschat.config.controller.ReturnType;
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
import java.util.Map;

//@WebServlet(name = "findAllByChatId", urlPatterns = "/v/reply/find")
public class RepliesByChatIdServlet implements Controller {
    private ReplyChatService replyChatService = new ReplyChatService();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.JSON)
    public ModelView process(Map<String, Object> requestParameters) {
        RepliesByChatIdDto dto = new RepliesByChatIdDto();
        dto.setChatId(Long.valueOf((String) requestParameters.get("chatId")));

        List<RepliesByChatIdResponseDto> findChats = replyChatService.findAllByChatId(dto);
        ModelView modelView = new ModelView();
        modelView.getModel().put("response", findChats);

        return modelView;
    }
}
