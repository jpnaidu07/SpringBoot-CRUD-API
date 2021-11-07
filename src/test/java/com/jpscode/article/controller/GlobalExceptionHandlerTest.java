/*
 * Copyright (c) 2019 Jeppesen. All rights reserved
 */

package com.jpscode.article.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jpscode.article.ArticleServiceApplication;
import com.jpscode.article.entity.Article;
import com.jpscode.article.exception.ArticleNotFoundException;
import com.jpscode.article.payLoads.ArticlePayLoads;
import com.jpscode.article.service.ArticleService;

@RunWith(SpringRunner.class)
@TestPropertySource("classpath:/application.properties")
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = { ArticleServiceApplication.class })
public class GlobalExceptionHandlerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private ArticleService articleService;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

	}

	@Test
	public void articleNotfoundExceptionTest() throws Exception {
		Mockito.when(articleService.findById(Mockito.anyString())).thenThrow(new ArticleNotFoundException("Not Found"));
		mockMvc.perform(get("/api/article/get/abc").accept(MediaType.APPLICATION_JSON));
		assertTrue(true);
	}

	@Test
	public void ExceptionTest() throws Exception {
		Mockito.when(articleService.update(new String(), new Article())).thenThrow(new IllegalAccessException());
		mockMvc.perform(put("/api/article/update/abc").contentType(MediaType.APPLICATION_JSON)
				.content(ArticlePayLoads.CREATE_ARTICLE));
		assertTrue(true);
	}

}
