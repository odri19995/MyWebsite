package com.example.project.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown =true)
public class KakaoProfile {

	public Long id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;
	
	@Data
	@JsonIgnoreProperties(ignoreUnknown =true)
	public class Properties {
		private String nickname;
	}

	@Data
	@JsonIgnoreProperties(ignoreUnknown =true)
	public class KakaoAccount {
		private Boolean profile_nickname_needs_agreement;
		private Profile profile;
		private Boolean has_email;
		private Boolean email_needs_agreement;
		private Boolean has_gender;
		private Boolean gender_needs_agreement;
		private String email;

		@Data
		@JsonIgnoreProperties(ignoreUnknown =true)
		public class Profile {
			private String nickname;
		}

	}

}
