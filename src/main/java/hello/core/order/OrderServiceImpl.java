package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{
    private final MemberRepository memberRepository; // = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //인터페이스 & 구현체 모두 의존
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy; //인터페이스만 의존 = DIP 지킴

    @Autowired //생성자에 넣기! 인터페이스와 구현체 자동 의존관계 주입
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

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
