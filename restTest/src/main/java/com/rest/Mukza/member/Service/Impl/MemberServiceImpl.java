package com.rest.Mukza.member.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.Mukza.member.Repository.MemberRepository;
import com.rest.Mukza.member.Service.MemberService;
import com.rest.Mukza.member.entity.Member;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;




    @Override
    public List<Member> findAll() {

          List<Member> members = new ArrayList<>();
          System.out.println("findAll  start " );
          memberRepository.findAll().forEach(e -> members.add(e));
          System.out.println("findAll  :  " + members );

          return members;

    }
	
}
