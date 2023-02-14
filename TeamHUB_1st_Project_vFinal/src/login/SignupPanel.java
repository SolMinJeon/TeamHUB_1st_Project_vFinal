package login;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import UserDB.CustomDAO;
import UserDB.CustomerInfo;
import frame.FrameBase;

public class SignupPanel extends JPanel {

	JPanel panel;

	JTextField idTf, nameTf, phoneTf;
	JPasswordField passwordTf;
	JLabel titleLabel, idLabel, passLabel, nameLabel, birthLabel, genderLabel, phoneLabel, loginbottomImage;
	JComboBox<String> yearComboBox;
	JComboBox<String> monthComboBox;
	JComboBox<String> dayComboBox;

	JRadioButton menButton;
	JRadioButton womenButton;

	JButton backButton;
	JButton registerButton;
	JButton checkButton;

	String birthday;
	String year = "", month = "", day = "";
	String id = "", password = "", name = "", gender = "", phone = "", grade = "";
	int total=0;

	public SignupPanel() {

		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(500, 800);

		Font font = new Font("안동엄마까투리", Font.BOLD, 30);
		Font font2 = new Font("안동엄마까투리", Font.BOLD, 18);
		Font font3 = new Font("안동엄마까투리", Font.BOLD, 15);
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(50, 50, 400, 700);
		panel.setLayout(null);

		titleLabel = new JLabel("회원가입");
		titleLabel.setFont(font);
		titleLabel.setBounds(120, 50, 200, 30);
		panel.add(titleLabel);

		idLabel = new JLabel("아이디");
		idLabel.setFont(font2);
		idLabel.setBounds(30, 150, 200, 25);
		panel.add(idLabel);

		passLabel = new JLabel("비밀번호");
		passLabel.setFont(font2);
		passLabel.setBounds(30, 200, 80, 25);
		panel.add(passLabel);

		nameLabel = new JLabel("이름");
		nameLabel.setFont(font2);
		nameLabel.setBounds(30, 250, 80, 25);
		panel.add(nameLabel);

		birthLabel = new JLabel("생년월일");
		birthLabel.setFont(font2);
		birthLabel.setBounds(30, 300, 80, 25);
		panel.add(birthLabel);

		genderLabel = new JLabel("성별");
		genderLabel.setFont(font2);
		genderLabel.setBounds(30, 350, 80, 25);
		panel.add(genderLabel);

		phoneLabel = new JLabel("휴대전화");
		phoneLabel.setFont(font2);
		phoneLabel.setBounds(30, 400, 80, 25);
		panel.add(phoneLabel);

		idTf = new JTextField(30);
		passwordTf = new JPasswordField(30);
		nameTf = new JTextField(30);
		phoneTf = new JTextField(30);

		idTf.setBounds(150, 150, 150, 25);
		passwordTf.setBounds(150, 200, 150, 25);
		nameTf.setBounds(150, 250, 150, 25);
		phoneTf.setBounds(150, 400, 150, 25);

		panel.add(idTf);
		panel.add(passwordTf);
		panel.add(nameTf);
		panel.add(phoneTf);

		yearComboBox = new JComboBox<String>(new String[] { "1970", "1971", "1972", "1973", "1974", "1975", "1976",
				"1977", "1978", "1979", "1980", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988",
				"1989", "1990", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000",
				"2001", "2002", "2003" });

		monthComboBox = new JComboBox<String>(
				new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" });

		dayComboBox = new JComboBox<String>(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
				"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
				"28", "29", "30", "31" });

		menButton = new JRadioButton("남성");
		womenButton = new JRadioButton("여성");

		menButton.setBounds(150, 350, 100, 30);
		menButton.setFont(font2);
		womenButton.setBounds(250, 350, 100, 30);
		womenButton.setFont(font2);

		panel.add(menButton);
		panel.add(womenButton);

		ButtonGroup genderGroup = new ButtonGroup();
		genderGroup.add(menButton);
		genderGroup.add(womenButton);

		yearComboBox.setBounds(150, 300, 70, 30);
		monthComboBox.setBounds(225, 300, 70, 30);
		dayComboBox.setBounds(300, 300, 70, 30);

		panel.add(yearComboBox);
		panel.add(monthComboBox);
		panel.add(dayComboBox);

		// 중복검사버튼
		checkButton = new RoundedButton("중복");
		checkButton.setBounds(310, idLabel.getY(), 60, 25);
		checkButton.setFont(font3);
		panel.add(checkButton);

		// 회원가입버튼
		registerButton = new RoundedButton("회원가입");
		registerButton.setBounds(75, phoneLabel.getY() + 70, 100, 40);
		registerButton.setFont(font3);
		panel.add(registerButton);

		// 뒤로버튼
		backButton = new RoundedButton("뒤로가기");
		backButton.setBounds(225, phoneLabel.getY() + 70, 100, 40);
		backButton.setFont(font3);
		panel.add(backButton);
		panel.setVisible(true);

		checkButton.setBorderPainted(false);
		checkButton.setContentAreaFilled(false);
		checkButton.setFocusPainted(false);

		registerButton.setBorderPainted(false);
		registerButton.setContentAreaFilled(false);
		registerButton.setFocusPainted(false);

		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);

		// 하단 이미지
		ImageIcon label_restaurant2 = new ImageIcon("./src/bottom1.png");
		loginbottomImage = new JLabel(label_restaurant2);
		loginbottomImage.setLocation(-20, 540);
		loginbottomImage.setSize(450, 230);
		panel.add(loginbottomImage);
		(new LoginPageThread2()).start();

		add(panel);

		// ----------------------------------년
		yearComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == yearComboBox) {
					JComboBox yearBox = (JComboBox) e.getSource();
					year = (String) yearBox.getSelectedItem();
				}
			}
		});

		// -----------------------------------월
		monthComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == monthComboBox) {
					JComboBox monthBox = (JComboBox) e.getSource();
					month = (String) monthBox.getSelectedItem();

				}
			}
		});

		// ---------------------------------일
		dayComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == dayComboBox) {
					JComboBox dayBox = (JComboBox) e.getSource();
					day = (String) dayBox.getSelectedItem();
				}
			}
		});

		// -------------------------------성별
		menButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gender = e.getActionCommand();
			}
		});
		
		womenButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gender = e.getActionCommand();
			}
		});

		// ----------------------------------
		checkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Boolean bChk = true;
				// 아이디중복체크버튼
				id = idTf.getText().trim();

				// ID 빈값 체크
				if (id.trim().equals("")) {
					JOptionPane.showMessageDialog(SignupPanel.this, "ID를 입력해주세요");
					return;
				}

				ArrayList<CustomerInfo> customerArr = new CustomDAO().readFile("");

				for (int i = 0; i < customerArr.size(); i++) {
					bChk = CustomDAO.validateId(id);
					if (bChk == false) {
						JOptionPane.showMessageDialog(SignupPanel.this, "사용이 불가합니다. 다른 아이디를 입력해주세요");
						break;
					}
				}

				if (bChk == true) {
					JOptionPane.showMessageDialog(SignupPanel.this, "사용가능한 아이디입니다.");
				}
			}
		});

		// ---------------------------회원가입버튼클릭하면
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // MemberList에 입력하도록
				Boolean bChk = true;

				// year.화면에선택한 값을.그냥 가져온다.
				year = yearComboBox.getSelectedItem().toString().trim();
				month = monthComboBox.getSelectedItem().toString().trim();
				day = dayComboBox.getSelectedItem().toString().trim();
				id = idTf.getText().trim();
				password = new String(passwordTf.getPassword()).trim();
				birthday = new String(year + "-" + month + "-" + day);
				name = nameTf.getText().trim();
				phone = phoneTf.getText().trim();
				gender = menButton.isSelected() ? "남성" : "여성";
				total = 0;
				grade = "SILVER";

				// 전체입력 오류
				// ArrayList로 데이터 저장하기
				if (!id.isEmpty() && (!password.isEmpty()) && !birthday.isEmpty() && !name.isEmpty() && !phone.isEmpty()
						&& !gender.isEmpty()) {

					// 한번더 중복체크
					ArrayList<CustomerInfo> customerArr = new CustomDAO().readFile("");

					for (int i = 0; i < customerArr.size(); i++) {
						bChk = CustomDAO.validateId(id);
						if (bChk == false) {
							JOptionPane.showMessageDialog(SignupPanel.this, "사용이 불가합니다. 다른 아이디를 입력해주세요");
							break;
						}
					}

					if (bChk == false) {
						return;
					}

					try (BufferedWriter br = new BufferedWriter(new FileWriter("MemberList.txt", true))) {
						br.write(id + "/");
						br.write(password + "/");
						br.write(name + "/");
						br.write(birthday + "/");
						br.write(gender + "/");
						br.write(phone + "/");
						br.write(total + "/");
						br.write(grade + "\n");
					} catch (Exception ex) {
						System.out.println(ex.getMessage());
						JOptionPane.showMessageDialog(null, "회원가입이 실패했습니다.");
					}

					JOptionPane.showMessageDialog(null, "회원가입에 성공했습니다.");

					// 메인화면으로 돌아가기
					FrameBase.getDispose();
					FrameBase.getInstance(new LoginPanel());

				} else {
					JOptionPane.showMessageDialog(null, "회원가입이 실패했습니다.");
				}
			}
		});// ArrayList로 정보 저장하기

		// ----------------------------뒤로버튼 누르면 메인으로 돌아가기
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginPanel lf = new LoginPanel();
				FrameBase.getDispose();
				FrameBase.getInstance(new LoginPanel());
			}
		});

		// --------------------
		monthComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateDayComboBox();
			}
		});
		
		add(panel);
	}

	private void updateDayComboBox() {
		dayComboBox.removeAllItems();
		String mi = (String) monthComboBox.getSelectedItem();

		if (mi.equals("01") || mi.equals("03") || mi.equals("05") || mi.equals("07") || mi.equals("08")
				|| mi.equals("10") || mi.equals("12")) {
			dayComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07",
					"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
					"24", "25", "26", "27", "28", "29", "30", "31" }));
		} else if (mi.equals("04") || mi.equals("06") || mi.equals("09") || mi.equals("11")) {
			dayComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07",
					"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
					"24", "25", "26", "27", "28", "29", "30" }));
		} else if (mi.equals("02")) {
			dayComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "01", "02", "03", "04", "05", "06", "07",
					"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23",
					"24", "25", "26", "27", "28", "29" }));
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
