package com.platform.readings.statistics;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class StringUtils {
	
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("Joe");
		list.add("James");
		list.add("Jack");
		Predicate<String> pred1 = name -> "Joe".equals(name);
		Predicate<String> pred2 = name -> "Jack".equals(name);
		list.removeIf(pred1.or(pred2));
		System.out.println(list);
	}

}
