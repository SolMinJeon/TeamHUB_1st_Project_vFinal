package login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import MainPage.Category1;
import MainPage2.Category2;
import UserDB.CartDAO;
//import UserDB.CartDAO2;
import UserDB.CustomDAO;
import UserDB.CustomerInfo;
import UserDB.MyInfo;
import frame.FrameBase;
import frame.UserInfo;

public class LoginPanel extends JPanel {
	
	public static String lid;
	public static String lname;

	JPanel panel;

	JLabel idLabel, loginturningImage, loginbottomImage;
	JTextField idField;
	JLabel passwordLabel;
	JPasswordField passwordField;
	JButton loginButton;
	JButton registerButton;
	JButton nonmenberButton;

	UserInfo userInfo;

	public LoginPanel() {

		// JPanel 구조
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(500, 800);

		Font font = new Font("안동엄마까투리", Font.BOLD, 30);
		Font font2 = new Font("안동엄마까투리", Font.BOLD, 20);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(50, 50, 400, 700);
		panel.setLayout(null);

		idLabel = new JLabel("LOGIN");
		idLabel.setBounds(160, 310, 200, 25);
		panel.add(idLabel);
		idLabel.setFont(font);

		idField = new JTextField("아이디");
		idField.setBounds(110, 350, 200, 35);
		idField.setForeground(Color.GRAY);

		idField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				String id = idField.getText();
				if (id.equals("아이디")) {
					idField.setForeground(Color.BLACK);
					idField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				String id = idField.getText();
				if (id.equals("")) {
					idField.setText("아이디");
					idField.setForeground(Color.GRAY);
				}
			}
		});

		panel.add(idField);

		passwordField = new JPasswordField("패스워드");
		passwordField.setBounds(idField.getX(), idField.getY() + 50, 200, 35);
		passwordField.setForeground(Color.GRAY);

		passwordField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				String id = passwordField.getText();
				if (id.equals("패스워드")) {
					passwordField.setForeground(Color.BLACK);
					passwordField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				String pw = passwordField.getText();
				if (pw.equals("")) {
					passwordField.setText("패스워드");
					passwordField.setForeground(Color.GRAY);
				}
			}
		});

		panel.add(passwordField);

		RoundedButton loginButton = new RoundedButton("로그인");
		loginButton.setBounds(idField.getX(), 450, 200, 35);
		panel.add(loginButton);
		loginButton.setPreferredSize(new Dimension(30, 30));
		loginButton.setFont(font2);

		RoundedButton registerButton = new RoundedButton("회원가입");
		registerButton.setBounds(idField.getX(), 500, 200, 35);
		panel.add(registerButton);
		registerButton.setPreferredSize(new Dimension(30, 30));
		registerButton.setFont(font2);

		RoundedButton nonmenberButton = new RoundedButton("비회원로그인");
		nonmenberButton.setBounds(idField.getX(), 550, 200, 35);
		panel.add(nonmenberButton);
		nonmenberButton.setPreferredSize(new Dimension(50, 30));
		nonmenberButton.setFont(font2);

		loginButton.setBorderPainted(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setFocusPainted(false);

		registerButton.setBorderPainted(false);
		registerButton.setContentAreaFilled(false);
		registerButton.setFocusPainted(false);

		nonmenberButton.setBorderPainted(false);
		nonmenberButton.setContentAreaFilled(false);
		nonmenberButton.setFocusPainted(false);

		add(panel);

		// 로그인버튼
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText();
				String password = passwordField.getText();

				// ID 빈값 체크
				if (id.trim().equals("")) {
					JOptionPane.showMessageDialog(LoginPanel.this, "ID를 입력해주세요");
					return;
				}

				// PASSWORD 빈값 체크 TODO -> 똑같이
				if (password.trim().equals("")) {
					JOptionPane.showMessageDialog(LoginPanel.this, "비밀번호를 입력해주세요");
					return;
				}

				ArrayList<CustomerInfo> customerArr = new CustomDAO().readFile("");

				boolean chk = false;
				for (int i = 0; i < customerArr.size(); i++) {
					if (id.equals(customerArr.get(i).getId()) && password.equals(customerArr.get(i).getPw())) {
						lid = id;
						lname = customerArr.get(i).getName();

						CartDAO cart = new CartDAO(customerArr.get(i).getId());
						MyInfo m = new MyInfo(customerArr.get(i).getName(), id, customerArr.get(i).getPw(),
								customerArr.get(i).getGrade(), customerArr.get(i).getTotal());

						chk = true;
						JOptionPane.showMessageDialog(LoginPanel.this, "로그인성공");
						FrameBase.getDispose();
						FrameBase.getInstance(new Category1(m, CustomDAO.getCustomList().get(i), cart));
						break;
					}
				}
				if (chk == false) {
					JOptionPane.showMessageDialog(LoginPanel.this, "로그인실패");
				}
			}
		});

		// 회원가입버튼
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new SignupPanel());
			}
		});

		// 비회원로그인버튼
		nonmenberButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Main 패널로 넘어가기
				FrameBase.getDispose();
				FrameBase.getInstance(new Category2());

			}
		});

		ImageIcon label_restaurant = new ImageIcon("./src/LoginLogo1.png");
		loginturningImage = new JLabel(label_restaurant);
		loginturningImage.setLocation(50, 40);
		loginturningImage.setSize(320, 230);
		panel.add(loginturningImage);
		(new LoginPageThread()).start();

		ImageIcon label_restaurant2 = new ImageIcon("./src/bottom1.png");
		loginbottomImage = new JLabel(label_restaurant2);
		loginbottomImage.setLocation(-20, 540);
		loginbottomImage.setSize(450, 230);
		panel.add(loginbottomImage);
		(new LoginPageThread2()).start();
	}
	
	class LoginPageThread extends Thread {
		public void run() {
			String[] imgs = { "./src/LoginLogo1.png", "./src/LoginLogo2.png", "./src/LoginLogo3.png",
					"./src/LoginLogo2.png" };
			while (true) {
				for (int i = 0; i < imgs.length; i++) {
					try {
						Thread.sleep(500);
						ImageIcon icon = new ImageIcon(imgs[i]);
						loginturningImage.setIcon(icon);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	class LoginPageThread2 extends Thread {
		public void run() {
			String[] imgs2 = { "./src/bottom1.png", "./src/bottom2.png", "./src/bottom3.png", "./src/bottom4.png",
					"./src/bottom5.png", "./src/bottom6.png" };
			while (true) {
				for (int i = 0; i < imgs2.length; i++) {
					try {
						Thread.sleep(500);
						ImageIcon icon2 = new ImageIcon(imgs2[i]);
						loginbottomImage.setIcon(icon2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
