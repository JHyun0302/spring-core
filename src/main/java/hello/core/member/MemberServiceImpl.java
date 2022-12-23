package hello.core.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository; //= new MemoryMemberRepository(); // 인터페이스와 구현체 모두 의존 자르기
    //추상화에만 의존 = DIP 지킴
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * MemberRepository memberRepository는 인터페이스(역할)에 의존
     * new MemoryMemberRepository()는 구현체에 의존
     * 즉 MemberServiceImpl는 인터페이스와 구현체 모두 의존(DIP 위반)
     */

    @Override
    public void join(Member member) {
        memberRepository.save(member); // 다형성에 의해 new MemoryMemberRepository()의 save가 호출
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
