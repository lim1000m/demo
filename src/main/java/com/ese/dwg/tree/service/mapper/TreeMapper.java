package com.ese.dwg.tree.service.mapper;

import java.util.List;
import java.util.Map;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface TreeMapper {

	List<EgovMap> getTreeData(Map<String, String> paramMap);
}