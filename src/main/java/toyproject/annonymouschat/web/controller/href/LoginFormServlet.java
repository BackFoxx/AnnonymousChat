package toyproject.annonymouschat.web.controller.href;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.MyForwardView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebServlet(name = "login-form", urlPatterns = "/v/login/login-form")
public class LoginFormServlet implements Controller {
    @Override
    public MyForwardView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("jsp 호출");
        return new MyForwardView("/login/login-form.jsp");
    }
}
