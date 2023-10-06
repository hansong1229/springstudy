package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.member.Member;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.MemoryMemberRepository;

// Service라고 명시해주면 스프링이 알아서 인식을 하게 된다.

@Transactional
// jpa를 사용하려면 서비스 부분에 Transactional을 어노테이션해야한다.
public class MemberService {

	private final MemberRepository memberRepository;

public MemberService(MemberRepository memberRepository) {
	// 외부에서 넣어주도록 하는 이유 = 여기서 쓰는 멤버 서비스와 테스트에서 쓰는 서비스가 다르게 되면 값이 넘어갈때 오류를 일으킬수도 있음
		this.memberRepository = memberRepository;
	}

//	회원가입
	public Long join(Member member) {
		
		long start = System.currentTimeMillis();
		
		// 같은 이름이 있는 중복 회원 X
		// result.orElserGet() ---> 값이 없으면 이 값을 꺼내라는 메서드

		/*
		 * memberRepository.findByName(member.getName()) // ifPresent = 값이 있으면 m =
		 * member .ifPresent(m -> { throw new IllegalStateException("이미 존재하는 회원입니다.");
		 * });
		 */
		// 위의 코드를 refactoring 한게 아래의 validateDuplicateMember(member); 단축키 알트+쉬프트+M
		try {
			
			validateDuplicateMember(member); // 중복 회원 검증
			memberRepository.save(member);
			return member.getId();
		}finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("join = "+ timeMs + "ms");
		}
	}

	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
				// ifPresent = 값이 있으면 m = member
				.ifPresent(m -> {
					throw new IllegalStateException("이미 존재하는 회원입니다.");
				});
	}

	// 전체 회원 조회
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
}
