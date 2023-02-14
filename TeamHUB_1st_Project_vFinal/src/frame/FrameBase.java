package frame;

//======================================== 기본 프레임 ========================================

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FrameBase extends JFrame{
	
	private static FrameBase instance;

	private FrameBase(JPanel e) {
		//시스템 정보(화면 크기를 얻기 위한 Toolkit)
		Toolkit tk = Toolkit.getDefaultToolkit();//해상도
		
		//기본 JFrame 구조
		setTitle("오늘 뭐 해 먹지?");
		setLayout(null);
		setSize(515, 838);
		setLocationRelativeTo(null); //프레임위치 화면중앙
		getImage(e);//기본 배경 이미지
		add(e);
		/* 숫가락 커서 적용 커서 클라스의 세팅된 커서를 부름(전체적용)*/
		setCursor(new Cursor1().Cursor1());
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //x버튼
	}//생성자

	//싱글톤 기법을 사용하려고 한다
	public static void getInstance(JPanel e) {
		//static으로 선언했으므로 해당 메서드가 생성자보다도 먼저 호출된다
		instance = new FrameBase(e);//생성자를 통해 기본 프레임 정의
		instance.getContentPane().removeAll();
		instance.getContentPane().add(e);
		instance.revalidate(); //레이아웃 관리자에게 레이아웃정보를 다시 계산하도록 지시
		instance.repaint(); //레이아웃을 새로 그린다
	}//getInstance()
	
	//기본 프레임 이미지를 저장하기 위한 메소드
	public static void getImage(JPanel e) {
		ImageIcon iphone = new ImageIcon("./src/iphone1.png");
		JLabel lblIphone = new JLabel(iphone);
		lblIphone.setSize(500, 800);
		lblIphone.setLocation(0, 0);
		
		// 사진 추가 
		e.add(lblIphone);
	}

	@Override
	public void dispose() {
		super.dispose();
	}
	public static void getDispose() {
		instance.dispose();
	}

}
