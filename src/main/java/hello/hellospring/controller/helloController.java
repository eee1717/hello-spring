package hello.hellospring.controller;


import hello.hellospring.HelloSpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class helloController {

    @GetMapping("hello")
   public String hello(Model model){
      model.addAttribute("data","hello!!" );
      return "hello";
    }

    @GetMapping("hello-mvc") //웹페이지에서 입력하는 주소
    public String helloMvc(@RequestParam("name") String name, Model model) //전달할 값관련
    {
        model.addAttribute("name",name);
        return "hello-template"; // templates에서 전달 받을  클래스이름
    }

    @GetMapping("hello-string")   //위의 코드와 차이점 이코드들은 view가없다.
    @ResponseBody
    public String helloString(@RequestParam("name") String name)
    {
        return "hello" + name;
    }


    @GetMapping("hello-api")   //json 방식
    @ResponseBody     //ctrl+shift+ enter면 코드 자동완성
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return  hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }







}
