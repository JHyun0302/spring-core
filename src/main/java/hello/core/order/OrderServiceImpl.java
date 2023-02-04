package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // LomBok: final이 붙은 인스턴스 객체를 이용해 constructure 생성해줌 - command + F12
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository; // = new MemoryMemberRepository(); - 싱글톤 & 생성자 & 수정자 주입
    private final DiscountPolicy discountPolicy; //인터페이스만 의존 = DIP 지킴
//    @Autowired private DiscountPolicy discountPolicy; //필드 주입
//    @Autowired private MemberRepository memberRepository; //필드 주입
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //인터페이스 & 구현체 모두 의존

    /**
     * setter 주입
     */
/*    @Autowired //수정자 주입
    public void setDiscountPolicy(DiscountPolicy discountPolicy) { //수정자 주입(setter 주입)
        System.out.println("memberRepository = " + memberRepository);
        this.discountPolicy = discountPolicy;
    }

    @Autowired //(required = false) //-> 선택적 변경 가능 - 수정자 주입
    public void setMemberRepository(MemberRepository memberRepository) { //수정자 주입(setter 주입)
        System.out.println("discountPolicy = " + discountPolicy);
        this.memberRepository = memberRepository;
    }*/

    /**
     * 생성자 주입
     */
    @Autowired//생성자에 넣기! 인터페이스와 구현체 자동 의존관계 주입 - 생성자가 1개이면 생략가능
    /**
     * 하위 타입 2개인 경우 주입하는 방법
     * 1. @Autowired 필드 명 매칭 (생성자에서 이름 직접 지정)
     * 2. @Qualifier 사용해서 지정
     * 3. @Primary 사용
     */
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) { //DiscountPolicy rateDiscountPolicy
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        //System.out.println("1.OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
        //this.discountPolicy = rateDiscountPolicy; //하위 타입이 2개인 경우(rate,fix)
    }

    /**
     * 일반 메서드 주입
     */
    /*@Autowired //일반 메서드 주입
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice); //SRP 잘 지킴

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
