package com.jpscode.article.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Read {
	private String articleId;
	
	private TimeToRead timeToRead;
	
}

