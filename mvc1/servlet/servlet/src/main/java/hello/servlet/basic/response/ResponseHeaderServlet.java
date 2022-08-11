package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // status-line
        // 상태코드 200으로 적는 것보다, 아래와 같이 'HttpServletResponse.SC_OK'로 적는 것 권장
        response.setStatus(HttpServletResponse.SC_OK);

        // response-header
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        // 캐시 무효화
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        // 사용자 정의 헤더 생성
        response.setHeader("my-header", "hello");

        // Header 편의 메소드
        content(response);
        cookie(response);
        redirect(response);

        // Message Body
        response.getWriter().write("ok");
    }

    // content 편의 메소드
    private void content(HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        // Content-Length: 2
        // response.setContentLength(2); //(생략시 자동 생성)
    }

    // 쿠키 편의 메소드
    private void cookie(HttpServletResponse response) {
        // Set-Cookie: myCookie=good; Max-Age=600;
        // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        // 600초간 유효한 쿠키 객체 생성해 추가
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);
        response.addCookie(cookie);
    }

    // redirect 편의 메소드
    private void redirect(HttpServletResponse response) throws IOException {
        // Status Code 302
        // Location: /basic/hello-form.html
        // response.setStatus(HttpServletResponse.SC_FOUND); //302
        // response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
