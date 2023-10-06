package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
		
		 // 컨트롤러에서 맵핑값이 없다면 static에 있는 정적 페이지를 반환하지만 
		// 컨트롤러에 맵핑값이 존재하면 static을 무시하고 return에 명시된 페이지를 반환한다.
	}
	
}
