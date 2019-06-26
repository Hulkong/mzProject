package com.rest.Mukza.member.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.Mukza.member.Service.MemberService;
import com.rest.Mukza.member.entity.Member;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/member")
@Api(value = "MemberController", description = "사용자 관련 API",basePath = "/member")
public class MemberController {

	@Autowired
	MemberService memberService;
	
    @RequestMapping(value = "", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "사용자 목록 조회", notes = "사용자 목록을 조회하는 API.")
    public ResponseEntity<List<Member>> getUserList(){
    	List<Member> members = memberService.findAll();
        return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
    }
}
