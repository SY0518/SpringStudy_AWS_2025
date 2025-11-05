package springStudy1.helloSpring.service;

import springStudy1.helloSpring.domain.Member;
import springStudy1.helloSpring.repository.MemberRepository;
import springStudy1.helloSpring.repository.MemoryMemberRepository;

import java.util.*;
import java.util.Optional;

public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //테스트에서 문제가 생길 수 있어 아래와 같이 수정
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        //외부에서 넣어지도록 수정 (직접 넣지 않고)
    }

    /*
         회원가입
     */
    public Long join(Member member){
        //중복 회원 검증
        //cmd+option+v
        /*  1번째 방법: 지저분
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.")
        });
         */
        //ctrl + t: 리팩토리와 관련된 여러 로직들이 나옴
        //cmd + option + m: 메소드 블럭을 해당 이름으로 대체하고 함수로 위치를 옮길 수 있음
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /*
    * 전체 회원 조회
    **/
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
