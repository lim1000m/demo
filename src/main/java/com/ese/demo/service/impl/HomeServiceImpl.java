package com.ese.demo.service.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ese.demo.service.HomeService;
import com.ese.entity.Item;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

	
	@Autowired
	SessionFactory sessionFactory;
	
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	@Transactional
	public String homeTest()  {
		Session session = getCurrentSession();
		Item item = new Item();
		item.setPrice(10000);
		item.setProduct("테스트");
		item.setQuantity(1);
		session.save(item);
		
		return "success!!";
	}
}
