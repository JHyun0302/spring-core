package hello.core.order;

import hello.core.AppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    @BeforeEach // test 실행 전 무조건 실행됨
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }
//    MemberService memberService = new MemberServiceImpl(memberRepository);
//    OrderService orderService = new OrderServiceImpl(new MemoryMemberRepository(), discountPolicy);

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    /*@Test
    void fieldInjectionTest(){ // 결국 필드 주입할려면 setter 만들어야함!!
        OrderServiceImpl orderService = new OrderServiceImpl(); // 스프링이 아닌 순수하게 돌리게 됨
        OrderService.setMemberRepository(new MemoryMemberRepository());
        OrderService.setDiscountPolicy(new FixDiscountPolicy());
        orderService.createOrder(1L, "itemA", 10000);
    }*/

}
