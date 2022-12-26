package hello.core.scope;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.getCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.getCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac = 
                new AnnotationConfigApplicationContext(ClientBean1.class, ClientBean2.class, PrototypeBean.class);

        ClientBean1 clientBean1 = ac.getBean(ClientBean1.class);
        System.out.println("clientBean1 = " + clientBean1);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean1 clientBean2 = ac.getBean(ClientBean1.class);
        System.out.println("clientBean1 = " + clientBean2);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);

        //prototype 새로 생성 테스트
        ClientBean2 clientBean3 = ac.getBean(ClientBean2.class);
        System.out.println("clientBean2 = " + clientBean3);
        int count3 = clientBean1.logic();
        assertThat(count3).isEqualTo(1);
    }

    @Scope("singleton")
    @RequiredArgsConstructor
    static class ClientBean1 {
        //private final PrototypeBean prototypeBean; //생성시점에 주입  //prototypeBean@x01

        @Autowired //필드 주입
        private Provider<PrototypeBean> prototypeBeanProvider; //ObjectFactory가 부모 인터페이스
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider; //ObjectFactory가 부모 인터페이스
//        private ObjectFactory<PrototypeBean> prototypeBeanProvider;
        public int logic() {
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); //.getObject()호출하면 스프링 컨테이너에서 프로토 타입 찾아서 반환해줌
            PrototypeBean prototypeBean = prototypeBeanProvider.get(); //Provider 쓰는 경우
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }
    @Scope("singleton")
    static class ClientBean2 {
        private final PrototypeBean prototypeBean; //생성시점에 주입

        ClientBean2(PrototypeBean prototypeBean) { //prototypeBean@x02
            this.prototypeBean = prototypeBean;
        }

        public int logic() {
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;
        public void addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
