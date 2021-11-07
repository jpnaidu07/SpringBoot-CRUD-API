package com.jpscode.article.controller;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpscode.article.entity.Article;
import com.jpscode.article.entity.Read;
import com.jpscode.article.service.ArticleService;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
	@PostMapping("/create")
	public ResponseEntity<Article> createArticle(
			@RequestBody(required = true) Article article) {
		return new ResponseEntity<>(articleService.save(article), HttpStatus.OK);
	}
	
	@PutMapping("/update/{slug}")
	public ResponseEntity<Article> updateArticle(@PathVariable("slug") String slug, @RequestBody(required = true) Article article) throws IllegalAccessException, InvocationTargetException {
		return new ResponseEntity<>(articleService.update(slug,article), HttpStatus.OK);
	}
	
	@GetMapping("/get/{slug}")
	public ResponseEntity<Article> getArticle(@PathVariable("slug") String slug) {
		return new ResponseEntity<>(articleService.findById(slug), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{slug}")
	public ResponseEntity<Article> deleteArticle(@PathVariable("slug") String slug) {
		return new ResponseEntity<>(articleService.deleteBySlug(slug), HttpStatus.OK);
	}
	
	@GetMapping("/read/time/{slug}")
	public ResponseEntity<Read> readArticle(@PathVariable("slug") String slug) {
		return new ResponseEntity<>(articleService.readArticleBySlug(slug), HttpStatus.OK);
	}
}