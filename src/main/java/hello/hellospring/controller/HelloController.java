package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","hello");  // 키, 값
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){ // 파라미터 정보 Ctrl + p (파라미터가 있어서) url http://localhost:8080/hello-mvc?name=spring!)
        model.addAttribute("name",name);// key , name
        return "hello-template";
    }


    @GetMapping("hello-string")
    @ResponseBody // body부에 return 값을 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // hello spring(name값) 데이터만 그대로 보여준다(html태그없이 => ResponseBody)
    }

    @GetMapping("hello-api")  // Json스타일로 객체 반환 (api)
    @ResponseBody // HTTP의 BODY에 문자 내용을 직접반환
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {   // 단축키 Alt + Insert
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



}
