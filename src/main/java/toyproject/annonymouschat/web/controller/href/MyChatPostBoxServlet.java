package toyproject.annonymouschat.web.controller.href;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.model.User;
import toyproject.annonymouschat.chat.dto.MyChatPostBoxResponseDto;
import toyproject.annonymouschat.chat.service.ChatService;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.ModelView;
import toyproject.annonymouschat.config.controller.MyForwardView;
import toyproject.annonymouschat.config.controller.ReturnType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
//@WebServlet(name = "/chat/mypostbox", urlPatterns = "/v/chat/mypostbox")
public class MyChatPostBoxServlet implements Controller {

    private ChatService chatService = new ChatService();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.FORWARD)
    public ModelView process(Map<String, Object> requestParameters) {
        log.info("jsp 호출");
        Long userId = ((User) requestParameters.get("user")).getId();
        List<MyChatPostBoxResponseDto> findChats = chatService.findAllByUserId(userId);

        ModelView modelView = new ModelView("/chat/mypostbox.jsp");
        modelView.getModel().put("findChats", findChats);
        return modelView;
    }
}
