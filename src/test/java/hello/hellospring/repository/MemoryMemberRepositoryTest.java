package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test; // 테스트 코드 사용(Junit)

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest { // 단위 테스트

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){ // 테스트는 순서와 상관없이, 의존관계없이 테스트를 해야한다 => 그래서 AfterEach를 사용해서 테스트한걸 clear 한다.
        repository.clearStore();
    }

    @Test
    public void save() { // 만들어놓은 save가 잘 동작하는 지 확인한다.
        Member member = new Member();
        member.setName("spring"); // save에서 Id는 자동 셋팅해서 name값만 지정

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        //System.out.println("result= "+(result = member));
        //Assertions.assertEquals(member,result);
        assertThat(member).isEqualTo(result); // static import  (import static org.assertj.core.api.Assertions.*;)
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member(); // 이름 변경 Shift + F6
        member2.setName("Spring2");
        repository.save(member2);

        //Optional<Member> result = repository.findByName("Spring1");
        Member result = repository.findByName("Spring1").get();
        
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
