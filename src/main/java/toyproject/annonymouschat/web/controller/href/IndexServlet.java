package toyproject.annonymouschat.web.controller.href;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.config.controller.Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@WebServlet(name = "index", urlPatterns = "/v/")
public class IndexServlet implements Controller {
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("jsp 호출");
        String viewPath = "/index.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }
}
