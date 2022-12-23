package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
        /**
         * appConfig에서 구현체 생성해서 MemberServiceImpl에 주입해서 인터페이스에 연결
         */
//        MemberService memberService = new MemberServiceImpl(memberRepository);
//        OrderService orderService =  new OrderServiceImpl(new MemoryMemberRepository(), discountPolicy);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println("order = " + order); //Order 객체의 toString()으로 출력
        System.out.println("order.calculatePrice() = " + order.calculatePrice());
    }
}
