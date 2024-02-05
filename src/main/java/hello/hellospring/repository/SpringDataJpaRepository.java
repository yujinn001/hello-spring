package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaRepository extends JpaRepository<Member,Long>, MemberRepository { // 인터페이스가 인터페이스를 받을 떄는 extends (인터페이스는 다중상속 가능)
    // JpaRepository를 받고 있으면 SpringDataJpaRepository가 구현체를 자동으로 만들어줘서 자동으로 등록해준다.

    // JPOL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
