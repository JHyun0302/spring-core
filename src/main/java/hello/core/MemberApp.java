package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        /*AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();*/
        /**
         * memberServiceImpl(new MemoryMemberRepository)
         * appConfig.memberService();에서 구현체 생성해서 memberService에 주입
          */
//      MemberService memberService = new MemberServiceImpl(memberRepository); //인터페이스 & 구현체 모두 의존

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //ApplicationContext = Spring Container(객체 관리)
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        //.getBean("메서드 이름", (반환) 타입)

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
