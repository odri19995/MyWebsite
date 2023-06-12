package com.example.project.vo;

import lombok.Data;

@Data
public class OpenAIProfile {

	public String id;
	public String object;
	public String model;
	public choices choices;
	
	@Data
	public class Properties {
		public String nickname;
	}

	@Data
	public class choices {
		public message message;
		@Data
		public class message {
			public String role;
			public String content;
		}

	}

}