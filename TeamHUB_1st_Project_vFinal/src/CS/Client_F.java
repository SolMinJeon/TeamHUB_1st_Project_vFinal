package CS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MainPage.CartPanel;
import MainPage.Category1;
import ProductDB.Product;
import ProductDB.ProductDAO;
import UserDB.CartDAO;
import UserDB.CustomerInfo;
import UserDB.MyInfo;
import frame.DetailedFoodInfo1;
import frame.FrameBase;
import frame.MyPage1;
import login.LoginPanel;


public class Client_F extends JPanel implements ActionListener, KeyListener {

	public static List<Product> searchProduct = new ArrayList<>();
	ArrayList<Product> Product = new ProductDAO().productList;
	private JPanel contentPane;

	String NickName = LoginPanel.lname;
	String IPAddress;
	int Port;
	Client_Back CB = new Client_Back();
	JTextField Chat = new JTextField("입력", 30);

	TextArea ChatList = new TextArea("",0, 0,TextArea.SCROLLBARS_VERTICAL_ONLY);
	
	TextArea UserList = new TextArea(0, 0);
	JButton Enter = new JButton("전송");

	public Client_F(MyInfo m,CustomerInfo c, CartDAO cart) {

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
		mid.setBounds(40, 150, 430, 550);
		contentPane = new JPanel();
		ChatList.setPreferredSize(new Dimension(400, 450));
		ChatList.setEditable(false);
		contentPane.add(ChatList);
		contentPane.setBackground(Color.WHITE);
		contentPane.setSize(430, 450);
		
		mid.add(contentPane);
		mid.add(Chat);
		mid.add(Enter);
		
		add(mid);

		String NickName;
		String IPAddress;
		int Port;
		JButton Enter = new JButton("전송");

		NickName = LoginPanel.lname;
		IPAddress = "172.16.10.42";
		Port = 8080;

		Chat.addKeyListener(this);
		Enter.addActionListener(this);

		CB.setGui(this);
		CB.getUserInfo(NickName, IPAddress, Port);
		CB.start(); // 채팅창이 켜짐과 동시에 접속을 실행해줍니다.

		// 하단 버튼 이미지
		// 하단 패널 + 하단 버튼
		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(255, 255, 255));
		bottom.setLayout(null);
		bottom.setBounds(26, 700, 450, 55);
		
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

		// 고객센터
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
				FrameBase.getInstance(new CartPanel(m, c, cart));
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

		// 마이페이지
		btnMy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				try {
					FrameBase.getInstance(new MyPage1(m,c,cart));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		// 리스트(카테고리)로 넘어감
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Category1(m,c,cart));
			}
		});

		// 리스트(카테고리)로 넘어감
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Category1(m,c,cart));
			}
		});

	}
	
	//추가
	public Client_F() {
	}
	
	public void AppendMessage(String Message) {
		ChatList.append(Message);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// 키보드 엔터키를 누르고, 입력값이 1이상일때만 전송되도록 합니다.
		String Message = Chat.getText().trim();
		if (e.getKeyCode() == KeyEvent.VK_ENTER && Message.length() > 0) {
			CB.Transmit(NickName + " : " + Message + "\n");
			Chat.setText(null);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
