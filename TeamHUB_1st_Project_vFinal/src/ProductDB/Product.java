package ProductDB;

public class Product {

	int id;
	String name; // 상품이름
	int price; // 가격
	int cnt = 1; // 수량
	String productImage; // 제품 이미지
	String productingredientImage; //상품 영양성분
	String ProductRecipe; // 제품 레시피
	String kind; // 종류
	String kcal;//칼로리
	
	
	public Product(int id, String name, int price, String productImage,String productingredientImage, String productRecipe, String kind,String kcal) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.productImage = productImage;
		this.productingredientImage = productingredientImage;
		this.ProductRecipe = productRecipe;
		this.kind = kind;
		this.kcal=kcal;
	}
	
	public Product(int id, String name, int price, String productImage,String productingredientImage, int cnt, String productRecipe, String kind, String kcal) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.productImage = productImage;
		this.productingredientImage = productingredientImage;
		this.cnt = cnt;
		this.ProductRecipe = productRecipe;
		this.kind = kind;
		this.kcal=kcal;
	}

	public Product(int id2, String name2, int price2, String productImage2) {
		this.id = id2;
		this.name = name2;
		this.price = price2;
		this.productImage = productImage2;
	}

	public Product(int id2, String name2, int price2, int cnt2, String productImage2, String kcal) {
		this.id = id2;
		this.name = name2;
		this.price = price2;
		this.cnt = cnt2;
		this.productImage = productImage2;
		this.kcal=kcal;
	}

	public int getId() {
		return id;
	}
	public String getProductingredientImage() {
		return productingredientImage;
	}
	public void setProductingredientImage(String productingredientImage) {
		this.productingredientImage = productingredientImage;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public String getProductRecipe() {
		return ProductRecipe;
	}
	public void setProductRecipe(String productRecipe) {
		ProductRecipe = productRecipe;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getKcal() {
		return kcal;
	}
	public void setKcal(String kcal) {
		this.kcal = kcal;
	}
	
}
