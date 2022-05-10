package toyproject.annonymouschat.web.controller.href;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.ModelView;
import toyproject.annonymouschat.config.controller.ReturnType;

import java.util.Map;

@Slf4j
//@WebServlet(name = "index", urlPatterns = "/v/")
public class IndexServlet implements Controller {
    @Override
    @ReturnType(type = ReturnType.ReturnTypes.FORWARD)
    public ModelView process(Map<String, Object> requestParameters) {
        log.info("jsp 호출");
        return new ModelView("index");
    }
}
