package toyproject.annonymouschat.web.controller.href;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.config.controller.ControllerWithMap;
import toyproject.annonymouschat.config.controller.ControllerWithTwoMap;
import toyproject.annonymouschat.config.controller.ModelView;
import toyproject.annonymouschat.config.controller.ReturnType;

import java.util.Map;

@Slf4j
public class LoginFormServlet implements ControllerWithTwoMap {
    @Override
    @ReturnType(type = ReturnType.ReturnTypes.FORWARD)
    public String process(Map<String, Object> requestParameters, Map<String, Object> model) {
        log.info("jsp 호출");
        return "login/login-form";
    }
}
