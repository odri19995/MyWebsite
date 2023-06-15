package com.example.project.vo;

import java.util.List;

import lombok.Data;

@Data
public class OpenAIProfile {

	private String id;
	private String object;
	private String model;
	private Long created;
	private Usage usage;
	private List<Choices> choices;
	
	@Data
	public static class Usage {
		private int prompt_tokens;
		private int completion_tokens;
		private int total_tokens;
	}

	@Data
	public static class Choices {
		private message message;
		private String finish_reason;
		private int index;
		@Data
		public static class message {
			private String role;
			private String content;
		}

	}

}