package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient  {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    /**
     * 1. implements InitializingBean, DisposableBean
     * 매우 오래된 방식: 수정 불가능한 외부 라이브러리에 적용 못함 & 스프링 전용 인터페이스에 의존하게 됨
     */
/*    @Override //의존관계 주입이 끝나면 호출하겠다!
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    @Override //close
    public void destroy() throws Exception {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }*/

    /**
     * 3. @PostConstruct & @PreDestroy 방법
     * 외부 라이브러리는 적용 못함
     */
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
