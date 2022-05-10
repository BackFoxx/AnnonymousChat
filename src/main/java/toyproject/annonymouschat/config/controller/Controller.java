package toyproject.annonymouschat.config.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface Controller {
    ModelView process(Map<String, Object> requestParameters);
}
