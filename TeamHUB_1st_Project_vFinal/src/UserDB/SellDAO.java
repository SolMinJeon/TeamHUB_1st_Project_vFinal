package UserDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ProductDB.Product;

public class SellDAO {

	public static Map<String, ArrayList<Product>> historyMap = new HashMap<>();

	public static ArrayList<Product> getCartList(String userId) {
		return historyMap.get(userId);
	}
	
	public SellDAO(String userId) {
		if (historyMap.get(userId) == null) {
			init(userId);
		}
	}
	
	private static void init(String userId) {
		historyMap.put(userId, new ArrayList<>());
	}

	public boolean registCart(String userId,Product product) {
		for (int i = 0; i < historyMap.get(userId).size(); i++) {
			if (historyMap.get(userId).get(i).getProductImage() == product.getProductImage()) { 
				System.out.println("중복");
				return false;
			}
		}
		return true;
	}
	
	// remove public
	public boolean remove(int id, String userId) {
		for(int i=0;i<historyMap.get(userId).size();i++)  {
			if(historyMap.get(userId).get(i).getId()==id) {
				historyMap.get(userId).remove(i);
				return true;
			}
		}
		return false;
	}
	

	public boolean change(int id,int cnt,String userId) {
		for(int i=0;i<historyMap.get(userId).size();i++)  {
			if(historyMap.get(userId).get(i).getId()==id) {
				historyMap.get(userId).get(i).setCnt(cnt);
				return true;
			}
		}
		return false;
	}
	
	public int totalMoney(String userId) {
		int money=0;
		for(int i=0;i<historyMap.get(userId).size();i++) {
			money+=historyMap.get(userId).get(i).getCnt()*historyMap.get(userId).get(i).getPrice();
		}
		return money;
	}
	
}
