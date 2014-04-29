package com.ese.demo.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ese.demo.service.HomeService;
import com.ese.entity.Item;
import com.ese.entity.Order;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

	@Autowired
	SessionFactory sessionFactory;
	
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	@Transactional
	public String insert(Item item)  {
		Session session = getCurrentSession();
		session.save(item);
		return "success!!";
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Item> getHomeTest() {
		Session session = getCurrentSession();
		return session.createQuery("FROM Item").list();
	}
	
	@Transactional
	public List<?> homeTest1() {
		Session session = getCurrentSession();
		String hql = "SELECT item FROM ITEM";
		Query query = session.createQuery(hql);
		List<?> results = query.list();
		return results;
	}
	
	@Transactional
	public void delete(Item item) {
		Session session  = getCurrentSession();
		session.delete(item);
	}
	
	@Transactional
	public void update(Item item) {
		Session session = getCurrentSession();
		session.update(item);
	}
	
	@Transactional
	public Item findOne(Item item) {
		Session session = getCurrentSession();
		return (Item) session.get(Item.class, item.getId());
	}
	
	@Transactional
	public Order findJoin(Order order) {
		Session session = getCurrentSession();
		return (Order) session.get(Order.class, order.getId());
	}
}
