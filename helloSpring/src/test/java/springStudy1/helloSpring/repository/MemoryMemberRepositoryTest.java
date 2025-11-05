package springStudy1.helloSpring.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import springStudy1.helloSpring.domain.Member;

import java.awt.*;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach  //테스트가 하나 끝날 때마다 실행해라.
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById((member.getId())).get();
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);   //최근 더 많이 사용하는 방식(위에 거랑 참조하는 게 다르니 주의, 위에 임포트된 것들 확인하기!)
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();     //get을 붙여주면 optional로 안 싸매고도 가능

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}

//모든 테스트는 순서 보장이 되지 않는다. 작성한 순서대로 시작되지 않기 때문에 테스트를 할 때마다 메모리를 clear시켜줘야 함
//그래서 afterEach() 사용