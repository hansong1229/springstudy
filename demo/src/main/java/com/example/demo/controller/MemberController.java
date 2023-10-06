package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.member.Member;
import com.example.demo.service.MemberService;

@Controller
// 컨트롤러, 서비스, 레파지토리 전부 컴포넌트에 의존성이 있다.
// 컨트롤러를 명시해주면 스프링 컨테이너에 멤버 컨트롤러 객체를 넣어둔다
// 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본적으로 싱글톤으로 등록한다(유일하게 하나만 등록해서 공유한다)
// 중복이 안된다는 의미
// 컴포넌트 스캔 스프링 컨테이너에 어노테이션(서비스, 레파지토리, 컨트롤러)을 명시한 클래스들을 찾아서 넣어주는 것
public class MemberController {
	
	private MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}

	// url은 동일하나 GetMapping, PostMapping이 다르기에 같은 url이여도 작동방식이 다르다.
	// 이렇게 동일한 url을 다른기능으로 나타낼수도 있다

	@PostMapping("/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());

		memberService.join(member);
		return "redirect:/";
	}
	
	@GetMapping("/members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
}
