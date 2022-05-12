package toyproject.annonymouschat.User.controller;

import toyproject.annonymouschat.User.session.UserSession;
import toyproject.annonymouschat.config.controller.ControllerWithMap;
import toyproject.annonymouschat.config.controller.ModelView;
import toyproject.annonymouschat.config.controller.ReturnType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class UserLogoutServlet implements ControllerWithMap {
    private UserSession userSession = new UserSession();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.REDIRECT)
    public ModelView process(Map<String, Object> requestParameters) {
        HttpServletRequest request = (HttpServletRequest) requestParameters.get("httpServletRequest");
        HttpServletResponse response = (HttpServletResponse) requestParameters.get("httpServletResponse");
        userSession.expire(request, response);

        return new ModelView("/");
    }
}
