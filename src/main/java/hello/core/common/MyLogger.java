package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

/**
 * 웹 스코프
 */
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) //프록시를 이용해 myLogger 가짜 생성
public class MyLogger {
    private String uuid;
    private String requestURL;
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    @PostConstruct //  Bean 초기화
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy // Bean 소멸
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
