package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {  // 부모에서 선언 자식에서 재정의(오버라이딩)
    Member save(Member member); // 회원 저장
    Optional<Member> findById(Long id); // Optional => null 처리하는 방법?? null그냥 반환하는 거 대신 Optional로 감싸서 반환한다.
    Optional<Member> findByName(String name);
    List<Member> findAll(); // 저장된 모든 회원 리스트 반환
}
