package com.example.project.util;

public class Util {
//	if(loginId == null || loginId.trim().length()==0) {
//		return "아이디를 입력해주세요";
//	}
	
	public static boolean empty(Object obj) {
		
		if (obj == null) {
			return true;
		}
		
		//앞 타입과 뒷 타입을 비교해주는 연산자
//		if(obj instanceof String == false) {
//			return true;
//		}
		
		String str = (String) obj;
		
		return str.trim().length() == 0;	
	}
	
	public static String f(String format, Object... args) {
		return String.format(format, args);
	}
	
	public static String jsHistoryBack(String msg) {
// 돌려줄 메세지가 없는 경우 잘못된 경우 msg를 공백으로 해준다. 
		if (msg == null) {
			msg = "";
		}

		return Util.f("""
					<script>
						const msg = '%s'.trim();
						if (msg.length > 0) {
							alert(msg);
						}
						history.back();
					</script>
					""", msg);
	}

	public static String jsReplace(String msg, String uri) {
		if (msg == null) {
			msg = "";
		}

		if (uri == null) {
			uri = "";
		}

		return Util.f("""
				<script>
					const msg = '%s'.trim();
					if (msg.length > 0) {
						alert(msg);
					}
					location.replace('%s');
				</script>
				""", msg, uri);
	}
}
