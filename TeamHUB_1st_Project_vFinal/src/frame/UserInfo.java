package frame;

import java.util.HashMap;
import java.util.Map;

import UserDB.CustomerInfo;

public class UserInfo {

	public static Map<String, CustomerInfo> UserInfoMap = new HashMap<>();

	public static CustomerInfo getUserInfoMap(String userId) {
		return UserInfoMap.get(userId);
	}

	public UserInfo(String userId, CustomerInfo c) {
		if (UserInfoMap.get(userId) == null) {
			init(userId, c);
		}

	}

	private void init(String userId, CustomerInfo c) {
		UserInfoMap.put(userId, c);
	}

}
