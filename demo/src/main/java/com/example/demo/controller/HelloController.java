package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        // return값은 자동으로 templates/hello.html을 return하게 된다. thymeleaf template기능인듯??
        return "hello";
    }
    
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value="name",required = false) String name, Model model) {
    	model.addAttribute("name",name);
    	return "hello-template";
    }
    
    @GetMapping("hello-string")
    @ResponseBody
    // http에서 바디부분의 응답부분에 이 데이터를 내가 직접 넣어줄거다
    public String helloString(@RequestParam("name") String name) {
    	return "hello " + name; // hello spring 이라는 문자가 그대로 반환
    }
    
    @GetMapping("hello-api")
    @ResponseBody
    // responsebody 가 명시돼있으면 spring에선 retunr값이 문자나 객체를 반환할때 
    // viewResolver 대신에 HttpMessageConverter안의 String converter나 json converter를
    // 통해서 변환 후 반환한다.
    // 그래서 해당 페이지로 들어가면 html은 하나도 없고 빈 껍데기의 return값만 출력된다
    public Hello helloApi(@RequestParam("name") String name) {
    	Hello hello = new Hello();
    	hello.setName(name);
    	return hello;
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