package com.jpscode.article.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.jpscode.article.entity.Article;
import com.jpscode.article.entity.Read;

public interface ArticleService {

	public List<Article> findAll();

	public Article save(Article article);

	public Article deleteBySlug(String slug);

	public Article findById(String slug);

	public Article update(String slug, Article article) throws IllegalAccessException, InvocationTargetException;

	public Read readArticleBySlug(String slug);

}
