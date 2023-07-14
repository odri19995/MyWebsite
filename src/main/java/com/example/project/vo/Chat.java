package com.example.project.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
	private int id;
	private String regDate;
	private int memberId;
	private int articleId;
	private String userMessage;
	private String response;
}
