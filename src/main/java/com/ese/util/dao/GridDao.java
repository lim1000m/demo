package com.ese.util.dao;

import java.util.List;

public interface GridDao <OT, PT>{
	public List<OT> getList(PT p);
	public int getCnt(PT p);
}
