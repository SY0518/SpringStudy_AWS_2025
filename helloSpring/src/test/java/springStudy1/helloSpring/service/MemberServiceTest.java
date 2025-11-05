package springStudy1.helloSpring.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import springStudy1.helloSpring.domain.Member;
import springStudy1.helloSpring.repository.MemberRepository;
import springStudy1.helloSpring.repository.MemoryMemberRepository;
import springStudy1.helloSpring.service.MemberService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
// 바로 위의 것을 임포트하면 fail을 쓸 때는 에러가 날 확률이 생긴다. (둘 다 fail이 존재하는 패키지라 충돌 존재)

class MemberServiceTest {

    //MemberService memberService = new MemberService();
    //MemoryMemberRepository memoryRepository = new MemoryMemberRepository();
    //위에 주석처리한 것처럼 선언하면 store가 static이 아닐 경우, 위처럼 따로 객체를 선언하는 건 문제가 될 수 있음 -> 사실상 다른 레포지토리를 사용하고 있는 것과 같다.
    //원래 클래스도 수정해주고 아래와 같이 수정 (defendency injection: DI)

    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    //테스트는 한글로 적어도 됨 (직관적이라)
    @Test
    //저장이 잘 되는지 테스트
    void 회원가입() {
        //given: 뭔가 주어졌는데
        Member member = new Member();
        member.setName("hello");

        //when: 이걸 실행했을 때
        Long saveId = memberService.join(member);

        //then: 이런 결과가 나와야 해
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*
        try{
            memberService.join(member2);
            fail("잘못됨");
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}