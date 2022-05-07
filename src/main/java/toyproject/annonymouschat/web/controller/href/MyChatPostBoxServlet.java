package toyproject.annonymouschat.web.controller.href;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.model.User;
import toyproject.annonymouschat.chat.dto.MyChatPostBoxResponseDto;
import toyproject.annonymouschat.chat.service.ChatService;
import toyproject.annonymouschat.config.controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
//@WebServlet(name = "/chat/mypostbox", urlPatterns = "/v/chat/mypostbox")
public class MyChatPostBoxServlet implements Controller {

    private ChatService chatService = new ChatService();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("jsp 호출");
        Long userId = ((User) request.getAttribute("user")).getId();
        List<MyChatPostBoxResponseDto> findChats = chatService.findAllByUserId(userId);
        request.setAttribute("findChats", findChats);

        String viewPath = "/chat/mypostbox.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
