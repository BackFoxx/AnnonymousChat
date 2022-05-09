package toyproject.annonymouschat.config.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* Myview 생성자: 이동한 페이지 경로를 저장
* render: 저장한 경로로 forward
* */
public class MyForwardView {

    private final String viewPath;

    public MyForwardView(String viewPath) {
        this.viewPath = viewPath;
    }


    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);
        requestDispatcher.forward(request, response);
    }
}
