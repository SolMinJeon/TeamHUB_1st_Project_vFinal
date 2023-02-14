package MainPage2;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import CS.Client_F;
import ProductDB.Product;
import ProductDB.ProductDAO;
import UserDB.CartDAO;
import UserDB.CustomerInfo;
import UserDB.MyInfo;
import frame.DetailedFoodInfo1;
import frame.FrameBase;

public class CartPanel2 extends JPanel {

	public static List<Product> searchProduct = new ArrayList<>();
	ArrayList<Product> Product = new ProductDAO().productList;
	ArrayList<JCheckBox> box = new ArrayList<>();
	int panelCount = 0;
	JPanel cartViewPanel;
	ArrayList<JPanel> pgNum = new ArrayList<>();
	JPanel pa;

	int partMoney = 0;
	int payMoney = 0;
	int count = 0;
	int sel;
	static int bb = 0;
	JCheckBox select;
	CustomerInfo c;
	MyInfo m;

	public CartPanel2() {
	}

	public CartPanel2(MyInfo m, CustomerInfo c, CartDAO cart) {

		this.c = c;

		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(500, 800);

		box = new ArrayList<>();

		ArrayList<ImageIcon> itemImg = new ArrayList<>();

		pa = new JPanel();
		pa.setBounds(0, 0, 0, 0);
		pa.setLayout(null);

		itemImg.clear();

		for (int i = 0; i < CartDAO.cartMap.get(c.getId()).size(); i++) {
			itemImg.add(new ImageIcon(CartDAO.cartMap.get(c.getId()).get(i).getProductImage()));
		}

		if ((CartDAO.cartMap.get(c.getId()).size() > 0)) {

			panelCount = (int) Math
					.ceil(Double.parseDouble(String.valueOf(CartDAO.cartMap.get(c.getId()).size())) / 4.0);

			for (int i = 0; i < panelCount; i++) {
				pgNum.add(pa);
				JPanel top = new JPanel();
				top.setBackground(new Color(255, 255, 255));
				top.setLayout(null);
				pgNum.get(i).add(top);
			}

			pgNum.get(0).setVisible(true);

			for (int j = 0; j < panelCount; j++) {
				JButton pageBtn = new JButton(String.valueOf(j + 1));

				if (Integer.parseInt(pageBtn.getText()) == panelCount) {

					pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).setBounds(30, 130, 430, 500);
					pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).setBackground(Color.WHITE);
					pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).setLayout(null);

					for (int i = (Integer.parseInt(pageBtn.getText()) - 1) * 4; i < (CartDAO.cartMap.get(c.getId())
							.size()); i++) {

						Image image = itemImg.get(i).getImage();
						Image newImg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
						// 이미지
						ImageIcon newIcon = new ImageIcon(newImg);
						JLabel img = new JLabel(newIcon);
						img.setBounds(0, 0 + ((i % 4) * 110), 200, 100);
						pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(img);

						box.add(new JCheckBox());
						box.get(i).setBounds(0, 0 + ((i % 4) * 110), 20, 20);
						box.get(i).setBackground(Color.WHITE);
						pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(box.get(i));

						box.get(i).addItemListener(new ItemListener() {
							@Override
							public void itemStateChanged(ItemEvent e) {
								if (e.getStateChange() == ItemEvent.SELECTED) {
									sel = 1;
								} else {
									sel = -1;
								}
								for (int k = 0; k < box.size(); k++) {
									if (e.getItem() == box.get(k)) {
										partMoney += sel * CartDAO.cartMap.get(c.getId()).get(k).getCnt()
												* CartDAO.cartMap.get(c.getId()).get(k).getPrice();
									}
								}
							}
						});

						JLabel nameInfo = new JLabel("상품명 : " + CartDAO.cartMap.get(c.getId()).get(i).getName()); // 상품명
						JLabel itemInfo = new JLabel("상품 가격 : " + CartDAO.cartMap.get(c.getId()).get(i).getPrice()
								* CartDAO.cartMap.get(c.getId()).get(i).getCnt()
								);
						JLabel cntInfo = new JLabel("수량 ");

						JTextField cntText = new JTextField();

						nameInfo.setBounds(175, 0 + ((i % 4) * 110), 200, 15);
						itemInfo.setBounds(175, 25 + ((i % 4) * 110), 200, 15);
						cntInfo.setBounds(175, 50 + ((i % 4) * 110), 100, 15);
						cntText.setBounds(175, 75 + ((i % 4) * 110), 60, 20);

						itemInfo.putClientProperty("id", CartDAO.cartMap.get(c.getId()).get(i).getId());

						cntText.setText(Integer.toString(CartDAO.cartMap.get(c.getId()).get(i).getCnt()));

						pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(nameInfo);
						pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(itemInfo);
						pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(cntInfo);
						pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(cntText);

						JButton removeBtn = new JButton("삭제");

						removeBtn.setBounds(350, 50 + ((i % 4) * 115), 65, 50);
						removeBtn.setBackground(new Color(255, 255, 245));
						removeBtn.putClientProperty("id", CartDAO.cartMap.get(c.getId()).get(i).getId());
						pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(removeBtn);

						removeBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								int id = Integer.parseInt(String.valueOf((removeBtn.getClientProperty("id"))));
								if (cart.remove(id, c.getId())) {
									FrameBase.getDispose();
									FrameBase.getInstance(new CartPanel2(m,c, cart));
								}
							}
						});

						JButton editBtn = new JButton("변경");
						editBtn.setBackground(new Color(255, 255, 245));

						editBtn.setBounds(350, 0 + ((i % 4) * 115), 65, 50);
						editBtn.putClientProperty("id", CartDAO.cartMap.get(c.getId()).get(i).getId());
						pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(editBtn);

						editBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								int id = Integer.parseInt(String.valueOf((editBtn.getClientProperty("id"))));
								if (cart.change(id, Integer.parseInt(cntText.getText()), c.getId())) {
									FrameBase.getDispose();
									FrameBase.getInstance(new CartPanel2(m,c, cart));
								}
							}
						});
					}
				}

				else {
					pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).setBounds(0, 0, 600, 560);
					pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).setBackground(Color.BLACK);
					pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).setLayout(null);

					for (int i = (Integer.parseInt(pageBtn.getText()) - 1) * 4; i < Integer.parseInt(pageBtn.getText())
							* 4; i++) {

						Image image = itemImg.get(i).getImage();
						Image newImg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
						// 이미지
						ImageIcon newIcon = new ImageIcon(newImg);
						JLabel img = new JLabel(newIcon);
						img.setBounds(50, 90 + ((i % 4) * 110), 200, 100);
						pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(img);

						box.add(new JCheckBox());
						box.get(i).setBounds(16, 127 + ((i % 4) * 110), 20, 20);
						box.get(i).setBackground(Color.WHITE);
						pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(box.get(i));

						box.get(i).addItemListener(new ItemListener() {
							@Override
							public void itemStateChanged(ItemEvent e) {
								if (e.getStateChange() == ItemEvent.SELECTED) {
									sel = 1;
								} else {
									sel = -1;
								}
								for (int k = 0; k < box.size(); k++) {
									if (e.getItem() == box.get(k)) {
										partMoney += sel * CartDAO.cartMap.get(c.getId()).get(k).getCnt()
												* CartDAO.cartMap.get(c.getId()).get(k).getPrice();
									}
								}
							}
						});
						
						JLabel nameInfo = new JLabel("상품명 : " + CartDAO.cartMap.get(c.getId()).get(i).getName()); // 상품명
						JLabel itemInfo = new JLabel("상품 가격 : " + CartDAO.cartMap.get(c.getId()).get(i).getPrice());
						JLabel cntInfo = new JLabel("수량 ");

						JTextField cntText = new JTextField();

						nameInfo.setBounds(300, 90 + ((i % 4) * 110), 200, 15);
						itemInfo.setBounds(300, 115 + ((i % 4) * 110), 200, 15);
						cntInfo.setBounds(300, 140 + ((i % 4) * 110), 100, 15);
						cntText.setBounds(350, 135 + ((i % 4) * 110), 60, 20);

						itemInfo.putClientProperty("id", CartDAO.cartMap.get(c.getId()).get(i).getId());

						cntText.setText(Integer.toString(CartDAO.cartMap.get(c.getId()).get(i).getCnt()));

						pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(nameInfo);
						pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(itemInfo);
						pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(cntInfo);
						pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(cntText);

						JButton removeBtn = new JButton("삭제");

						removeBtn.setBounds(500, 102 + ((i % 4) * 115), 65, 50);
						removeBtn.putClientProperty("id", CartDAO.cartMap.get(c.getId()).get(i).getId());
						pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(removeBtn);

						removeBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								int id = Integer.parseInt(String.valueOf((removeBtn.getClientProperty("id"))));
								if (cart.remove(id, c.getId())) {
									FrameBase.getDispose();
									FrameBase.getInstance(new CartPanel2(m,c, cart));
								}
							}
						});

						JButton editBtn = new JButton("변경");
						editBtn.setBackground(new Color(245, 245, 245));
						editBtn.setBounds(430, 102 + ((i % 4) * 115), 65, 50);
						editBtn.putClientProperty("id", CartDAO.cartMap.get(c.getId()).get(i).getId());
						pgNum.get(Integer.parseInt(pageBtn.getText())).add(editBtn);

						editBtn.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {

								int id = Integer.parseInt(String.valueOf((editBtn.getClientProperty("id"))));
								if (cart.change(id, Integer.parseInt(cntText.getText()), c.getId())) {
									FrameBase.getDispose();
									FrameBase.getInstance(new CartPanel2(m,c, cart));
								}
							}
						});

					}
				}

				pageBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						for (int j = 0; j < pgNum.size(); j++) {
							if (Integer.parseInt(pageBtn.getText()) - 1 == j) {
								pgNum.get(j).setVisible(true);

							} else {
								pgNum.get(j).setVisible(false);
							}
						}
					}
				});

				pa.add(pageBtn);
			}

		}
		for (int i = 0; i < pgNum.size(); i++) {
			add(pgNum.get(i));
		}

		add(pa);
		// 총 결제 금액
		payMoney = cart.totalMoney(c.getId());

		JButton payBtn = new JButton("PAY");
		payBtn.setBackground(new Color(255,0,255));
		payBtn.setBounds(0,500, 30, 30);
		add(payBtn);
		
		payBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String grade = c.gradeCheck((c.getTotal()));

				if (payMoney == 0) {
					JOptionPane.showMessageDialog(null, "구매할 수 있는 상품이 없습니다.");
				} else {
					int result = JOptionPane.showConfirmDialog(null,
							"총 " + c.salePrice(grade, payMoney) + "원을 결제 하시겠습니까?", "Confirm",
							JOptionPane.YES_NO_CANCEL_OPTION);
					if (result == JOptionPane.CLOSED_OPTION) {
						JOptionPane.showMessageDialog(null, "결제를 취소하셨습니다.");
					} else if (result == JOptionPane.YES_OPTION) {
						// 나중에 로그인 유저 결제금액 올려주는 method 추가
						JOptionPane.showMessageDialog(null, c.salePrice(grade, payMoney) + "원을 결제하셨습니다.");

						for (int i = 0; i < CartDAO.cartMap.get(c.getId()).size(); i++) {
							CartDAO.cartMap.get(c.getId()).clear();
						}

						FrameBase.getDispose();
						FrameBase.getInstance(new Category2());
						c.setTotal(c.salePrice(grade, payMoney));

					} else {
						JOptionPane.showMessageDialog(null, "결제를 취소하셨습니다.");
					}

				}

			}
		});

		JPanel top = new JPanel();
		top.setBackground(new Color(255, 255, 255));
		top.setLayout(null);
		top.setBounds(23, 49, 450, 75);

		ImageIcon imageLogo = new ImageIcon("./src/Logo2.png");
		JButton btnLogo = new JButton(imageLogo);
		btnLogo.setSize(150, 90);
		btnLogo.setBorderPainted(false);
		btnLogo.setContentAreaFilled(false);
		btnLogo.setFocusPainted(false);
		btnLogo.setLocation(0, -15);
		top.add(btnLogo);

		ImageIcon imageShop = new ImageIcon("./src/shop.png");
		JButton btnShop = new JButton(imageShop);
		btnShop.setSize(40, 40);
		btnShop.setBorderPainted(false);
		btnShop.setContentAreaFilled(false);
		btnShop.setFocusPainted(false);
		btnShop.setLocation(348, 0);
		top.add(btnShop);

		ImageIcon imageCs = new ImageIcon("./src/cs.png");
		JButton btnCs = new JButton(imageCs);
		btnCs.setSize(40, 40);
		btnCs.setBorderPainted(false);
		btnCs.setContentAreaFilled(false);
		btnCs.setFocusPainted(false);
		btnCs.setLocation(298, 0);
		top.add(btnCs);

		ImageIcon imageSearch = new ImageIcon("./src/search.png");
		JButton btnSearch = new JButton(imageSearch);
		btnSearch.setSize(40, 40);
		btnSearch.setBorderPainted(false);
		btnSearch.setContentAreaFilled(false);
		btnSearch.setFocusPainted(false);
		btnSearch.setLocation(398, 0);
		top.add(btnSearch);
		add(top);

		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(255, 255, 255));
		bottom.setLayout(null);
		bottom.setBounds(26, 700, 450, 55);

		ImageIcon imageHome1 = new ImageIcon("./src/home1.png");
		ImageIcon imageHome2 = new ImageIcon("./src/home2.png");
		ImageIcon imageBack1 = new ImageIcon("./src/back1.png");
		ImageIcon imageBack2 = new ImageIcon("./src/back2.png");

		JButton btnHome = new JButton(imageHome1);
		JButton btnBack = new JButton(imageBack1);

		ImageIcon imageMy1 = new ImageIcon("./src/my1.png");
		ImageIcon imageMy2 = new ImageIcon("./src/my2.png");
		JButton btnMy = new JButton(imageMy1);
		btnMy = new JButton(imageMy1);
		btnMy.setRolloverIcon(imageMy2);
		btnMy.setSize(50, 50);
		btnMy.setBorderPainted(false);
		btnMy.setContentAreaFilled(false);
		btnMy.setFocusPainted(false);
		btnMy.setLocation(75, 10);

		btnHome = new JButton(imageHome1);
		btnHome.setRolloverIcon(imageHome2);
		btnHome.setSize(50, 50);
		btnHome.setBorderPainted(false);
		btnHome.setContentAreaFilled(false);
		btnHome.setFocusPainted(false);
		btnHome.setLocation(210, 10);

		btnBack = new JButton(imageBack1);
		btnBack.setRolloverIcon(imageBack2);
		btnBack.setSize(50, 50);
		btnBack.setBorderPainted(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setFocusPainted(false);
		btnBack.setLocation(335, 10);

		bottom.add(btnMy);
		bottom.add(btnHome);
		bottom.add(btnBack);
		add(bottom);

		btnCs.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Client_F(m,c,cart));
			}
		});

		// 장바구니
		btnShop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new CartPanel2(m,c, cart));
			}
		});

		// 검색창
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchProduct.clear();
				String itemName = JOptionPane.showInputDialog("찾으실 상품을 입력하시오");
				int a = 0;
				System.out.println(itemName);
				if (!(itemName == null)) {
					if (!itemName.equals("")) {
						for (int i = 0; i < ProductDAO.productList.size(); i++) {
							if (ProductDAO.productList.get(i).getName().toLowerCase()
									.contains(itemName.toLowerCase())) {
								searchProduct.add(ProductDAO.productList.get(i));
								Product meatProduct1 = new ProductDAO().searchProduct(itemName); // 상품 정보를 ProductDAO를
																									// 통해 찾고
								FrameBase.getDispose();
								FrameBase.getInstance(new DetailedFoodInfo1(m,c, meatProduct1, cart)); // 상세페이지를 연다
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

		// 마이페이지
		btnMy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,"비회원입니다.");
			}
		});

		// home
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Category2());
			}
		});

		// back
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Category2());
			}
		});

	}

}
