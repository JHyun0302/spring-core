package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //Spring기반으로 변경
public class AppConfig { //Config: 설정 정보
    @Bean //Spring Container에 등록
    public MemberService memberService(){ //인터페이스: 역할
        return new MemberServiceImpl(memberRepository()); //Extract Method: Command + Option + M
    }

    /**
     * 리팩토링: new를 하는 곳 따로 만들어서 중복 없앰
     */
    @Bean //(name = "changeName") //스프링 빈에 등록되는 이름 바꾸기
    public static MemoryMemberRepository memberRepository() { //구현체: 구현
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){ //인터페이스: 역할
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }
    @Bean
    public DiscountPolicy discountPolicy(){ //구현체: 구현
        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
    }
}
