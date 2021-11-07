package com.jpscode.article.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.slugify.Slugify;
import com.jpscode.article.entity.Article;
import com.jpscode.article.entity.Read;
import com.jpscode.article.entity.TimeToRead;
import com.jpscode.article.exception.ArticleNotFoundException;
import com.jpscode.article.repository.ArticleRepository;
import com.jpscode.article.utils.CopyUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j(topic = "ArticleService")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleRepository articleRepository;

	@Value("${speed.of.average.human}")
	private int speed;

	private static Slugify slg = new Slugify();

	private static BeanUtilsBean copyNonNull = new CopyUtils();

	@Override
	public List<Article> findAll() {
		return articleRepository.findAll();
	}

	@Override
	public Article findById(String slug) {
		log.info("Fetching the details from Article using slug: {}", slug);
		Optional<Article> articleDetails = articleRepository.findBySlug(slug);
		Article article = null;
		if (articleDetails.isPresent()) {
			article = articleDetails.get();
		} else {
			throw new ArticleNotFoundException("No record found for given slug: " + slug);
		}
		log.info("Fetched Article using slug: {}", slug);
		return article;
	}

	@Override
	@Transactional
	public Article save(Article article) {
		
		String slug = slg.slugify(article.getTitle());
		log.info("Saving Article using slug: {}", slug);
		article.setSlug(slug);
		return articleRepository.saveAndFlush(article);
	}

	@Override
	@Transactional
	public Article deleteBySlug(String slug) {
		log.info("Fetching the details from Article to delete by slug: {}", slug);
		Optional<Article> article = articleRepository.findBySlug(slug);
		if (article.isPresent()) {
			articleRepository.delete(article.get());
		} else {
			throw new ArticleNotFoundException("No article found for given slug: " + slug);
		}
		log.info("Deleted article for slug: {}", slug);
		return article.get();
	}

	@Override
	@Transactional
	public Article update(String slug, Article article) throws IllegalAccessException, InvocationTargetException {
		log.info("Fetching the details from Article to update by slug: {}", slug);
		Optional<Article> articleDetails = articleRepository.findBySlug(slug);
		Article articleTemp = null;
		if (articleDetails.isPresent()) {
			articleTemp = articleDetails.get();
			copyNonNull.copyProperties(articleTemp, article);
			articleRepository.save(articleTemp);
		} else {
			throw new ArticleNotFoundException("No article found for given slug: " + slug);
		}
		log.info("Updated the details from Article by slug: {}", slug);
		return articleTemp;
	}

	@SuppressWarnings("static-access")
	@Override
	public Read readArticleBySlug(String slug) {
		log.info("Fetching the details from Article to read by slug: {}", slug);
		Optional<Article> articleDetails = articleRepository.findBySlug(slug);

		Read timeRead = null;
		if (articleDetails.isPresent()) {
			long wordCount = articleDetails.get().getDescription().length() 
					+ articleDetails.get().getBody().length()
					+articleDetails.get().getTitle().length();
			
			long mins = wordCount/speed;
			long seconds = wordCount % speed;
			
			timeRead = new Read().builder().articleId(articleDetails.get().getSlug())
		  .timeToRead(new TimeToRead().builder().mins(mins).seconds(seconds).build()).build();
		} else {
			throw new ArticleNotFoundException("No article found for given slug: " + slug);
		}
		log.info("Successfully read Article by slug: {}", slug);
		return timeRead;
	}

}
