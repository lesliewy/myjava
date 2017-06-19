/**
 * Project Name:MyJava  
 * File Name:Test1.java  
 * Package Name:examples.json.gson  
 * Date:Dec 4, 201611:18:23 AM  
 * Copyright (c) 2016, wy All Rights Reserved.  
 *  
 */
package examples.json.gson;

import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
         * ClassName: Test1 <br/>  
         * Function: TODO ADD FUNCTION. <br/>  
         * Reason: TODO ADD REASON(可选). <br/>  
         * date: Dec 4, 2016 11:18:23 AM <br/>  
         *  
         * @author leslie  
         * @version   
         * @since version 1.0  
         */
public class Test1 {

	@Test
	public void test1() {
		String strJson = "{\"url\" : \"/chaxun/zuozhe/324.html\" , \"numofpoems\" : 1 , \"name\" : \"伯夷a\" , \"brief\" :\"较b\", \"tags\":[\"a\", \"b\"]}";
		/*
		Gson gson = new Gson();
		Author author = gson.fromJson(strJson, Author.class);
		*/
		Author author = GsonUtil.parseJsonWithGson(strJson, Author.class);
		System.out.println(author.url + " " + author.getName() + " "
				+ author.getNumofpoems() + " " + author.getBrief() + " " + author.getTags());
	}

	@Test
	public void test2() {
		String strJson = "[ {\"url\" : \"/chaxun/zuozhe/324.html\" , \"numofpoems\" : 1 , \"name\" : \"伯夷a\" , \"brief\" :\"较a\", \"tags\":[\"a\", \"b\"]},"
				+ "{\"url\" : \"/chaxun/zuozhe/324.html\" , \"numofpoems\" : 12 , \"name\" : \"伯夷b\" , \"brief\" : \"较b\", \"tags\":[\"aaa\", \"bbb\"]}]";
		Gson gson = new Gson();
		List<Author> retList = gson.fromJson(strJson,
				new TypeToken<List<Author>>() {
				}.getType());
		for(Author author : retList){
			System.out.println(author.url + " " + author.getName() + " "
					+ author.getNumofpoems() + " " + author.getBrief() + " " + author.getTags());
		}
	}
	
	@Test
	public void test3(){
		String query = "{'$match':{'author.name':'潘岳'}}|{'$unwind':'$poems'}|{'$project':{'category_url':'$category.url', 'category_name':           '$category.name', 'category_numofauthors':'$category.numofauthors', 'author_url':'$author.url', 'author_name':'$author.name', 'author_numofpoems':     '$author.numofpoems', 'author_brief':'$author.brief', 'poem_url':'$poems.url', 'poem_name':'$poems.name', 'poem_content':'$poems.content',             'poem_appreciation':'$poems.appreciation', 'poem_tags':'$poems.tags'}}";
		String[] arr = query.split("\\|");
		for(String str : arr){
			System.out.println(str);
		}
	}

	class Author {
		private String url = "";
		private int numofpoems = 0;
		private String name = "";
		private String brief = "";
		private List<String> tags = null;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public int getNumofpoems() {
			return numofpoems;
		}

		public void setNumofpoems(int numofpoems) {
			this.numofpoems = numofpoems;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getBrief() {
			return brief;
		}

		public void setBrief(String brief) {
			this.brief = brief;
		}

		public List<String> getTags() {
			return tags;
		}

		public void setTags(List<String> tags) {
			this.tags = tags;
		}

	}

}
