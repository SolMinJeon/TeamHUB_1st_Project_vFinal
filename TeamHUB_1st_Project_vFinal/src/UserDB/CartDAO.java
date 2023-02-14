package UserDB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ProductDB.Product;

public class CartDAO {

	public static Map<String, ArrayList<Product>> cartMap = new HashMap<>();
	

	public static ArrayList<Product> getCartList(String userId) {
		return cartMap.get(userId);
	}
	
	public CartDAO(String userId) {
		if (cartMap.get(userId) == null) {
			init(userId);
		}
	}
	
	private static void init(String userId) {
		cartMap.put(userId, new ArrayList<>());
	}

	public boolean registCart(String userId,Product product) {
		for (int i = 0; i < cartMap.get(userId).size(); i++) {
			if (cartMap.get(userId).get(i).getProductImage() == product.getProductImage()) { 
				System.out.println("중복제품입니다.");
				return false;
			}
		}
		return true;
	}
	
	// remove public
	public boolean remove(int id, String userId) {
		for(int i=0;i<cartMap.get(userId).size();i++)  {
			if(cartMap.get(userId).get(i).getId()==id) {
				cartMap.get(userId).remove(i);
				return true;
			}
		}
		return false;	
	}

	public boolean change(int id,int cnt,String userId) {
		for(int i=0;i<cartMap.get(userId).size();i++)  {
			if(cartMap.get(userId).get(i).getId()==id) {
				cartMap.get(userId).get(i).setCnt(cnt);
				return true;
			}
		}
		return false;
	}
	
	public int totalMoney(String userId) {
		int money=0;
		for(int i=0;i<cartMap.get(userId).size();i++) {
			money+=cartMap.get(userId).get(i).getCnt()*cartMap.get(userId).get(i).getPrice();
		}
		return money;
	}
	
}
