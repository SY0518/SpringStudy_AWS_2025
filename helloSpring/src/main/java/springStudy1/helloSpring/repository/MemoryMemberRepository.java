package springStudy1.helloSpring.repository;

import springStudy1.helloSpring.domain.Member;

import java.util.*;
//util과 awt를 같이 임포트하면 List가 util이 아닌 awt를 우선으로 참조한다. 그러면 파라미터를 받지 않는 것으로 표기된다.

//option + enter -> 구현 선택
public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //Null 값이 반환되어도 감쌀 수 있도록 Nullable 설정
    }

    //lambda로 작성
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
        //리포지토리를 싹 비워줌
    }
}
