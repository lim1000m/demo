package com.ese.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ese.demo.service.HomeService;
import com.ese.entity.Item;
import com.ese.entity.Order;
import com.ese.entity.ProDept;

@Service("homeService")
public class HomeServiceImpl implements HomeService {

	@Autowired
	SessionFactory sessionFactory;
	
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	@Transactional
	public int insert(Item item)  {
		Session session = getCurrentSession();
		session.save(item);
		return item.getId();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Item> getHomeTest() {
		Session session = getCurrentSession();
		return session.createQuery("FROM Item").list();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Item> getList(Item item) {
		Session session = getCurrentSession();
		List<Item> list = session.createCriteria(Item.class)
							.add(Restrictions.eq("id", item.getId()))
							.add(Restrictions.like("product", item.getProduct(), MatchMode.ANYWHERE)).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Map<String, Object>> getProjectionList() {
		Session session = getCurrentSession();
		Criteria cri = session.createCriteria(Item.class);
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.alias(Projections.count("quantity"), "quantity_count"));
		projectionList.add(Projections.groupProperty("quantity").as("quantity"));
		
		cri.setProjection(projectionList);
		cri.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		
		return cri.list();
	}
	
	@Transactional
	public void subJoin() {
		Session session  = getCurrentSession();

		DetachedCriteria dept = DetachedCriteria.forClass(ProDept.class,"dept")
							    .setProjection(Projections.property("proDeptId"));
		
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = session.createCriteria(Item.class, "item")
		.add(Property.forName("proDept.proDeptId").in(dept))
		.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP).list();
		
		for(Map<String, Object> map : list) {
			System.out.println(map.toString());
		}
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
		session.delete(item);
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
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void joinWhere() {
		Session session  = getCurrentSession();

		List<Item> list = session.createCriteria(Item.class)
				.createAlias("order", "customer")
				.add(Restrictions.like("product", "c", MatchMode.ANYWHERE))
				.add(Restrictions.ilike("product", "c", MatchMode.ANYWHERE))
				.add(Restrictions.like("customer.customer", "Y", MatchMode.ANYWHERE).ignoreCase())
				.list();
		
		for(Item item : list) {
			System.out.println(item.getId()+" / "+item.getProduct()+" / "+item.getProDept().getProDeptNm());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public void testSubQuery() {
		
		Session session = getCurrentSession();
		
		DetachedCriteria deptQuery = DetachedCriteria.forClass(ProDept.class)
									 .setProjection(Projections.projectionList().add(Projections.property("proDeptId"))
											 									.add(Projections.property("proDeptNm")))
									 .add(Restrictions.eq("proDeptNm", "제품"));
								     
		List<Item> list = session.createCriteria(Item.class)
								 .createAlias("proDept", "dept")
							     .add(Subqueries.propertiesEq(new String[]{"dept.proDeptId", "dept.proDeptNm"}, deptQuery)).list();
		
		for(Item item : list) {
			System.out.println(item.getProduct()+"/"+item.getProDept().getProDeptNm());
		}
		
	}
}