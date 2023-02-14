package MainPage2;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import frame.FrameBase;
import login.LoginPanel;

public class Category2 extends JPanel {

	JLabel turningImageLable, lableTitle, lable1, lable2, lable3;

	public Category2() {
		
		String[] wea = { "SUN", "HOT", "RAIN" };
		
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		setSize(500, 800);

		ImageIcon image1 = new ImageIcon("./src/뭐해먹지.png");// 이미지를 불러오고
		Image img1 = image1.getImage(); // image로 형태 변환
		Image Img1 = img1.getScaledInstance(200, 50, img1.SCALE_SMOOTH); // 이미지 사이즈 변환
		ImageIcon fix1 = new ImageIcon(Img1); // 다시 icon화
		lable1 = new JLabel(fix1);
		lable1.setLocation(150, 45);
		lable1.setSize(200, 100);

		add(lable1);

		// 이미지 삽입
		ImageIcon label_restaurant = new ImageIcon("./src/떡볶이.png");
		turningImageLable = new JLabel(label_restaurant);
		turningImageLable.setLocation(100, lable1.getY() + 80);
		turningImageLable.setSize(300, 300);

		add(turningImageLable);
		(new homeImageThread()).start(); // 이미지 스타트

		// 테마별 이미지
		ImageIcon image2 = new ImageIcon("./src/고민고민.png");// 이미지를 불러오고
		Image img2 = image2.getImage(); // image로 형태 변환
		Image Img2 = img2.getScaledInstance(200, 50, img2.SCALE_SMOOTH); // 이미지 사이즈 변환
		ImageIcon fix2 = new ImageIcon(Img2); // 다시 icon화
		lable2 = new JLabel(fix2);
		lable2.setLocation(150, turningImageLable.getY() + 290);
		lable2.setSize(200, 100);
		add(lable2);
		
		// ---------------------- 줄--------------------------
		ImageIcon image3 = new ImageIcon("./src/줄.png");
		lable3 = new JLabel(image3);
		lable3.setLocation(turningImageLable.getX() - 30, lable2.getY() + 90);
		lable3.setSize(375, 3);
		add(lable3);

		// 테마 버튼-------------------------------------------------
		ImageIcon imageWeather = new ImageIcon("./src/날씨.png");
		ImageIcon image4 = new ImageIcon("./src/고기.png");// 이미지를 불러오고
		Image img4 = image4.getImage(); // image로 형태 변환
		Image Img4 = img4.getScaledInstance(75, 75, img4.SCALE_SMOOTH); // 이미지 사이즈 변환
		ImageIcon fix4 = new ImageIcon(Img4); // 다시 icon화
		ImageIcon image5 = new ImageIcon("./src/고기2_2.png");// 이미지를 불러오고
		Image img5 = image5.getImage(); // image로 형태 변환
		Image Img5 = img5.getScaledInstance(75, 75, img5.SCALE_SMOOTH); // 이미지 사이즈 변환
		ImageIcon fix5 = new ImageIcon(Img5); // 다시 icon화

		ImageIcon image6 = new ImageIcon("./src/다이어트.png");// 이미지를 불러오고
		Image img6 = image6.getImage(); // image로 형태 변환
		Image Img6 = img6.getScaledInstance(75, 75, img4.SCALE_SMOOTH); // 이미지 사이즈 변환
		ImageIcon fix6 = new ImageIcon(Img6); // 다시 icon화
		ImageIcon image7 = new ImageIcon("./src/다이어트1.png");// 이미지를 불러오고
		Image img7 = image7.getImage(); // image로 형태 변환
		Image Img7 = img7.getScaledInstance(75, 75, img5.SCALE_SMOOTH); // 이미지 사이즈 변환
		ImageIcon fix7 = new ImageIcon(Img7); // 다시 icon화

		ImageIcon imageVegan = new ImageIcon("./src/비건.png");
		ImageIcon imageVegan2 = new ImageIcon("./src/비건_1.png");

		ImageIcon imageSeafood = new ImageIcon("./src/해산물.png");
		ImageIcon imageSeafood2 = new ImageIcon("./src/해산물2.png");

		ImageIcon imageCalendar = new ImageIcon("./src/달력.png");

		JButton buttonWeather = new JButton(imageWeather);
		ImageIcon sun = new ImageIcon("./src/sun.jpg");
		ImageIcon rain = new ImageIcon("./src/rain.jpg");
		ImageIcon hot = new ImageIcon("./src/hot.jpg");
		
		int ran = (int) (Math.random() * 3 + 1);
		switch (ran) {
		case 1: {
			buttonWeather.setRolloverIcon(sun);
			break;
		}
		case 2: {
			buttonWeather.setRolloverIcon(hot);
			break;
		}
		default:
			buttonWeather.setRolloverIcon(rain);
		}

		JButton buttonMeat = new JButton(fix4);
		buttonMeat.setRolloverIcon(fix5);

		JButton buttonVegan = new JButton(imageVegan);
		buttonVegan.setRolloverIcon(imageVegan2);

		JButton buttonSeafood = new JButton(imageSeafood);
		buttonSeafood.setRolloverIcon(imageSeafood2);

		JButton buttonDiet = new JButton(fix6);
		buttonDiet.setRolloverIcon(fix7);

		JButton buttonCalendar = new JButton(imageCalendar);

		buttonWeather.setSize(75, 75);
		buttonWeather.setBorderPainted(false);
		buttonWeather.setContentAreaFilled(false);
		buttonWeather.setFocusPainted(false);
		buttonWeather.setLocation(75, 515);
		add(buttonWeather);

		buttonMeat.setSize(75, 75);
		buttonMeat.setBorderPainted(false);
		buttonMeat.setContentAreaFilled(false);
		buttonMeat.setFocusPainted(false);
		buttonMeat.setLocation(buttonWeather.getX() + 135, buttonWeather.getY());
		add(buttonMeat);

		buttonVegan.setSize(100, 75);
		buttonVegan.setBorderPainted(false);
		buttonVegan.setContentAreaFilled(false);
		buttonVegan.setFocusPainted(false);
		buttonVegan.setLocation(buttonMeat.getX() + 130, buttonWeather.getY());
		add(buttonVegan);

		buttonSeafood.setSize(75, 75);
		buttonSeafood.setBorderPainted(false);
		buttonSeafood.setContentAreaFilled(false);
		buttonSeafood.setFocusPainted(false);
		buttonSeafood.setLocation(buttonWeather.getX(), buttonWeather.getY() + 100);
		add(buttonSeafood);

		buttonDiet.setSize(100, 75);
		buttonDiet.setBorderPainted(false);
		buttonDiet.setContentAreaFilled(false);
		buttonDiet.setFocusPainted(false);
		buttonDiet.setLocation(buttonSeafood.getX() + 135, buttonWeather.getY() + 100);
		add(buttonDiet);

		buttonCalendar.setSize(75, 75);
		buttonCalendar.setBorderPainted(false);
		buttonCalendar.setContentAreaFilled(false);
		buttonCalendar.setFocusPainted(false);
		buttonCalendar.setLocation(buttonDiet.getX() + 150, buttonWeather.getY() + 100);
		add(buttonCalendar);
		// 버튼 활성화
		buttonWeather.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(wea[ran-1]);
				switch (ran) {
				case 1: {
					FrameBase.getDispose();
					FrameBase.getInstance(new Sun2());
					break;
				}
				case 2: {
					FrameBase.getDispose();
					FrameBase.getInstance(new Hot2());
					break;
				}
				default: {
					FrameBase.getDispose();
					FrameBase.getInstance(new Rain2());
					break;
				}
				}
			}
		});

		// 버튼 활성화
		buttonMeat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Meat2());
			}
		});

		// 버튼 활성화
		buttonVegan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Vegan2());
			}
		});

		// 버튼 활성화
		buttonSeafood.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Seafood2());
			}
		});

		// 버튼 활성화
		buttonDiet.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Diet2());
			}
		});

		buttonCalendar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "비회원이십니다.");
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

		btnMy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "비회원이십니다.");
			}
		});

		// 리스트(카테고리)로 넘어감
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new Category2());
			}
		});

		// 리스트(카테고리)로 넘어감
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FrameBase.getDispose();
				FrameBase.getInstance(new LoginPanel());
			}
		});

	}

	class homeImageThread extends Thread {
		public void run() {
			String[] imgs = { "./src/스파게티.png", "./src/부대찌개.png", "./src/떡볶이.png" };
			while (true) {
				for (int i = 0; i < imgs.length; i++) {
					try {
						Thread.sleep(1000);
						ImageIcon icon = new ImageIcon(imgs[i]);
						turningImageLable.setIcon(icon);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}