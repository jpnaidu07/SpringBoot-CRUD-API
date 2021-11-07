package com.jpscode.article.payLoads;


public class ArticlePayLoads {

	private ArticlePayLoads() {

	}

	public static final String ARTICLE_DETAILS = "{\r\n" + 
			"  \"id\": 2,\r\n" + 
			"  \"slug\": \"how-to-learn-spring-boot\",\r\n" + 
			"  \"title\": \"How to learn Spring Boot\",\r\n" + 
			"  \"description\": \"Ever wonder how?\",\r\n" + 
			"  \"body\": \"You have to believe\",\r\n" + 
			"  \"createdAt\": \"2020-07-31 23:22:05.71\",\r\n" + 
			"  \"updatedAt\": \"2020-07-31 23:22:05.71\"\r\n" + 
			"}";
	
	public static final String CREATE_ARTICLE = "{\r\n" + 
			"\"title\": \"How to learn Spring Boot\",\r\n" + 
			"\"description\": \"Ever wonder how?\",\r\n" + 
			"\"body\": \"You have to believe\"\r\n" + 
			"}";
	

}