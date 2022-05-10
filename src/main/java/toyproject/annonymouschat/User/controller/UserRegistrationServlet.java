package toyproject.annonymouschat.User.controller;

import lombok.extern.slf4j.Slf4j;
import toyproject.annonymouschat.User.dto.UserRegistrationDto;
import toyproject.annonymouschat.User.service.UserService;
import toyproject.annonymouschat.config.controller.Controller;
import toyproject.annonymouschat.config.controller.ModelView;
import toyproject.annonymouschat.config.controller.ReturnType;

import java.util.Map;

@Slf4j
//@WebServlet(name = "registration", urlPatterns = "/login/registration")
public class UserRegistrationServlet implements Controller {
    private UserService userService = new UserService();

    @Override
    @ReturnType(type = ReturnType.ReturnTypes.FORWARD)
    public ModelView process(Map<String, Object> requestParameters) {
        String userEmail = (String) requestParameters.get("userEmail");
        String password = (String) requestParameters.get("password");

        UserRegistrationDto registrationDto = new UserRegistrationDto(userEmail, password);
        String savedEmail = userService.registration(registrationDto);

        ModelView modelView = new ModelView("/v/login/login-form");
        modelView.getModel().put("savedEmail", savedEmail);
        return modelView;
    }
}
