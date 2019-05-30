package com.rest.lightOn.Service;

import java.util.List;

import com.rest.lightOn.entity.Cctv;

public interface CctvService {
	
	List<Cctv> findByOrgId(int orgId);

}