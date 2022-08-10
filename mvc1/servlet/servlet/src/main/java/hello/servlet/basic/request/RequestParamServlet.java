package hello.servlet.basic.request;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 파라미터 전송 기능
//http://localhost:8080/request-param?userName=hello&age=20
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("전체 파라미터 조회");

        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println("paramName = " + request.getParameter(paramName)));

        System.out.println();

        System.out.println("단일 파라미터 조회");
        String userName = request.getParameter("userName");
        String age = request.getParameter("age");
        System.out.println("userName = " + userName);
        System.out.println("age = " + age);

        System.out.println("이름이 같은 복수 파라미터 조회");
        String[] userNames = request.getParameterValues("userName");
        for (String name : userNames) {
            System.out.println("userName = " + name);
        }
    }
}
