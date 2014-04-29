package com.ese.demo.service;

import java.util.List;

import com.ese.entity.Item;
import com.ese.entity.Order;

public interface HomeService {

	public String insert(Item item);
	public List<Item> getHomeTest();
	public void delete(Item item);
	public void update(Item item);
	public Item findOne(Item item);
	public Order findJoin(Order order);
} 
