/**
 * Project Name:MyJava  
 * File Name:WyTest.java  
 * Package Name:com.test1  
 * Date:Mar 13, 2017 3:58:08 PM  
 * Copyright (c) 2017, wy All Rights Reserved.  
 *  
 */
package com.test1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 *  
 * @author leslie  
 * @version   
 * @since version 1.0  
 */
public class WyTest {
	public static void main(String[] args) throws IOException{
		if(args == null || args.length < 2){
			System.out.println("usage: WyTest [file] [searchString]");
			return;
		}
		
		File file = new File(args[0]);
		String searchStr = args[1];
		
		if(!file.exists()){
			System.out.println(file.getAbsolutePath() + " not exists.");
			return;
		}
		
	   Map<Integer, String> treeMap = new TreeMap<Integer, String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			while(line != null && line.length() > 0){
				treeMap.put(calcuNumOfOccur(line, searchStr), line);
			}
		} catch (FileNotFoundException e) {
			throw e; 
		}
		
		for(Entry<Integer, String> entry : treeMap.entrySet()){
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}
	
	private static Integer calcuNumOfOccur(String line, String str){
		int occur = line.indexOf(str);
		int result = 0;
		while(occur != -1){
			result++;
			occur = line.indexOf(str, result);
		}
		return result;
	}
}
