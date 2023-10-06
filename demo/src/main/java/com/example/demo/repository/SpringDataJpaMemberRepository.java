package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.member.Member;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{

	
	// JPQL select m from Member m where m.name = ? 와 같은 뜻
	// 서비스로직은 모든 애플리케이션이 다르기 때문에 findByName 이외에도 참조할 컬럼명을 메서드명으로 반영해서 검색할 수 있다.
	@Override
	Optional<Member> findByName(String name);
	// 인터페이스만 있으면 스프링데이터JPA가 JPA repository를 받고 있으면 구현체를 자동으로 만든다.
	// 그래서 스프링 빈에 자동으로 등록시킨다.
}
