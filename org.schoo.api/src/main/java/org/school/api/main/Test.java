package org.school.api.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		
		String s="Niel O' Brian";
		School_API.riteToFile("SSS");
		System.out.println(s.replace("'", "\\'"));
	}

}
