/*
 * Copyright (c) 2019 Jeppesen. All rights reserved
 */

package com.jpscode.article.service;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.jpscode.article.entity.Article;
import com.jpscode.article.payLoads.ArticlePayLoads;
import com.jpscode.article.repository.ArticleRepository;

@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceImplTest {

	@InjectMocks
	ArticleServiceImpl articleService;

	@Mock
	ArticleRepository respository;

	Article expected;

	@Before
	public void setup() throws NumberFormatException, Exception {
		MockitoAnnotations.initMocks(this);
		expected = new Gson().fromJson(ArticlePayLoads.ARTICLE_DETAILS, Article.class);

	}

	@Test
	public void findByIdTest() {
		Optional<Article> articleDetails = Optional.of(expected);
		Mockito.when(respository.findBySlug("abc")).thenReturn(articleDetails);
		Article actual = articleService.findById("abc");
		assertEquals(expected.getId(), actual.getId());
	}

	@Test
	public void saveTest() {
		Article articleDetails = expected;
		Mockito.when(respository.saveAndFlush(Mockito.any(Article.class))).thenReturn(articleDetails);
		Article actual = articleService.save(new Gson().fromJson(ArticlePayLoads.CREATE_ARTICLE, Article.class));
		assertEquals(expected.getId(), actual.getId());
	}

	@Test
	public void updateTest() throws JsonSyntaxException, IllegalAccessException, InvocationTargetException {
		Optional<Article> articleDetails = Optional.of(expected);
		Mockito.when(respository.findBySlug(Mockito.anyString())).thenReturn(articleDetails);
		Mockito.when(respository.save(Mockito.any(Article.class))).thenReturn(articleDetails.get());
		Article actual = articleService.update(Mockito.anyString(),
				new Gson().fromJson(ArticlePayLoads.CREATE_ARTICLE, Article.class));
		assertEquals(expected.getId(), actual.getId());
	}

	@Test
	public void deleteTest() {
		Optional<Article> articleDetails = Optional.of(expected);
		Mockito.when(respository.findBySlug(Mockito.anyString())).thenReturn(articleDetails);
		Article actual = articleService.deleteBySlug(Mockito.anyString());
		assertEquals(expected.getId(), actual.getId());
	}

}
