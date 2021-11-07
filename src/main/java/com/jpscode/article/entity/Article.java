package com.jpscode.article.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Article")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article {

//	@Generated(GenerationTime.INSERT)
	@Id
	@GeneratedValue
	@Column(name = "id", columnDefinition = "serial", updatable = false)
	private Integer id;

	private String slug;

	private String title;

	private String description;

	private String body;

	@Generated(GenerationTime.INSERT)
	@Column(name = "createdAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
	private String createdAt;

	@Generated(GenerationTime.ALWAYS)
	@Column(name = "updatedAt", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", updatable = false)
	private String updatedAt;

}
