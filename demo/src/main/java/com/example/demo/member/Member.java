package com.example.demo.member;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
// 자바가 관리하는 Entity라고 명시해줘야 JPA 사용 가능
public class Member {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	// Id -> PK값을 명시 , GeneratedValue(strategy = GenerationType.IDENTITY) -> Autoincreament되는 PK다고 알려줌
	private Long id;
	
//	@Column(name="username") 만약 자바에서는 변수명이 name인데 DB컬럼명은 username이라면 이렇게 맵핑을 할 수 있다
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}