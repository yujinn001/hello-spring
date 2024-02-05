package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // 처음 동작하게 되면 contorller파일에 있는 걸 먼저 찾고 그다음..index.html
    public String home(){
        return "home"; // home.html을 찾는다.
    }
}
