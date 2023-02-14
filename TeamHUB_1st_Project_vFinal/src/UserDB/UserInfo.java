package UserDB;

import java.util.HashMap;
import java.util.Map;

public class UserInfo {

	public static Map<String, MyInfo> UserInfoMap = new HashMap<>();

	public static MyInfo getUserInfoMap(String userId) {
		return UserInfoMap.get(userId);
	}

	public UserInfo(String userId, MyInfo m) {
		if (UserInfoMap.get(userId) == null) {
			init(userId,m);
		}
	}

	private void init(String userId, MyInfo m) {
		UserInfoMap.put(userId, m);
	}

}
