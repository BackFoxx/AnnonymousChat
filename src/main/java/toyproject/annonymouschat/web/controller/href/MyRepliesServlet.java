package toyproject.annonymouschat.web.controller.href;

import toyproject.annonymouschat.User.model.User;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.MyForwardView;
import toyproject.annonymouschat.replychat.dto.RepliesByUserIdDto;
import toyproject.annonymouschat.replychat.dto.RepliesByUserIdResponseDto;
import toyproject.annonymouschat.replychat.service.ReplyChatService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//@WebServlet(name = "findAllByUserID", urlPatterns = "/v/chat/myreply")
public class MyRepliesServlet implements Controller {
    ReplyChatService replyChatService = new ReplyChatService();

    @Override
    public MyForwardView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RepliesByUserIdDto dto = new RepliesByUserIdDto();
        dto.setUserId(((User) request.getAttribute("user")).getId());

        List<RepliesByUserIdResponseDto> replies = replyChatService.findAllByUserId(dto);
        request.setAttribute("replies", replies);

        return new MyForwardView("/chat/replychat/myreplies.jsp");
    }
}
