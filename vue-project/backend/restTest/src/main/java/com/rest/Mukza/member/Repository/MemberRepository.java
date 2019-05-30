package com.rest.Mukza.member.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.rest.Mukza.member.entity.Member;

public interface MemberRepository extends CrudRepository<Member, Integer>{

	List<Member> findAll();
	
}
