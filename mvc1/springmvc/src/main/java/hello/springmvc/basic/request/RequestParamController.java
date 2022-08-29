package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);
        response.getWriter().write("ok");
    }

    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge){

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    // 변수명과 파라미터명이 같으면 생략 가능
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // 단순 타입(String, int, Integer)이고 파라미터명과 같으면, 어노테이션도 생략 가능
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // required=true일 경우, 파라미터를 전달받지 않으면 오류 출력
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) int age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(defaultValue = "20") int age){

        log.info("username={}, age={}", username, age);
        return "ok";
    }


    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap){

        Object username = paramMap.get("username");
        Object age = paramMap.get("age");
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("username={}", helloData);

        return "ok";
    }

    // @ModelAttribute 어노테이션 생략 가능
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("username={}", helloData);

        return "ok";
    }

}
