package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {
    MemberService memberService;

    @BeforeEach // test 실행 전 무조건 실행됨
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
//    MemberService memberService = new MemberServiceImpl(memberRepository);
    @Test //단위 테스트: 스프링 없이 순수한 자바로 test
    void join(){
        //given: 어떤 조건이 주어졌을 때
        Member member = new Member(1L, "memberA", Grade.VIP);

        //when: 동작을 수행하면
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        //then: 결과가 나온다.
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
