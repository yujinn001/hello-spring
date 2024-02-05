package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements  MemberRepository{  //  Alt + Enter

    private static Map<Long,Member> store = new HashMap<>(); // 회원정보 저장소 (static이 없을 경우 다른 db가 된다)
    private  static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence); // id 셋팅
        store.put(member.getId(), member); // store에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null값이 나올 수 있는 거를 대비해 Optional로 감싸서 리턴한다.
    }

    @Override
    public Optional<Member> findByName(String name) {  // 자바 람다식
        return  store.values().stream() // stream 객체 생성
                .filter(member -> member.getName().equals(name)) // filter로 가공하기
                // 여기서는 member라는 객체에서 getName()이름을 가져온후,  name와 동일한 것 탐색
                .findAny(); //어떤 것이든 찾아서 반환. 없을 경우를 대비해서 Optional에 감싸서 사용
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // store.values() => store의 멤버들
    }

    public void clearStore(){
        store.clear();
    }
}
