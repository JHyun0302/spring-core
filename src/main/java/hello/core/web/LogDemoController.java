package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    /**
     * Web Scope: 이 빈은 실제 고객의 요청이 와야 생성 가능!
     * 해결법1. 즉 DL이 필요! -> ObjectProvider
     * 해결법2. 프록시 생성
     * 핵심: 진짜 객체 조회를 꼭 필요한 시점까지 지연처리 한다는 점!!
     */
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public  String logDemo(HttpServletRequest request) throws InterruptedException {  //HttpServletRequest: 자바에서 제공하는 표준 Servlet 규약(고객 요청 정보 받음)
        String requestURL = request.getRequestURL().toString();//고객이 어떤 url로 요청했는지 파악 가능
//        MyLogger myLogger = myLoggerProvider.getObject(); //ObjectProvider

        System.out.println("myLogger = " + myLogger.getClass()); // 가짜 프록시 주입
        myLogger.setRequestURL(requestURL); //기능 수행할 때 진짜를 찾아서 동작

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
