package com.example.demo;


import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.repository.JdbcTemplateMemberRepository;
import com.example.demo.repository.JpaMemberRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;
import com.example.demo.service.MemberService;

@Configuration
public class SpringConfig {
	
	private final MemberRepository memberRepository;
	
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	// jpa를 사용할때 쓰는 DI
	/*
	 * private EntityManager em;
	 * 
	 * @Autowired public SpringConfig(EntityManager em) { this.em = em; }
	 */
	
	
	// Jdbc template을 쓸때 필요한 객체
	/*
	 * private DataSource dataSource;
	 * 
	 * @Autowired public SpringConfig(DataSource dataSource) { this.dataSource =
	 * dataSource; }
	 */
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}
	

	// bean을 따로 등록하면 좋은점 : 나중에 수정사항이 생겼을때 bean만 고치면 모든게 수정되기 때문에 진짜 편하다
	// @Autowired를 통한 DI는 스프링이 관리하는 객체에서만 동작함. 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작X
	// 또 한가지 방법으로 xml 문서로 설정하는 방법이 있긴 하지만 요즘 잘 사용하지 않기에 여기선 그냥 넘어감
/*	public MemberRepository memberRepository() {
		
		
		// return new JpaMemberRepository(em);
		// Jpa를 쓰기 위함
		
		
		// return new JdbcTemplateMemberRepository(dataSource);
		// jdbctemplate을 쓸때 리턴해주던 값
		
		
		// return new MemoryMemberRepository();
		// 위 코드를 쳤었던 이유 = 예제의 상황에서는 아직 DB가 정해져 있지 않았던 상황이었기에 메모리에 정보를 담아준 상태였었다.
		// 그래서 임시로 메모리에 정보를 담는 클래스가 필요했었다. 이렇게 코드를 짜는게 나중에 전체 코드를 수정하지 않고 
		// Bean에서만 해당 클래스를 교체만 해주면 되기 때문에 유지보수에 용이함
		
		// @Autowired를 통한 DI는 helloController, MemberService 등과 같이 스프링이 관리하는 객체에서만 동작한다.
		// spring bean에만 등록돼있는 메서드, 객체만 @autowired를 사용할 수 있다.
	}*/
	
	
	
}
