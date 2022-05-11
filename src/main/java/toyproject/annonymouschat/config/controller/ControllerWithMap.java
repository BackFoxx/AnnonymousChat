package toyproject.annonymouschat.config.controller;

import java.util.Map;

public interface ControllerWithMap {
    ModelView process(Map<String, Object> requestParameters);
}
