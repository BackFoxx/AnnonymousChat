package toyproject.annonymouschat.web.controller.href;

import toyproject.annonymouschat.User.model.User;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.ModelView;
import toyproject.annonymouschat.config.controller.MyForwardView;
import toyproject.annonymouschat.config.controller.ReturnType;
import toyproject.annonymouschat.replychat.dto.RepliesByUserIdDto;
import toyproject.annonymouschat.replychat.dto.RepliesByUserIdResponseDto;
import toyproject.annonymouschat.replychat.service.ReplyChatService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

//@WebServlet(name = "findAllByUserID", urlPatterns = "/v/chat/myreply")
public class MyRepliesServlet implements Controller {
    ReplyChatService replyChatService = new ReplyChatService();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.FORWARD)
    public ModelView process(Map<String, Object> requestParameters) {
        RepliesByUserIdDto dto = new RepliesByUserIdDto();
        dto.setUserId(((User) requestParameters.get("user")).getId());

        List<RepliesByUserIdResponseDto> replies = replyChatService.findAllByUserId(dto);

        ModelView modelView = new ModelView("/chat/replychat/myreplies.jsp");
        modelView.getModel().put("replies", replies);
        return modelView;
    }
}
