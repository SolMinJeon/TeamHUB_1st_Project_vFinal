package UserDB;

public class MyInfo {
		
		String name;
		String id;
		String pw;
		String grade;
		int total;
		
		MyInfo() {
			this.id = "";
		}
		
		public MyInfo(String name, String id, String pw,  String grade,int total) {
			this.name = name;
			this.id = id;
			this.pw = pw;
			this.total = total;
			this.grade = grade;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getId() {
			return id;
		}
		public void setGrade(String grade) {
			this.grade = grade;
		}
		public String getGrade() {
			return grade;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPw() {
			return pw;
		}
		public void setPw(String pw) {
			this.pw = pw;
		}
		public int getTotal() {
			return total;
		}

}
