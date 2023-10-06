package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.example.demo.member.Member;

public class JpaMemberRepository implements MemberRepository{

	// jpa는 EntityManager라는걸로 모든게 동작함 그래서 꼭 주입받아야함
	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Member save(Member member) {
		// insert를 알아서 해줌
		em.persist(member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// 멤버 클래스에 id라는 식별자를 이용해서 결과값을 조회
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	@Override
	public Optional<Member> findByName(String name) {
		List<Member> result =  em.createQuery("select m from Member m where m.name = :name", Member.class)
		.setParameter("name",name)
		.getResultList();
		// pk기반이 아닌것들은 전부 jpql을 작성해줘야한다. 
		// 이 경우에는 findByName, findAll의 경우
		// 스프링 데이터 JPA를 사용하면 jpql도 안해도됨
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		List<Member> result = em.createQuery("select m from Member m",Member.class).getResultList();
		// table이 아닌 객체를 쿼리로 전송
		return result;
	}

}
