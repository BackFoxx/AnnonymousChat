package toyproject.annonymouschat.config.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* MyJson 생성자: 반환할 오브젝트를 Json으로 바꾸어 저장
* render: response body에 넣어 반환
* */
public class MyJson {
    private ObjectMapper objectMapper = new ObjectMapper();
    private final String jsonResult;

    public MyJson(Object object) throws JsonProcessingException {
        this.jsonResult = objectMapper.writeValueAsString(object);
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResult);
    }
}
