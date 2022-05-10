package toyproject.annonymouschat.chat.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.model.User;
import toyproject.annonymouschat.chat.model.Chat;
import toyproject.annonymouschat.chat.service.ChatService;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.ModelView;
import toyproject.annonymouschat.config.controller.MyJson;
import toyproject.annonymouschat.config.controller.ReturnType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
//@WebServlet(name = "/chat/postbox/random", urlPatterns = "/v/chat/postbox/random")
public class ChatGetRandomServlet implements Controller {
    private ChatService chatService = new ChatService();
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.JSON)
    public ModelView process(Map<String, Object> requestParameters) {
        Long userId = ((User) requestParameters.get("user")).getId();
        Chat randomChat = chatService.getRandom(userId);

        ModelView modelView = new ModelView();
        modelView.getModel().put("response", randomChat);
        return modelView;
    }
}
