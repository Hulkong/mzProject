package com.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rest.Mukza.member.Repository.MemberRepository;
import com.rest.Mukza.member.entity.Member;

@SpringBootApplication
public class RestTestApplication implements CommandLineRunner {

	@Autowired
	MemberRepository memberRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(RestTestApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		
		Iterable<Member> list1 = memberRepository.findAll();
		
		System.out.println("findAll() Method.");
		for( Member m : list1){
			System.out.println(m.toString());
		}
		
		
	}

}
