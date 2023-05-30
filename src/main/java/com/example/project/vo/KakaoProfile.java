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
		public String nickname;
	}

	@Data
	public class KakaoAccount {
		public Boolean profile_nickname_needs_agreement;
		public Profile profile;
		public Boolean has_email;
		public Boolean email_needs_agreement;
		public Boolean has_gender;
		public Boolean gender_needs_agreement;
		public String email;

		@Data
		public class Profile {
			public String nickname;
		}

	}

}
