package com.example.project.vo;

import lombok.Data;

@Data
public class KakaoProfile {

	public Long id;
	public String connected_at;
	public Properties properties;
	public KakaoAccount kakao_account;
	
	@Data
	public class Properties {
		private String nickname;
	}

	@Data
	public class KakaoAccount {
		private Boolean profile_nickname_needs_agreement;
		private Profile profile;
		private Boolean has_email;
		private Boolean email_needs_agreement;
		private Boolean has_gender;
		private Boolean gender_needs_agreement;
		private String email;

		@Data
		public class Profile {
			private String nickname;
		}

	}

}
