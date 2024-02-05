package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest { // 단축키 (Ctrl + Shift + T)

    MemberService memberService; // = new MemberService();
    MemoryMemberRepository memberRepository; // = new MemoryMemberRepository();

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);  //DI
    }


    @AfterEach
    public void afterEach(){ // 테스트는 순서와 상관없이, 의존관계없이 테스트를 해야한다 => 그래서 AfterEach를 사용해서 테스트한걸 clear 한다.
        memberRepository.clearStore();
    }

    @Test  // 테스트는 정상 작동도 중요하지만 예외처리도 중요
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");
        // when
        Long saveId = memberService.join(member);

        // then (검증부분)
        Member findMember = memberService.findOne(saveId).get(); // 변수 추출 (Ctrl + Alt + V) .get()사용할때는 Optional X
        assertThat(member.getName()).isEqualTo(findMember.getName());  // static import 단축키 (Alt + Enter)
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        /*try {
            memberService.join(member2);
            fail();
        } catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.121212");
        }*/
       // memberService.join(member2);

        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}