package com.example.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import com.example.demo.member.Member;


// 테스트 케이스는 서로 의존관계 없이 작성해야한다 그래야 에러가 안뜸 이거 엄청 중요 !!!!!!!!!!!!!!!!!!
class MemoryMemberRepositoryTest {

	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	@AfterEach //메서드의 동작이 끝날때마다 실행되는 콜백 메서드
	public void afterEach() {
		// 메서드가 끝날때마다 리포지토리의 메모리를 지워준다. 그래서 테스트를 실행할때 중복으로 값이 지정돼도 
		// 해당 메서드가 메모리를 지워주기 때문에 에러가 안남
		repository.clearStore();
	}
	
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		
		Member result = repository.findById(member.getId()).get();
		System.out.println("result = " + (result == member));
		
		//메모리에 있는 member와 result가 같은 값인가 검증해주는 메서드
		//같은 값이 안들어오면 Junit test 할때 실패가 뜨고 기대값과 결과를 보여줌
		//아래 코드는 실패의 예
		// Assertions.assertEquals(member, null);
		
		
		// 원래는 Assertions.assertThat(member).isEqualTo(result);
		// 이게 맞으나 Assertions를 static import했기 때문에 assertThat만 단독으로 사용 가능하다.
		assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName("spring1").get();
		
		assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		
		assertThat(result.size()).isEqualTo(2);
		
		// 전체 데이터를 테스트 할때 findByName에서 먼저 멤버에 스프링1,2를 집어넣었기 때문에 
		// 테스트를 할때는 에러가 날수밖에 없다. 그렇기 때문에 테스트 할때마다 메모리를 비워줘야함
		// 테스트할때 실행순서는 랜덤이다. 그래서 전체 테스트 할때 될수도 있고 안될수도 있을듯???
	}
	
	
}
