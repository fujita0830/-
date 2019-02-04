package com.internousdev.cyan.util;

public class CommonUtility {
	public String getRamdomValue() {
		String value="";
		double d;
		for(int i=1; i<=16; i++) {
			d=Math.random() * 10;
			value=value+((int)d);
		}
		return value;
	}

	public String[] parseArrayList(String s) {
		return s.split(", ",0);
	}
}
