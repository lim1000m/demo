package com.ese.domain;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BaseVO {
	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();

		for (Field field: this.getClass().getDeclaredFields()) {
			try {
				field.setAccessible(true);
				map.put(field.getName(), field.get(this));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
