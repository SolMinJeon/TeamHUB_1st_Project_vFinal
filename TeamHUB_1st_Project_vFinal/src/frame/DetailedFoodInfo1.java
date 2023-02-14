package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CS.Client_F;
import MainPage.CartPanel;
import MainPage.Category1;
import MainPage.Diet1;
import MainPage.Hot1;
import MainPage.Meat1;
import MainPage.Rain1;
import MainPage.Seafood1;
import MainPage.Sun1;
import MainPage.Vegan1;
import ProductDB.Product;
import ProductDB.ProductDAO;
import UserDB.CustomerInfo;
import UserDB.MyInfo;

public class DetailedFoodInfo1 extends JPanel {

	public static List<Product> searchProduct = new ArrayList<>();
	ArrayList<Product> Product = new ProductDAO().productList;

	// 음식 상세 정보

	// 음식이미지 리스트에 담에서 출력
	List<ImageIcon> foodImg, ingredientImg;

	// 음식 출력용 변수
	int productId;

	// 텍스트 필드를 화용
	JTextField ingredient, recipe;

	public DetailedFoodInfo1(MyInfo m, CustomerInfo c, Product product, UserDB.CartDAO cart) { // 가상 DB 완성되면 매게변수 수정 (우선
																								// product만
		// 설정해서 넘겨받고 UI 수장)
		setLayout(null);
		setSize(500, 800);
		setBackground(Color.WHITE);

		foodImg = new ArrayList<ImageIcon>(); // 음식이미지
		ingredientImg = new ArrayList<ImageIcon>(); // 영양성분 이미지
		
		// 이미지에 대한 productId에 대한 정보를 저장해준다
		for (int i = 0; i < ProductDAO.productList.size(); i++) { // ProductDAO에 등록된 배열의 수만큼 돌린다
			foodImg.add(new ImageIcon(ProductDAO.productList.get(i).getProductImage())); // 일단 모든 이미지를 가져와서 리스트에 추가함
			if (product.getName().equals(ProductDAO.productList.get(i).getName())) { // Product의 정보와 비교해준다 (이름 비교
				productId = ProductDAO.productList.get(i).getId(); // ProductDAO에 있는 정보(ArrayList 형태)를 불러오고 해당 아이디를 얻는다
			}

		} // for (현재 foodImg에는 모든 이미지가 배열형태로 저장되어 있음

		for (int i = 0; i < ProductDAO.productList.size(); i++) {

			ingredientImg.add(new ImageIcon(ProductDAO.productList.get(i).getProductingredientImage()));

		}

		// =========================상품 이미지와 영양성분 이미지====================
		Image image = foodImg.get(productId).getImage(); // 우선 해당 아이디의 이미지를 담아서
		ImageIcon foodIcon = new ImageIcon(image); // 이미지 아이콘 형태로 저장하고
		JLabel LBfoodIcon = new JLabel(foodIcon); // 라벨을 붙인다 (패널내에서 움직이기 위해서)
		LBfoodIcon.setSize(200, 200); // 사이즈
		LBfoodIcon.setLocation(50, 150); // 좌표
		add(LBfoodIcon); // 패널에 추가

		Image image1 = ingredientImg.get(productId).getImage(); // 우선 해당 아이디의 이미지를 담아서
		ImageIcon ingredientIcon = new ImageIcon(image1); // 이미지 아이콘 형태로 저장하고
		JLabel ingredientLable = new JLabel(ingredientIcon); // 라벨을 붙인다 (패널내에서 움직이기 위해서)
		ingredientLable.setSize(200, 200); // 사이즈
		ingredientLable.setLocation(LBfoodIcon.getX() + 210, LBfoodIcon.getY()); // 좌표
		add(ingredientLable); // 패널에 추가

		// ===================하단의 텍스트 필드==========================(우선 상품이름 + 가격 + 레시피만)
		// 출력), 스크롤바 하단으로
		TextArea recipeInfo = new TextArea("제품명 : " + product.getName() + "\n\n 가격 : " + product.getPrice()
				+ " 원\n\n 레시피 : \n" + product.getProductRecipe(), 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);

		Font font = new Font("안동엄마까투리", Font.BOLD, 10);
		recipeInfo.setFont(font);
		recipeInfo.setLocation(LBfoodIcon.getX(), LBfoodIcon.getY() + 225);
		recipeInfo.setSize(400, 200);
		recipeInfo.setBackground(new Color(255, 255, 255));
		recipeInfo.setEditable(false);
		add(recipeInfo);

		// =====================상단 페널====================
		// JPanel
		// 상단 패널
		JPanel top = new JPanel();
		top.setBackground(new Color(255, 255, 255));
		top.setLayout(null);
		top.setBounds(23, 49, 450, 75);
		add(top);

		// 이미지
		ImageIcon imageLogo = new ImageIcon("./src/Logo2.png");
		ImageIcon imageCs = new ImageIcon("./src/cs.png");
		ImageIcon imageShop = new ImageIcon("./src/shop.png");

		// 상단 버튼
		JButton btnLogo = new JButton(imageLogo);

		JButton btnCs = new JButton(imageCs);
		JButton btnShop = new JButton(imageShop);

		btnLogo.setSize(150, 90);
		btnLogo.setBorderPainted(false);
		btnLogo.setContentAreaFilled(false);
		btnLogo.setFocusPainted(false);
		btnLogo.setLocation(0, -15);

		btnCs.setSize(40, 40);
		btnCs.setBorderPainted(false);
		btnCs.setContentAreaFilled(false);
		btnCs.setFocusPainted(false);
		btnCs.setLocation(298, 0);

		btnShop.setSize(40, 40);
		btnShop.setBorderPainted(false);
		btnShop.setContentAreaFilled(false);
		btnShop.setFocusPainted(false);
		btnShop.setLocation(348, 0);

		ImageIcon imageSearch = new ImageIcon("./src/search.png");
		JButton btnSearch = new JButton(imageSearch);
		btnSearch.setSize(40, 40);
		btnSearch.setBorderPainted(false);
		btnSearch.setContentAreaFilled(false);
		btnSearch.setFocusPainted(false);
		btnSearch.setLocation(398, 0);

		// 검색창
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = JOptionPane.showInputDialog("찾으실 상품을 입력하시오");
				int a = 0;
				System.out.println(itemName);
				if (!(itemName == null)) {
					if (!itemName.equals("")) {
						for (int i = 0; i < ProductDAO.productList.size(); i++) {
							if (ProductDAO.productList.get(i).getName().toLowerCase()
									.contains(itemName.toLowerCase())) {
								Product meatProduct1 = new ProductDAO().searchProduct(itemName); // 상품 정보를 ProductDAO를
																									// 통해 찾고
								FrameBase.getDispose();
								FrameBase.getInstance(new DetailedFoodInfo1(m, c, meatProduct1, cart)); // 상세페이지를 연다
								a++;
							}
						}
						if (a == 0) {
							JOptionPane.showMessageDialog(null, "상품이 존재하지 않습니다.");
						}
					}
				}
			}
		});

		// 상단 패널에 버튼 추가
		top.add(btnLogo);
		top.add(btnCs);
		top.add(btnShop);
		top.add(btnSearch);
		add(top);

		// ================구매하기=================

		ImageIcon imageBuy1 = new ImageIcon("./src/장바구니구니2.png");// 이미지를 불러오고
		Image img1 = imageBuy1.getImage(); // image로 형태 변환
		Image fixedImg1 = img1.getScaledInstance(200, 56, image.SCALE_SMOOTH); // 이미지 사이즈 변환
		ImageIcon fixedBuy1 = new ImageIcon(fixedImg1); // 다시 icon화

		ImageIcon imageBuy2 = new ImageIcon("./src/장바구니구니1.png");
		Image img2 = imageBuy2.getImage();
		Image fixedImg2 = img2.getScaledInstance(200, 56, image.SCALE_SMOOTH);
		ImageIcon fixedBuy2 = new ImageIcon(fixedImg2);
		JButton buttonBuy2 = new JButton(fixedBuy2);
		buttonBuy2 = new JButton(fixedBuy2);
		buttonBuy2.setRolloverIcon(fixedBuy1);

		buttonBuy2.setSize(200, 56);
		buttonBuy2.setBorderPainted(false);
		buttonBuy2.setContentAreaFilled(false);
		buttonBuy2.setFocusPainted(false);
		buttonBuy2.setLocation(150, 600);
		add(buttonBuy2);

		buttonBuy2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (cart.registCart(c.getId(),
						new Product(ProductDAO.productList.get(productId).getId(),
								ProductDAO.productList.get(productId).getName(),
								ProductDAO.productList.get(productId).getPrice(),
								ProductDAO.productList.get(productId).getCnt(),
								ProductDAO.productList.get(productId).getProductImage(),
								ProductDAO.productList.get(productId).getKcal()))) {

					UserDB.CartDAO.cartMap.get(c.getId())
							.add(new Product(ProductDAO.productList.get(productId).getId(),
									ProductDAO.productList.get(productId).getName(),
									ProductDAO.productList.get(productId).getPrice(),
									ProductDAO.productList.get(productId).getCnt(),
									ProductDAO.productList.get(productId).getProductImage(),
									ProductDAO.productList.get(productId).getKcal()));

				} else {

					for (int i = 0; i < UserDB.CartDAO.cartMap.get(c.getId()).size(); i++) {

						UserDB.CartDAO.cartMap.get(c.getId()).get(i)
								.setCnt(UserDB.CartDAO.cartMap.get(c.getId()).get(i).getCnt() + 1);
					}
				}
				JOptionPane.showMessageDialog(null, "장바구니 담기");
			}
		});

		btnCs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Client_F(m, c, cart));
			}
		});
		btnShop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new CartPanel(m, c, cart));
			}
		});

		// 패널 사이즈 + 자리 변경 (아래쪽으로 내림)
		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(255, 255, 255));
		bottom.setLayout(null);
		bottom.setBounds(75, 715, 345, 55);

		ImageIcon imageMy1 = new ImageIcon("./src/my1.png");
		ImageIcon imageMy2 = new ImageIcon("./src/my2.png");
		ImageIcon imageHome1 = new ImageIcon("./src/home1.png");
		ImageIcon imageHome2 = new ImageIcon("./src/home2.png");
		ImageIcon imageBack1 = new ImageIcon("./src/back1.png");
		ImageIcon imageBack2 = new ImageIcon("./src/back2.png");

		JButton btnMy = new JButton(imageMy1);
		JButton btnHome = new JButton(imageHome1);
		JButton btnBack = new JButton(imageBack1);

		btnMy = new JButton(imageMy1);
		btnMy.setRolloverIcon(imageMy2);
		btnMy.setSize(50, 50);
		btnMy.setBorderPainted(false);
		btnMy.setContentAreaFilled(false);
		btnMy.setFocusPainted(false);
		btnMy.setLocation(30, 10);

		btnHome = new JButton(imageHome1);
		btnHome.setRolloverIcon(imageHome2);
		btnHome.setSize(50, 50);
		btnHome.setBorderPainted(false);
		btnHome.setContentAreaFilled(false);
		btnHome.setFocusPainted(false);
		btnHome.setLocation(btnMy.getX() + 125, 10);

		btnBack = new JButton(imageBack1);
		btnBack.setRolloverIcon(imageBack2);
		btnBack.setSize(50, 50);
		btnBack.setBorderPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setFocusPainted(false);
		btnBack.setLocation(btnHome.getX() + 125, 10);

		bottom.add(btnMy);
		bottom.add(btnHome);
		bottom.add(btnBack);
		add(bottom);

		// 마이페이지
		btnMy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				try {
					FrameBase.getInstance(new MyPage1(m, c, cart));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		// 홈
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Category1(m, c, cart));
			}
		});

		// back button
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				if (0 <= productId && productId <= 3) {
					FrameBase.getInstance(new Hot1(m, c, cart));
				} else if (4 <= productId && productId <= 7) {
					FrameBase.getInstance(new Sun1(m, c, cart));
				} else if (8 <= productId && productId <= 11) {
					FrameBase.getInstance(new Rain1(m, c, cart));
				} else if (12 <= productId && productId <= 15) {
					FrameBase.getInstance(new Meat1(m, c, cart));
				} else if (16 <= productId && productId <= 19) {
					FrameBase.getInstance(new Vegan1(m, c, cart));
				} else if (20 <= productId && productId <= 23) {
					FrameBase.getInstance(new Seafood1(m, c, cart));
				} else if (24 <= productId && productId <= 27) {
					FrameBase.getInstance(new Diet1(m, c, cart));
				}
			}
		});

	}

}
