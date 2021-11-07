package com.jpscode.article.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jpscode.article.entity.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {

	public Optional<Article> findBySlug(String slug);

}
