package MainPage;

import java.awt.Color;
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
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import CS.Client_F;
import ProductDB.Product;
import ProductDB.ProductDAO;
import UserDB.CartDAO;
import UserDB.CustomerInfo;
import UserDB.MyInfo;
import frame.DetailedFoodInfo1;
import frame.Font1;
import frame.FrameBase;
import frame.MyPage1;

public class Sun1 extends JPanel {

	public static List<Product> searchProduct = new ArrayList<>();
	ArrayList<Product> Product = new ProductDAO().productList;

	public Sun1(MyInfo m, CustomerInfo c, CartDAO cart) {

		// 프레임 사이즈
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(500, 800);

		// JPanel
		// 상단 패널
		JPanel top = new JPanel();
		top.setBackground(new Color(255, 255, 255));
		top.setLayout(null);
		top.setBounds(23, 49, 450, 75);
		add(top);

		// 이미지
		ImageIcon imageLogo = new ImageIcon("./src/Logo2.png");
		JButton btnLogo = new JButton(imageLogo);
		btnLogo.setSize(150, 90);
		btnLogo.setBorderPainted(false);
		btnLogo.setContentAreaFilled(false);
		btnLogo.setFocusPainted(false);
		btnLogo.setLocation(0, -15);

		ImageIcon imageSearch = new ImageIcon("./src/search.png");
		ImageIcon imageCs = new ImageIcon("./src/cs.png");
		ImageIcon imageShop = new ImageIcon("./src/shop.png");

		// 상단 버튼
		JButton btnSearch = new JButton(imageSearch);
		JButton btnCs = new JButton(imageCs);
		JButton btnShop = new JButton(imageShop);

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

		btnSearch.setSize(40, 40);
		btnSearch.setBorderPainted(false);
		btnSearch.setContentAreaFilled(false);
		btnSearch.setFocusPainted(false);
		btnSearch.setLocation(398, 0);

		// 상단 패널에 버튼 추가
		top.add(btnLogo);
		top.add(btnCs);
		top.add(btnShop);
		top.add(btnSearch);
		add(top);

		// 중앙단
		JPanel mid = new JPanel();
		mid.setBackground(new Color(255, 255, 255));
		mid.setLayout(null);
		mid.setBounds(23, 130, 450, 580);
		add(mid);

		JTextPane title = new JTextPane();
		title.setEditable(false);
		title.setText("SUN");
		title.setBackground(Color.WHITE);
		title.setBounds(50, 0, 360, 30);
		title.setFont(new Font1().bigFont());
		StyledDocument doc = title.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		mid.add(title);

		// 김밥
		ImageIcon product1 = new ImageIcon("./src/ProductDB/김밥.jpg");
		JButton itemBtn1 = new JButton(product1);
		itemBtn1.setName("김밥");
		itemBtn1.setSize(150, 150);
		itemBtn1.setLocation(50, 50);
		itemBtn1.setBorderPainted(false);
		itemBtn1.setContentAreaFilled(false);
		itemBtn1.setFocusPainted(false);

		// 음식마다 수정 이름
		JLabel aLabel1 = new JLabel("김밥");
		aLabel1.setBounds(100, 210, 185, 20);
		aLabel1.setFont(new Font1().smallFont());
		mid.add(aLabel1);

		// 음식마다 수정 가격
		JLabel aLabel2 = new JLabel("15,000");
		aLabel2.setBounds(100, 235, 185, 20);
		aLabel2.setFont(new Font1().smallFont());
		mid.add(aLabel2);

		// 알리오올리오
		ImageIcon product2 = new ImageIcon("./src/ProductDB/알리오올리오.jpg");
		JButton itemBtn2 = new JButton(product2);
		itemBtn2.setName("알리오올리오");
		itemBtn2.setSize(150, 150);
		itemBtn2.setLocation(260, 50);
		itemBtn2.setBorderPainted(false);
		itemBtn2.setContentAreaFilled(false);
		itemBtn2.setFocusPainted(false);

		JLabel bLabel1 = new JLabel("알리오올리오");
		bLabel1.setBounds(310, 210, 185, 20);
		bLabel1.setFont(new Font1().smallFont());
		mid.add(bLabel1);

		JLabel bLabel2 = new JLabel("12,000");
		bLabel2.setBounds(310, 235, 185, 20);
		bLabel2.setFont(new Font1().smallFont());
		mid.add(bLabel2);

		// 샌드위치
		ImageIcon product3 = new ImageIcon("./src/ProductDB/샌드위치.jpg");
		JButton itemBtn3 = new JButton(product3);
		itemBtn3.setName("샌드위치");
		itemBtn3.setSize(150, 150);
		itemBtn3.setLocation(50, 300);
		itemBtn3.setBorderPainted(false);
		itemBtn3.setContentAreaFilled(false);
		itemBtn3.setFocusPainted(false);

		JLabel cLabel1 = new JLabel("샌드위치");
		cLabel1.setBounds(100, 460, 185, 20);
		cLabel1.setFont(new Font1().smallFont());
		mid.add(cLabel1);

		JLabel cLabel2 = new JLabel("10,000");
		cLabel2.setBounds(100, 485, 185, 20);
		cLabel2.setFont(new Font1().smallFont());
		mid.add(cLabel2);

		// 소세지야채볶음
		ImageIcon product4 = new ImageIcon("./src/ProductDB/소세지야채볶음.jpg");
		JButton itemBtn4 = new JButton(product4);
		itemBtn4.setName("소세지야채볶음");
		itemBtn4.setSize(150, 150);
		itemBtn4.setLocation(260, 300);
		itemBtn4.setBorderPainted(false);
		itemBtn4.setContentAreaFilled(false);
		itemBtn4.setFocusPainted(false);

		JLabel dLabel1 = new JLabel("소세지야채볶음");
		dLabel1.setFont(new Font1().smallFont());
		dLabel1.setBounds(310, 460, 185, 20);
		mid.add(dLabel1);

		// 음식마다 수정 가격
		JLabel dLabel2 = new JLabel("7,000");
		dLabel2.setBounds(310, 485, 185, 20);
		dLabel2.setFont(new Font1().smallFont());
		mid.add(dLabel2);

		mid.add(itemBtn1);
		mid.add(itemBtn2);
		mid.add(itemBtn3);
		mid.add(itemBtn4);

		add(mid);

		itemBtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose(); // 이전 화면을 지워주고
				Product meatProduct1 = new ProductDAO().searchProduct("김밥"); // 상품 정보를 ProductDAO를 통해 찾고
				FrameBase.getInstance(new DetailedFoodInfo1(m, c, meatProduct1, cart)); // 상세페이지를 연다
			}
		});

		itemBtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose(); // 이전 화면을 지워주고
				Product meatProduct1 = new ProductDAO().searchProduct("알리오올리오"); // 상품 정보를 ProductDAO를 통해 찾고
				FrameBase.getInstance(new DetailedFoodInfo1(m, c, meatProduct1, cart)); // 상세페이지를 연다
			}
		});

		itemBtn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose(); // 이전 화면을 지워주고
				Product meatProduct1 = new ProductDAO().searchProduct("샌드위치"); // 상품 정보를 ProductDAO를 통해 찾고
				FrameBase.getInstance(new DetailedFoodInfo1(m, c, meatProduct1, cart)); // 상세페이지를 연다
			}
		});

		itemBtn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose(); // 이전 화면을 지워주고
				Product meatProduct1 = new ProductDAO().searchProduct("소세지야채볶음"); // 상품 정보를 ProductDAO를 통해 찾고
				FrameBase.getInstance(new DetailedFoodInfo1(m, c, meatProduct1, cart)); // 상세페이지를 연다
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

		// 고객센터
		btnCs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Client_F(m, c, cart));
			}
		});

		// 장바구니
		btnShop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new CartPanel(m, c, cart));
			}
		});

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

		// 마이페이지 구현
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

		// home
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Category1(m, c, cart));
			}
		});
		//back
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Category1(m, c, cart));
			}
		});

	}

}
