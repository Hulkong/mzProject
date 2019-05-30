package com.rest.lightOn.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.Mukza.member.Repository.MemberRepository;
import com.rest.Mukza.member.Service.MemberService;
import com.rest.Mukza.member.entity.Member;
import com.rest.lightOn.Repository.CctvRepository;
import com.rest.lightOn.Service.CctvService;
import com.rest.lightOn.entity.Cctv;

@Service
public class CctvServiceImpl implements CctvService{

    @Autowired
    private CctvRepository cctvRepository;




    @Override
    public List<Cctv> findByOrgId(int orgId) {

          List<Cctv> cctvList = new ArrayList<>();
          System.out.println("findAll  start " );
//          cctvRepository.findByOrgId(orgId).forEach(e -> cctvList.add(e));
          System.out.println("findAll  :  " + cctvList );

          return cctvList;

    }
	
}
