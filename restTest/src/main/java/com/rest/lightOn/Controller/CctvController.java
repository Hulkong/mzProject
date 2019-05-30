package com.rest.lightOn.Controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rest.Mukza.member.Service.MemberService;
import com.rest.Mukza.member.entity.Member;
import com.rest.lightOn.Service.CctvService;
import com.rest.lightOn.entity.Cctv;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/cctv")
@Api(value = "CctvController", description = "사용자 관련 API",basePath = "/cctv")
public class CctvController {

	@Autowired
	CctvService cctvService;
	
	
    @RequestMapping(value = "/{orgId}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ApiOperation(value = "사용자 목록 조회", notes = "사용자 목록을 조회하는 API.")
    public ResponseEntity<List<Cctv>> getCctvList(@PathVariable("orgId") int orgId){
    	List<Cctv> cctvList = cctvService.findByOrgId(orgId);
        return new ResponseEntity<List<Cctv>>(cctvList, HttpStatus.OK);
    }
}
