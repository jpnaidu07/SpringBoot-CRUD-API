/*
 * Copyright (c) 2019 Jeppesen. All rights reserved
 */

package com.jpscode.article.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.jpscode.article.entity.Article;
import com.jpscode.article.exception.GlobalExceptionHandler;
import com.jpscode.article.payLoads.ArticlePayLoads;
import com.jpscode.article.service.ArticleService;

@RunWith(MockitoJUnitRunner.class)
public class ArticleControllerTest {

	@InjectMocks
	ArticleController articleController;

	@Mock
	ArticleService articleService;

	private MockMvc mockMvc;

	Article article = new Article();

	@Before
	public void init() {

		article = new Gson().fromJson(ArticlePayLoads.ARTICLE_DETAILS, Article.class);
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(articleController)
				.setControllerAdvice(new GlobalExceptionHandler()).build();
	}

	@Test
	public void fetchArticleDetailsTest() throws Exception {
		ResponseEntity<Article> expected = new ResponseEntity<>(article, HttpStatus.OK);
		Mockito.when(articleService.findById(Mockito.anyString())).thenReturn(article);
		MvcResult result = this.mockMvc.perform(get("/api/article/get/abc")).andDo(print()).andExpect(status().isOk())
				.andReturn();
		assertEquals(expected.getStatusCodeValue(), result.getResponse().getStatus());
	}

	@Test
	public void createArticleTest() throws Exception {
		ResponseEntity<Article> expected = new ResponseEntity<>(article, HttpStatus.OK);
		Mockito.lenient().when(articleService.save(new Article())).thenReturn(article);
		MvcResult result = this.mockMvc.perform(post("/api/article/create").contentType(MediaType.APPLICATION_JSON)
				.content(ArticlePayLoads.CREATE_ARTICLE)).andDo(print()).andExpect(status().isOk()).andReturn();
		assertEquals(expected.getStatusCodeValue(), result.getResponse().getStatus());
	}

	@Test
	public void updateArticleTest() throws Exception {
		ResponseEntity<Article> expected = new ResponseEntity<>(article, HttpStatus.OK);
		Mockito.lenient().when(articleService.update(new String(), new Article())).thenReturn(article);
		MvcResult result = this.mockMvc.perform(put("/api/article/update/abc").contentType(MediaType.APPLICATION_JSON)
				.content(ArticlePayLoads.CREATE_ARTICLE)).andDo(print()).andExpect(status().isOk()).andReturn();
		assertEquals(expected.getStatusCodeValue(), result.getResponse().getStatus());
	}

	@Test
	public void deleteArticleTest() throws Exception {
		ResponseEntity<Article> expected = new ResponseEntity<>(article, HttpStatus.OK);
		Mockito.lenient().when(articleService.update(new String(), new Article())).thenReturn(article);
		MvcResult result = this.mockMvc.perform(delete("/api/article/delete/abc")).andDo(print())
				.andExpect(status().isOk()).andReturn();
		assertEquals(expected.getStatusCodeValue(), result.getResponse().getStatus());
	}

}
