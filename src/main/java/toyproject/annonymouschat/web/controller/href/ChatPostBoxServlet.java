package toyproject.annonymouschat.web.controller.href;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.ModelView;
import toyproject.annonymouschat.config.controller.MyForwardView;
import toyproject.annonymouschat.config.controller.ReturnType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
//@WebServlet(name = "chat/postbox", urlPatterns = "/v/chat/postbox")
public class ChatPostBoxServlet implements Controller {
    @Override
    @ReturnType(type = ReturnType.ReturnTypes.FORWARD)
    public ModelView process(Map<String, Object> requestParameters) {
        log.info("jsp 호출");
        return new ModelView("/chat/postbox.jsp");
    }
}
