package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 어댑터 인터페이스
public interface MyHandlerAdapter {

    // 핸들러(컨트롤러)를 지원할 수 있는지 여부
    boolean supports(Object handler);

    // 핸들러의 프로세스 호출
    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
