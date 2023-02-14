package UserDB;

public class History {
	
	String name, kcal;
	
	public History() {
	}
	public History(String n, String k) {
		name = n;
		kcal = k;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKcal() {
		return kcal;
	}
	public void setKcal(String kcal) {
		this.kcal = kcal;
	}
	

}
