package com.ese.demo;

import java.util.ArrayList;
import java.util.List;

public class DummyDao {
	private List<String> database;
	
	public DummyDao(List<String> databases) {
		this.database = databases;
	}
	
	public void delete(String element) {
		database.remove(element);
	}
	
	public void add(String element) {
		database.add(element);
	}
	
	public List<String> find(String queryWord) {
		List<String> result = new ArrayList<String>();
		for(String element : database) {
			if(element.contains(queryWord)) {
				result.add(element);
			}
		}
		return result;
 	}
}
