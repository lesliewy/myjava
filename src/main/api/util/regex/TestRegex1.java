/**
 * Project Name:MyJava  
 * File Name:AboutRegex1.java  
 * Package Name:api.util.regex  
 * Date:Oct 30, 20139:26:34 AM  
 * Copyright (c) 2013, wy All Rights Reserved.  
 *  
 */
package api.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class TestRegex1 {

    /**
     * 查找以Java开头,任意结尾的字符串
     */
    @Test
    public void test1() {
        Pattern pattern = Pattern.compile("^Java.*");
        Matcher matcher = pattern.matcher("Java不是人");
        boolean b = matcher.matches();
        //当条件满足时，将返回true，否则返回false
        System.out.println(b);
    }

    /**
     * 以多条件分割字符串时
     */
    @Test
    public void test2() {
        Pattern pattern = Pattern.compile("[, |]+");
        String[] strs = pattern.split("Java Hello World  Java,Hello,,World|Sun");
        for (int i = 0; i < strs.length; i++) {
            System.out.println(strs[i]);
        }
    }

    /**
     * 文字替换（首次出现字符）
     */
    @Test
    public void test3() {
        Pattern pattern = Pattern.compile("正则表达式");
        Matcher matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World");
        //替换第一个符合正则的数据
        System.out.println(matcher.replaceFirst("Java"));
    }

    /**
     * 文字替换（全部）
     */
    @Test
    public void test4() {
        Pattern pattern = Pattern.compile("正则表达式");
        Matcher matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World");
        //替换第一个符合正则的数据
        System.out.println(matcher.replaceAll("Java"));
    }

    /**
     * 文字替换（置换字符）
     */
    @Test
    public void test5() {
        Pattern pattern = Pattern.compile("正则表达式");
        Matcher matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World ");
        StringBuffer sbr = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sbr, "Java");
        }
        matcher.appendTail(sbr);
        System.out.println(sbr.toString());
    }

    /**
     * 验证是否为邮箱地址
     */
    @Test
    public void test6() {
        String str = "ceponline@yahoo.com.cn";
        Pattern pattern = Pattern.compile("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        System.out.println(matcher.matches());
    }

    /**
     * 查找html中对应条件字符串
     */
    @Test
    public void test7() {
        Pattern pattern = Pattern.compile("href=\"(.+?)\"");
        Matcher matcher = pattern.matcher("<a href=\"index.html\">主页</a>");
        if (matcher.find())
            System.out.println("group(0): " + matcher.group(0) + "\ngroup(1): " + matcher.group(1));
    }

    /**
     * 替换指定{}中文字
     */
    @Test
    public void test8() {
        String str = "Java目前的发展史是由{0}年-{1}年";
        String[][] object = { new String[] { "\\{0\\}", "1995" }, new String[] { "\\{1\\}", "2007" } };
        System.out.println(replace(str, object));
    }
    
    public static String replace(final String sourceString, Object[] object) {
        String temp = sourceString;
        for (int i = 0; i < object.length; i++) {
            String[] result = (String[]) object[i];
            Pattern pattern = Pattern.compile(result[0]);
            Matcher matcher = pattern.matcher(temp);
            temp = matcher.replaceAll(result[1]);
        }
        return temp;
    }
}
