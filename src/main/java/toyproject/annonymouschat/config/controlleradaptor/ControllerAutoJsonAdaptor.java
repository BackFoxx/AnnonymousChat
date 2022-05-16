package toyproject.annonymouschat.config.controlleradaptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import toyproject.annonymouschat.config.controller.controller.ControllerAutoJson;
import toyproject.annonymouschat.config.controller.ModelView;
import toyproject.annonymouschat.config.controller.RequestBody;
import toyproject.annonymouschat.config.controller.ReturnType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class ControllerAutoJsonAdaptor implements ControllerAdaptor {
    @Override
    public boolean supports(Object controller) {
        if (controller instanceof ControllerAutoJson) {
            Method process = getMethod(controller);
            return process.getAnnotation(ReturnType.class).type() == ReturnType.ReturnTypes.JSON;
        }
        return false;
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object controller) {
        ControllerAutoJson target = (ControllerAutoJson) controller;
        Method method = getMethod(controller);

        Parameter[] parameters = method.getParameters();
        Parameter parameter = Arrays.stream(parameters)
                .filter(param -> param.isAnnotationPresent(RequestBody.class)).findAny()
                .orElseThrow(() -> new RuntimeException("파라미터 없음"));
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Object requestBody = objectMapper.readValue(request.getInputStream(), parameter.getType());
            Object model = target.process(requestBody);

            ModelView modelView = new ModelView();
            modelView.getModel().put("response", model);

            return modelView;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Method getMethod(Object controller) {
        Method process = Arrays.stream(controller.getClass().getDeclaredMethods())
                .filter(method -> method.getName().equals("process")).findAny().get();
        return process;
    }
}
