package com.ese.dwg.user.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ese.domain.DeptAuth;
import com.ese.domain.Dvsn;
import com.ese.domain.UserInfo;
import com.ese.dwg.user.service.mapper.UserMapper;

@Service("userService")
public class UserService {

	@Autowired
	SqlSession session;
	
	public UserInfo loginSso(UserInfo userInfo) {
		return session.getMapper(UserMapper.class).getLoginSso(userInfo);
	}
	
	public DeptAuth getDeptAuth(String userName) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		
		DeptAuth deptAuth = mapper.getDeptAuth(userName);
		List<Dvsn> dvsn = mapper.getDvsn();
		
		if(deptAuth.getDvsnCd().equals("SU")) {
			deptAuth.setDvsnCd(dvsn.get(0).getDvsnCd());
		}
		return deptAuth;
	}
} 
