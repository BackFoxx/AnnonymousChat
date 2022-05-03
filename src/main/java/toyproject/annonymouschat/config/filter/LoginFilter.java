package toyproject.annonymouschat.config.filter;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.model.User;
import toyproject.annonymouschat.User.session.UserSession;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@Slf4j
public class LoginFilter implements Filter {
    private UserSession userSession = new UserSession();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("doFilter 작동");

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        User user = userSession.getSession(httpRequest);
        if (user != null) {
            httpRequest.setAttribute("user", user);
            chain.doFilter(httpRequest, response);
        }
        log.info("로그인 안되어있음");
        chain.doFilter(httpRequest, response);
    }
}
