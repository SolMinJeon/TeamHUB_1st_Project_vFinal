package frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import CS.Client_F;
import MainPage.CartPanel;
import MainPage.Category1;
import ProductDB.Product;
import ProductDB.ProductDAO;
import UserDB.CartDAO;
import UserDB.CustomerInfo;
import UserDB.MyInfo;

public class MyPage1 extends JPanel {
	
	public static List<Product> searchProduct = new ArrayList<>();
	ArrayList<Product> Product = new ProductDAO().productList;

	private JLabel vvipturningimage;
	private JLabel vipturningimage;
	private JLabel goldturningimage;
	private JLabel silverturningimage;

	public MyPage1(MyInfo m,CustomerInfo c, CartDAO cart) throws IOException {

		MemberInfoRenewal memberList = new MemberInfoRenewal();
		memberList.filterAndWriteToFile();
		// 프레임 사이즈
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(500, 800);

		// JPanel
		// 상단 패널 s
		JPanel top = new JPanel();
		top.setBackground(new Color(255, 255, 255));
		top.setLayout(null);
		top.setBounds(23, 49, 450, 65);
		add(top);
		Font f = new Font("안동엄마까투리", Font.ITALIC, 20);
		// 이미지
		ImageIcon imageLogo = new ImageIcon("./src/logo2.png");
		ImageIcon imageSearch = new ImageIcon("./src/search.png");
		ImageIcon imageCs = new ImageIcon("./src/cs.png");
		ImageIcon imageShop = new ImageIcon("./src/shop.png");

		// 상단 버튼
		JButton btnLogo = new JButton(imageLogo);
		JButton btnSearch = new JButton(imageSearch);
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
		// user 아이콘
		
		ImageIcon imageVvip = new ImageIcon("./src/VVIP.png");
		ImageIcon imageVip = new ImageIcon("./src/VIP.png");
		ImageIcon imageVip1 = new ImageIcon("./src/VIP_1.png");
		ImageIcon imageGold = new ImageIcon("./src/GOLD.png");
		ImageIcon imageSilver = new ImageIcon("./src/SILVER.png");
		
		JButton btnVvip = new JButton(imageVvip);
		JButton btnVip = new JButton(imageVip);
		JButton btnVip1 = new JButton(imageVip1);
		JButton btnGold = new JButton(imageGold);
		JButton btnSilver = new JButton(imageSilver);
		
		 btnVvip.setSize(200, 70);
	     btnVvip.setBorderPainted(false);
	     btnVvip.setContentAreaFilled(false);
	     btnVvip.setFocusPainted(false);
	     btnVvip.setLocation(200, 395);
	      
	     btnVip.setSize(200, 70);
	     btnVip.setBorderPainted(false);
	     btnVip.setContentAreaFilled(false);
	     btnVip.setFocusPainted(false);
	     btnVip.setLocation(200, 395);
	     
	     btnVip1.setSize(200, 70);
	     btnVip1.setBorderPainted(false);
	     btnVip1.setContentAreaFilled(false);
	     btnVip1.setFocusPainted(false);
	     btnVip1.setLocation(200, 395);
	      
	     btnGold.setSize(200, 70);
	     btnGold.setBorderPainted(false);
	     btnGold.setContentAreaFilled(false);
	     btnGold.setFocusPainted(false);
	     btnGold.setLocation(200, 395);
	      
	     btnSilver.setSize(200, 70);
	     btnSilver.setBorderPainted(false);
	     btnSilver.setContentAreaFilled(false);
	     btnSilver.setFocusPainted(false);
	     btnSilver.setLocation(200, 395);

		// 구입금액
		int t = c.getTotal();
		System.out.println(t);

		String s1;
		if (t >= 1000000) {
			s1 = "VVIP";
		} else if (t >= 500000) {
			s1 = "VIP";
		} else if (t >= 100000) {
			s1 = "GOLD";
		} else {
			s1 = "SILVER";
		}

		try (BufferedWriter br = new BufferedWriter(new FileWriter("MemberList.txt", true))) {
			br.write(c.getId() + "/");
			br.write(c.getPw() + "/");
			br.write(c.getName() + "/");
			br.write(c.getBirthday() + "/");
			br.write(c.getSex() + "/");
			br.write(c.getPhone() + "/");
			br.write(c.getTotal() + "/");
			br.write(c.getGrade() + "\n");
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(null, "회원가입이 실패했습니다.");
		}

		if(s1 == "VVIP") {
			ImageIcon vviplogo = new ImageIcon("./src/VvipLogo.png");
			Image ximg1=vviplogo.getImage();
			Image yimg1= ximg1.getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH);
			ImageIcon newimg1=new ImageIcon(yimg1);
			
			JLabel prof1 = new JLabel(newimg1);
			prof1.setBounds(70,150,200,150);
			add(prof1);
		} else if(s1 == "VIP") {
			ImageIcon viplogo = new ImageIcon("./src/VipLogo.png");
			Image ximg2=viplogo.getImage();
			Image yimg2= ximg2.getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH);
			ImageIcon newimg2=new ImageIcon(yimg2);
			
			JLabel prof2 = new JLabel(newimg2);
			prof2.setBounds(70,150,200,150);
			add(prof2);
		} else if(s1 == "GOLD") {
			ImageIcon goldlogo = new ImageIcon("./src/GoldLogo.png");
			Image ximg3=goldlogo.getImage();
			Image yimg3= ximg3.getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH);
			ImageIcon newimg3=new ImageIcon(yimg3);
			
			JLabel prof3 = new JLabel(newimg3);
			prof3.setBounds(70,150,200,150);
			add(prof3);
		} else {
			ImageIcon silverlogo = new ImageIcon("./src/SilverLogo.png");
			Image ximg4=silverlogo.getImage();
			Image yimg4= ximg4.getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH);
			ImageIcon newimg4=new ImageIcon(yimg4);
			
			JLabel prof4 = new JLabel(newimg4);
			prof4.setBounds(70,150,200,150);
			add(prof4);
		}

		JLabel hello = new JLabel(c.getName() + "님");
		hello.setBackground(new Color(229, 243, 197));
		hello.setSize(200, 70);
		hello.setLocation(280, 150);
		hello.setFont(new Font("안동엄마까투리", Font.BOLD, 37));
		add(hello);
		
		JLabel hi = new JLabel("환영합니다");
		hi.setFont(new Font("안동엄마까투리", Font.BOLD, 37));
		hi.setSize(200, 70);
		hi.setLocation(280, 190);
		add(hi);

		JLabel info1 = new JLabel("등급 : ");
		info1.setFont(f);
		info1.setSize(200, 70);
		info1.setLocation(130, 400);
		add(info1);
		
		if(s1 == "VVIP") {
			vvipturningimage = new JLabel(imageVvip);
			vvipturningimage.setLocation(180, 395);
			vvipturningimage.setSize(200, 70);
			add(vvipturningimage);
			(new vvipThread()).start();
		} else if(s1 == "VIP") {
			vipturningimage = new JLabel(imageVip);
			vipturningimage.setLocation(180, 395);
			vipturningimage.setSize(200, 70);
			add(vipturningimage);
			(new vipThread()).start();
		} else if(s1 == "GOLD") {
			goldturningimage = new JLabel(imageGold);
			goldturningimage.setLocation(180, 395);
			goldturningimage.setSize(200, 70);
			add(goldturningimage);
			(new goldThread()).start();
		} else {
			silverturningimage = new JLabel(imageSilver);
			silverturningimage.setLocation(180, 395);
			silverturningimage.setSize(200, 70);
			add(silverturningimage);
			(new silverThread()).start();
		}

		JLabel info2 = new JLabel("총 결제금액 : " + t + "원");
		info2.setFont(f);
		info2.setSize(250, 70);
		info2.setLocation(130, 450);
		add(info2);
		
		JLabel info3 = new JLabel(c.getName() + "님의 혜택");
		info3.setFont(new Font("안동엄마까투리", Font.BOLD, 27));
		info3.setSize(200, 70);
		info3.setLocation(190, 500);
		add(info3);
		
		if(s1 == "VVIP") {
			JLabel vvip = new JLabel("10% 할인 | 매달 생필품 택 3 | 배달비 무료");
			vvip.setFont(new Font("안동엄마까투리", Font.BOLD, 26));
			vvip.setSize(500, 70);
			vvip.setLocation(29, 550);
			add(vvip);
		} else if(s1 == "VIP") {
			JLabel vip = new JLabel("7% 할인 | 매달 생필품 택 1 | 배달비 무료");
			vip.setFont(new Font("안동엄마까투리", Font.BOLD, 27));
			vip.setSize(500, 70);
			vip.setLocation(27, 550);
			add(vip);
		} else if(s1 == "GOLD") {
			JLabel gold = new JLabel("4% 할인 | 매달 생필품 택 1");
			gold.setFont(new Font("안동엄마까투리", Font.BOLD, 29));
			gold.setSize(500, 70);
			gold.setLocation(90, 550);
			add(gold);
		} else {
			JLabel silver = new JLabel("GOLD등급 부터 혜택이 지급됩니다.");
			silver.setFont(new Font("안동엄마까투리", Font.BOLD, 29));
			silver.setSize(500, 70);
			silver.setLocation(50, 550);
			add(silver);
		}

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

		// 홈
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Category1(m,c,cart));
			}
		});

		// back button
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Category1(m,c,cart));
			}
		});
	}

	public void filterAndWriteToFile() throws IOException {
		List<String> lines = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader("MemberList.txt"));
		String line;
		while ((line = reader.readLine()) != null) {
			if (!line.startsWith("w/")) {
				lines.add(line);
			}
		}
		reader.close();

		BufferedWriter writer = new BufferedWriter(new FileWriter("MemberList.txt"));
		for (String newLine : lines) {
			writer.write(newLine + System.lineSeparator());
		}
		writer.close();
	}
	class vvipThread extends Thread {
		public void run() {
			String[] imgs = { "./src/VVIP.png", "./src/VVIP_1.png" };
			while (true) {
				for (int i = 0; i < imgs.length; i++) {
					try {
						Thread.sleep(500);
						ImageIcon icon = new ImageIcon(imgs[i]);
						vvipturningimage.setIcon(icon);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	class vipThread extends Thread {
		public void run() {
			String[] imgs = { "./src/VIP.png", "./src/VIP_1.png" };
			while (true) {
				for (int i = 0; i < imgs.length; i++) {
					try {
						Thread.sleep(500);
						ImageIcon icon = new ImageIcon(imgs[i]);
						vipturningimage.setIcon(icon);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	class goldThread extends Thread {

		public void run() {
			String[] imgs = { "./src/GOLD.png", "./src/GOLD_1.png" };
			while (true) {
				for (int i = 0; i < imgs.length; i++) {
					try {
						Thread.sleep(500);
						ImageIcon icon = new ImageIcon(imgs[i]);
						goldturningimage.setIcon(icon);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	class silverThread extends Thread{
		public void run() {
			String[] imgs = { "./src/SILVER.png", "./src/SILVER_1.png" };
			while (true) {
				for (int i = 0; i < imgs.length; i++) {
					try {
						Thread.sleep(500);
						ImageIcon icon = new ImageIcon(imgs[i]);
						silverturningimage.setIcon(icon);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}

	}
	

}