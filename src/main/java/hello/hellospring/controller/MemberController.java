package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller // 어노테이션 Controller를 사용하게 되면 스프링 통에 MemberController 객체를 생성해서 스프링에서 관리해준다.
public class MemberController {

    private  final MemberService memberService; //= new MemberService(); 공용으로 쓰기 위해 스프링 컨테이너에 등록하고 쓴다.


    @Autowired // 연결시켜줄 때 Autowired (Dependency Injection 의존성 주입)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String CreateForm(){
        return "/members/createMemberForm";
    }

    @PostMapping("/members/new") //등록
    public String create(MemberForm form){ // createMemberForm.html : 이름
        Member member = new Member();
        member.setName(form.getName()); //

        memberService.join(member); // 조인

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); //   <tr th:each="member : ${members}">
        return "members/memberList";
    }

}
