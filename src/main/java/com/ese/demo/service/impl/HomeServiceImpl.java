package com.ese.demo.service.impl;

import org.springframework.stereotype.Service;

import com.ese.demo.service.HomeService;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

	@Override
	public String homeTest() {
		return "success!!";
	}

}
