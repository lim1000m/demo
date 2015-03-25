package com.ese.demo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DummyDaoTest {

	private List<String> database;
	private DummyDao dummyDao;
	
	@Before
	public void setUp() throws Exception {
		database = new ArrayList<String>();
		for(int i=0; i<20; i++) {
			database.add("test"+i);
		}
		dummyDao = new DummyDao(database);
	}

	@Test
	public void testDelete() {
		assertEquals(1, dummyDao.find("test0").size()); //find test0을 찾아서 있는지 검사함
		dummyDao.delete("test0"); //test20을 add함
		assertEquals(19, database.size()); //19를 찾는게 아니라 사이즈를 구해옴 왜냠19개를등록했을거니까
		assertEquals(0, dummyDao.find("test0").size()); //
	}

	@Test
	public void testAdd() {
		assertEquals(0, dummyDao.find("test20").size());
		dummyDao.add("test20");
		assertEquals(21, database.size());
		assertEquals(1, dummyDao.find("test20").size());
	}

	@Test
	public void testFind() {
		List<String> results = dummyDao.find("2");
		assertEquals(2, results.size());
		for (String result : results) {
//			assertTrue(result.equals("test2") || results.equals("test12"));
		}
	}

}
