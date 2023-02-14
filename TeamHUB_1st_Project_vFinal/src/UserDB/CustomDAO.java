package UserDB;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CustomDAO {

	static String filename = "MemberList.txt";

	private static ArrayList<CustomerInfo> customList;

	// readFile 추가함
	public ArrayList<CustomerInfo> readFile(String fname) {

		try {
			if (fname.equals("")) {
				fname = filename;
			}

			customList = new ArrayList<>();

			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8));

			while (true) {
				String text = br.readLine(); // FILE 안의 내용 한 라인씩 읽음

				if (text == null) { // 라인에 값이 없으면 읽기 종료
					break;
				}

				String[] strArray = text.split("/");
				int a = Integer.parseInt(strArray[6]);
				
				customList.add(new CustomerInfo(strArray[0],strArray[1], strArray[2], strArray[3], strArray[4],
						strArray[5], a, strArray[7]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return customList;
	}

	public static ArrayList<CustomerInfo> getCustomList() {
		return customList;
	}

	public static void setCustomList(ArrayList<CustomerInfo> customList) {
		CustomDAO.customList = customList;
	}

	public CustomDAO() {
		if (customList == null) {
			init();
		}
	}

	private void init() { // 초기 정보
		customList = new ArrayList<>();
	}

	// login 확인 -> id, pw 맞으면 true | 틀리면 false
	public MyInfo loginCheck(String id, String pw) {

		for (int i = 0; i < customList.size(); i++) {
			if (customList.get(i).getId().equals(id) && customList.get(i).getPw().equals(pw)) {
				return new MyInfo(customList.get(i).getName(), id, pw,customList.get(i).getGrade(),customList.get(i).getTotal());
			}
		}
		return new MyInfo();
	}

	public boolean checkId(String id) {

		for (int i = 0; i < customList.size(); i++) {
			if (customList.get(i).getId().equals(id)) {
				return false;
			}
		}
		return true;
	}

	public void registCustomer(String id, String pw, String name, String birthday, String sex, String phone,
			int total, String grade) {
		customList.add(new CustomerInfo(id, pw, name, birthday, sex, phone, total, grade));
	}

	public String findId(String name) {
		for (int i = 0; i < customList.size(); i++) {
			if (customList.get(i).getName().equals(name)) {
				return customList.get(i).getId();
			}
		}
		return null;
	}

	public String findPw(String id) {
		for (int i = 0; i < customList.size(); i++) {
			if (customList.get(i).getId().equals(id)) {
				return customList.get(i).getPw();
			}
		}
		return null;
	}

	public static boolean validateId(String id) { // 중복체크
		for (int i = 0; i < customList.size(); i++) {
			if (customList.get(i).getId().equals(id)) {
				return false;
			}
		}
		return true;
	}

}
