package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional // JPA를 사용하려면 필요하다
public class MemberService { //서비스는 비즈니스에 의존적으로 설계

    private final MemberRepository memberRepository; // = new MemoryMemberRepository(); new로 안하는 이유는 Service에서 Repository를 생성하고
    // MemberServiceTest에서도 생성하게 되면 같은 Repository가 아니므로 생성자(constructor)를 생성해서 외부에서 넣어줄 수 있게 바꾼다.

    public MemberService(MemberRepository memberRepository) {   // DI
        // constructor  단축키 Alt + Insert (외부에서 넣어줄 수 있게 바꿈)
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {  // 단축키 (Ctrl+Alt+M), 중복 회원 검증
        // 같은 이름이 있는 중복 회원 X
//        Optional<Member> result = memberRepository.findByName(member.getName()); // Ctrl + Alt + V (변수 추출)
//        result.ifPresent(m->{ // Optional을 사용해서 선언해서 ifPresent를 사용할 수 있다.
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
