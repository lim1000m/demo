package com.ese.dwg.tree.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ese.dwg.tree.service.mapper.TreeMapper;

import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service("treeService")
public class TreeService {

	@Autowired
	SqlSession session;
	
	public List<EgovMap> getTreeData(Map<String, String> paramMap) {
		return session.getMapper(TreeMapper.class).getTreeData(paramMap);
	}
} 
