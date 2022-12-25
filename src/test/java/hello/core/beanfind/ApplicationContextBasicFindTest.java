package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); //memberService가 MemberServiceImpl타입인가?(AppConfig 참고)
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회") // 같은 타입이 여러 개일 경우 곤란
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); //memberService가 MemberServiceImpl타입으로 받을 수 있나
    }

    @Test
    @DisplayName("구체 타입으로 조회") //구현에 의존하므로 좋은 코드 아님
    void findBeanByName2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        //인스턴스 타입을 보고 결정하기 때문에 구체 타입으로 작성해도 됨
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class); //memberService가 MemberServiceImpl타입으로 받을 수 있나
    }

    @Test
    @DisplayName("빈 이름으로 조회x")
    void findBeanByNameX(){
//        ac.getBean(("xxxx", MemberService.class))
//        MemberService bean = ac.getBean(("xxxx"), MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                () ->ac.getBean(("xxxx"), MemberService.class)); //예외가 터지면 테스트 성공
    }
}
