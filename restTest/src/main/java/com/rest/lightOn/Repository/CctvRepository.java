package com.rest.lightOn.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.rest.Mukza.member.entity.Member;
import com.rest.lightOn.entity.Cctv;

public interface CctvRepository extends CrudRepository<Cctv, Integer>{

//	List<Cctv> findByorgId(int org_id);
	
}
