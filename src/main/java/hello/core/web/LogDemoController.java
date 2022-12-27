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
    private final LogDemoService logDemoService;

//    private final ObjectProvider<MyLogger> myLoggerProvider; //Provider: myLogger를 찾을 수 있는 DL이 주입 됨
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public  String logDemo(HttpServletRequest request) throws InterruptedException {  //HttpServletRequest: 자바에서 제공하는 표준 Servlet 규약(고객 요청 정보 받음)
        String requestURL = request.getRequestURL().toString();//고객이 어떤 url로 요청했는지 파악 가능
//        MyLogger myLogger = myLoggerProvider.getObject();

        System.out.println("myLogger = " + myLogger.getClass()); // 가짜 프록시 주입
        myLogger.setRequestURL(requestURL); //기능 수행할 때 진짜를 찾아서 동작

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
