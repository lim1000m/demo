package com.ese.demo.service;

import java.util.List;
import java.util.Map;

import com.ese.entity.Item;
import com.ese.entity.Order;

public interface HomeService {

	public int insert(Item item);
	public List<Item> getHomeTest();
	public void delete(Item item);
	public void update(Item item);
	public Item findOne(Item item);
	public Order findJoin(Order order);
	public List<Item> getList(Item item);
	public List<Map<String, Object>> getProjectionList();
	public void subJoin();
	public void joinWhere();
	public void testSubQuery();
} 
