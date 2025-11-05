package springStudy1.helloSpring.repository;

import springStudy1.helloSpring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //요즘 Null 대신 Optional이라는 걸로 감싸서 반환하는 걸 선호
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
