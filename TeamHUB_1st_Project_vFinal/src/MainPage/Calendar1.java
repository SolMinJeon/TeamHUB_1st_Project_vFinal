package MainPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import UserDB.CartDAO;
import UserDB.CustomerInfo;
import UserDB.MyInfo;
import frame.FrameBase;
import frame.MyPage1;
import login.LoginPanel;

class CalendarDataManager { // 6*7배열에 나타낼 달력 값을 구하는 class
	static final int CAL_WIDTH = 7;
	final static int CAL_HEIGHT = 6;
	int calDates[][] = new int[CAL_HEIGHT][CAL_WIDTH];
	int calYear;
	int calMonth;
	int calDayOfMon;
	final int calLastDateOfMonth[]={31,28,31,30,31,30,31,31,30,31,30,31};
	int calLastDate;
	Calendar today = Calendar.getInstance();
	Calendar cal;
	
	public CalendarDataManager(){ 
		setToday(); 
	}
	public void setToday(){
		calYear = today.get(Calendar.YEAR); 
		calMonth = today.get(Calendar.MONTH);
		calDayOfMon = today.get(Calendar.DAY_OF_MONTH);
		makeCalData(today);
	}
	private void makeCalData(Calendar cal){
		// 1일의 위치와 마지막 날짜를 구함 
		int calStartingPos = (cal.get(Calendar.DAY_OF_WEEK)+7-(cal.get(Calendar.DAY_OF_MONTH))%7)%7;
		if(calMonth == 1) calLastDate = calLastDateOfMonth[calMonth] + leapCheck(calYear);
		else calLastDate = calLastDateOfMonth[calMonth];
		// 달력 배열 초기화
		for(int i = 0 ; i<CAL_HEIGHT ; i++){
			for(int j = 0 ; j<CAL_WIDTH ; j++){
				calDates[i][j] = 0;
			}
		}
		// 달력 배열에 값 채워넣기
		for(int i = 0, num = 1, k = 0 ; i<CAL_HEIGHT ; i++){
			if(i == 0) k = calStartingPos;
			else k = 0;
			for(int j = k ; j<CAL_WIDTH ; j++){
				if(num <= calLastDate) calDates[i][j]=num++;
			}
		}
	}
	private int leapCheck(int year){ // 윤년인지 확인하는 함수
		if(year%4 == 0 && year%100 != 0 || year%400 == 0) return 1;
		else return 0;
	}
	public void moveMonth(int mon){ // 현재달로 부터 n달 전후를 받아 달력 배열을 만드는 함수(1년은 +12, -12달로 이동 가능)
		calMonth += mon;
		if(calMonth>11) while(calMonth>11){
			calYear++;
			calMonth -= 12;
		} else if (calMonth<0) while(calMonth<0){
			calYear--;
			calMonth += 12;
		}
		cal = new GregorianCalendar(calYear,calMonth,calDayOfMon);
		makeCalData(cal);
	}
}

public class Calendar1 extends CalendarDataManager {
	
	//메인
	JFrame mainFrame;
		
	JPanel mainPanel = new JPanel();
	
	//탑
	JPanel calOpPanel;
		JButton todayBut;
		JLabel todayLab;
		
		JLabel infoClock;
		
		JButton lYearBut;
		JButton lMonBut;
		JLabel curMMYYYYLab;
		JButton nMonBut;
		JButton nYearBut;
		ListenForCalOpButtons lForCalOpButtons = new ListenForCalOpButtons();
		
	//카렌다
	JPanel calPanel;
		JButton weekDaysName[];
		JButton dateButs[][] = new JButton[6][7];
		listenForDateButs lForDateButs = new listenForDateButs(); 
	
	JPanel memoPanel;
		JLabel selectedDate;
		JTextArea memoArea;
		JScrollPane memoAreaSP;
		JPanel memoSubPanel;
		JButton saveBut; 
		JButton delBut; 
		JButton clearBut;
	
	JPanel frameBottomPanel;
		JButton backb;
		JButton homeb;
		JButton myb;
	
	//상수, 메세지
	final String WEEK_DAY_NAME[] = { "일", "월", "화", "수", "목", "금", "토" };
	final String title = "오늘 뭐 해 먹지?";
	
	public Calendar1(MyInfo m,CustomerInfo c, CartDAO cart) { //구성요소 순으로 정렬되어 있음. 각 판넬 사이에 빈줄로 구별
		
		mainFrame = new JFrame(title);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon iphone = new ImageIcon("./src/iphone1.png");
		JLabel lblIphone = new JLabel(iphone);
		lblIphone.setBounds(0, 0, 500, 800);
		mainFrame.setBounds(0, 0, 515, 838);
		mainFrame.setLocationRelativeTo(null); //프레임위치 화면중앙
		mainFrame.setResizable(false); //크기조절
		mainFrame.add(lblIphone);
		
		try{
			UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//LookAndFeel Windows 스타일 적용
			SwingUtilities.updateComponentTreeUI(mainFrame) ;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		calOpPanel = new JPanel();
			todayBut = new JButton("오늘날짜");
			todayBut.setToolTipText("Today");
			todayBut.addActionListener(lForCalOpButtons);
			todayLab = new JLabel(today.get(Calendar.MONTH)+1+"/"+today.get(Calendar.DAY_OF_MONTH)+"/"+today.get(Calendar.YEAR));
			todayLab.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			
			infoClock = new JLabel(today.get(Calendar.MONTH)+1+"/"+today.get(Calendar.DAY_OF_MONTH)+"/"+today.get(Calendar.YEAR));
			calOpPanel.setLayout(new BorderLayout());
			infoClock = new JLabel("", SwingConstants.RIGHT); //시간 메모기준 정렬
			infoClock = new JLabel(""); //시간 메모기준 정렬
			infoClock.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			calOpPanel.add(infoClock, BorderLayout.NORTH);
			calOpPanel.add(infoClock);
			selectedDate = new JLabel("<Html><font size=3>"+(today.get(Calendar.MONTH)+1)+"/"+today.get(Calendar.DAY_OF_MONTH)+"/"+today.get(Calendar.YEAR)+"&nbsp;(Today)</html>", SwingConstants.LEFT);
			selectedDate.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
			
			lYearBut = new JButton("<<");
			lYearBut.setToolTipText("Previous Year");
			lYearBut.addActionListener(lForCalOpButtons);
			lMonBut = new JButton("<");
			lMonBut.setToolTipText("Previous Month");
			lMonBut.addActionListener(lForCalOpButtons);
			curMMYYYYLab = new JLabel("<html><table width=100><tr><th><font size=5>"+((calMonth+1)<10?"&nbsp;":"")+(calMonth+1)+" / "+calYear+"</th></tr></table></html>");
			nMonBut = new JButton(">");
			nMonBut.setToolTipText("Next Month");
			nMonBut.addActionListener(lForCalOpButtons);
			nYearBut = new JButton(">>");
			nYearBut.setToolTipText("Next Year");
			nYearBut.addActionListener(lForCalOpButtons);
			
			calOpPanel.setLayout(new GridBagLayout());
			GridBagConstraints calOpGC = new GridBagConstraints();
			calOpGC.gridx = 1;
			calOpGC.gridy = 1;
			calOpGC.gridwidth = 2;
			calOpGC.gridheight = 1;
			calOpGC.weightx = 1;
			calOpGC.weighty = 1;
			calOpGC.insets = new Insets(5,5,0,0);
			calOpGC.anchor = GridBagConstraints.WEST;
			calOpGC.fill = GridBagConstraints.NONE;
			calOpPanel.add(todayBut,calOpGC);
			calOpGC.gridwidth = 3;
			calOpGC.gridx = 2;
			calOpGC.gridy = 1;
			calOpPanel.add(todayLab,calOpGC);
			calOpGC.anchor = GridBagConstraints.CENTER;
			
			calOpGC.gridwidth = 3;
			calOpGC.gridx = 6;
			calOpGC.gridy = 1;
			calOpPanel.add(infoClock,calOpGC);
			calOpGC.anchor = GridBagConstraints.EAST;
			
			calOpGC.gridwidth = 1;
			calOpGC.gridx = 1;
			calOpGC.gridy = 2;
			calOpPanel.add(lYearBut,calOpGC);
			calOpGC.gridwidth = 1;
			calOpGC.gridx = 2;
			calOpGC.gridy = 2;
			calOpPanel.add(lMonBut,calOpGC);
			calOpGC.gridwidth = 2;
			calOpGC.gridx = 3;
			calOpGC.gridy = 2;
			calOpPanel.add(curMMYYYYLab,calOpGC);
			calOpGC.gridwidth = 1;
			calOpGC.gridx = 5;
			calOpGC.gridy = 2;
			calOpPanel.add(nMonBut,calOpGC);
			calOpGC.gridwidth = 1;
			calOpGC.gridx = 6;
			calOpGC.gridy = 2;
			calOpPanel.add(nYearBut,calOpGC);
		
		calPanel=new JPanel();
			weekDaysName = new JButton[7];
			for(int i=0 ; i<CAL_WIDTH ; i++){
				weekDaysName[i]=new JButton(WEEK_DAY_NAME[i]);
				weekDaysName[i].setBorderPainted(false);
				weekDaysName[i].setContentAreaFilled(false);
				weekDaysName[i].setForeground(Color.WHITE); //요일 글자 색
				if(i == 0) weekDaysName[i].setBackground(new Color(200, 50, 50));
				else if (i == 6) weekDaysName[i].setBackground(new Color(50, 100, 200));
				else weekDaysName[i].setBackground(new Color(150, 150, 150));
				weekDaysName[i].setOpaque(true);
				weekDaysName[i].setFocusPainted(false);
				calPanel.add(weekDaysName[i]);
			}
			for(int i=0 ; i<CAL_HEIGHT ; i++){
				for(int j=0 ; j<CAL_WIDTH ; j++){
					dateButs[i][j]=new JButton();
					dateButs[i][j].setBorderPainted(false);
					dateButs[i][j].setContentAreaFilled(false);
					dateButs[i][j].setBackground(Color.WHITE); //날짜 버튼 색
					dateButs[i][j].setOpaque(true);
					dateButs[i][j].addActionListener(lForDateButs);
					calPanel.add(dateButs[i][j]);
				}
			}
			calPanel.setLayout(new GridLayout(0,7,2,2)); // 버튼배치?
			calPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); //버튼여백?
			showCal(); // 달력을 표시
			
		memoPanel=new JPanel();
			memoPanel.setBorder(BorderFactory.createTitledBorder(LoginPanel.lname)); //메모 제목
			memoArea = new JTextArea();
			memoArea.setLineWrap(true); //자동 줄바꾸기
			memoArea.setWrapStyleWord(true); //오른쪽끝을 만나면 다음칸으로 넘어감
			memoAreaSP = new JScrollPane(memoArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			memoAreaSP.setPreferredSize(new Dimension(420, 50));
			readMemo();
			
			memoSubPanel=new JPanel();
			
			saveBut = new JButton("메모 저장"); 
			
			saveBut.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					try {
						File f= new File("MemoData");
						if(!f.isDirectory()) f.mkdir();
						JOptionPane.showMessageDialog(null, "메모가 저장되었습니다.");
						String memo = memoArea.getText();
						if(memo.length()>=0){
							BufferedWriter out = new BufferedWriter(new FileWriter("MemoData/"+LoginPanel.lid+calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDayOfMon<10?"0":"")+calDayOfMon+".txt"));
							String str = memoArea.getText();
							out.write(str);  
							out.close();
						}
						else
							JOptionPane.showMessageDialog(null, "error");
					} catch (IOException e) {
						e.printStackTrace();
					}
					showCal();
				}					
			});
			
			delBut = new JButton("메모 삭제");
			
			delBut.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					memoArea.setText("");
					File f =new File("MemoData/"+LoginPanel.lid+calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDayOfMon<10?"0":"")+calDayOfMon+".txt");
					JOptionPane.showMessageDialog(null, "메모가 삭제되었습니다.");
					if(f.exists()){
						f.delete();
						showCal();
					}
					else 
						JOptionPane.showMessageDialog(null, "error");			
				}					
			});
			
			clearBut = new JButton("메모 초기화");
			
			clearBut.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					JOptionPane.showMessageDialog(null, "메모가 초기화되었습니다.");
					memoArea.setText("");
				}
			});
			
			memoSubPanel.add(saveBut);
			memoSubPanel.add(delBut);
			memoSubPanel.add(clearBut);
			memoSubPanel.setBackground(Color.WHITE);
			memoPanel.setLayout(new BorderLayout());
			memoPanel.add(selectedDate, BorderLayout.NORTH);
			memoPanel.add(memoAreaSP,BorderLayout.CENTER);
			memoPanel.add(memoSubPanel,BorderLayout.SOUTH);
			
		frameBottomPanel = new JPanel();
		
		ImageIcon backi = new ImageIcon("./src/back1.png");
		ImageIcon homei = new ImageIcon("./src/home1.png");
		ImageIcon myi = new ImageIcon("./src/my1.png");
		ImageIcon backh = new ImageIcon("./src/back2.png");
		ImageIcon homeh = new ImageIcon("./src/home2.png");
		ImageIcon myh = new ImageIcon("./src/my2.png");
		
		myb = new JButton(myi);
		myb.setSize(50, 50);
		myb.setRolloverIcon(myh);
		myb.setBorderPainted(false);
		myb.setFocusPainted(false);
		myb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				try {
					FrameBase.getInstance(new MyPage1(m,c,cart));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		homeb = new JButton(homei);
		homeb.setSize(50, 50);
		homeb.setRolloverIcon(homeh);
		homeb.setBorderPainted(false);
		homeb.setFocusPainted(false);
		homeb.setBorder(BorderFactory.createEmptyBorder(0,65,0,65));
		homeb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				FrameBase.getInstance(new Category1(m,c,cart));				
				
			}
		});
		
		backb = new JButton(backi);
		backb.setSize(50, 50);
		backb.setRolloverIcon(backh);
		backb.setBorderPainted(false);
		backb.setFocusPainted(false);
		backb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				FrameBase.getInstance(new Category1(m,c,cart));
			}
		});
		
		
		
		myb.setBackground(Color.white);
		homeb.setBackground(Color.white);
		backb.setBackground(Color.white);
		
		frameBottomPanel.add(myb);
		frameBottomPanel.add(homeb);
		frameBottomPanel.add(backb);

		//calOpPanel, calPanel을  frameSubPanelWest에 배치
		JPanel frameSubPanelWest = new JPanel();
		Dimension calOpPanelSize = calOpPanel.getPreferredSize();
		calOpPanelSize.height = 70; //캘린더 상단 세로
		calOpPanelSize.width = 450; //캘린더 상단 세로
		calOpPanel.setPreferredSize(calOpPanelSize);
		frameSubPanelWest.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
		
		Dimension calPanelSize = calPanel.getPreferredSize();
		calPanelSize.height = 300;
		calPanelSize.width = 450;
		calPanel.setPreferredSize(calPanelSize);
		
		frameSubPanelWest.setLayout(new BorderLayout());
		frameSubPanelWest.add(calOpPanel,BorderLayout.NORTH);
		frameSubPanelWest.add(calPanel,BorderLayout.CENTER);
		
		

		//infoPanel, memoPanel을  frameSubPanelEast에 배치
		JPanel frameSubPanelEast = new JPanel();
		Dimension memoPanelSize=memoPanel.getPreferredSize();
		memoPanelSize.height = 250;
		memoPanelSize.width = 450;
		memoPanel.setPreferredSize(memoPanelSize);
		frameSubPanelEast.add(memoPanel,BorderLayout.CENTER);
		memoPanel.setBackground(Color.white);
		frameSubPanelEast.add(memoPanel);

		Dimension frameSubPanelWestSize = frameSubPanelWest.getPreferredSize();
		frameSubPanelWestSize.width = 450;
		frameSubPanelWest.setPreferredSize(frameSubPanelWestSize);
		
		JPanel frameBottomPanelBottom= new JPanel();
		Dimension frameBottomPanelSize = frameBottomPanel.getPreferredSize();
		frameBottomPanelSize.width = 450;
		frameBottomPanelSize.height = 70;
		frameBottomPanel.setPreferredSize(frameBottomPanelSize);
		frameBottomPanel.setBackground(Color.white);
		frameBottomPanelBottom.add(frameBottomPanel);
		
		frameSubPanelEast.setBackground(Color.white);
		frameBottomPanelBottom.setBackground(Color.white);
		

		mainPanel.setPreferredSize(new Dimension(400, 500));
		mainPanel.add(frameSubPanelWest, BorderLayout.NORTH); //기본값 WEST 카렌다 위치
		mainPanel.add(frameSubPanelEast, BorderLayout.CENTER);
		mainPanel.add(frameBottomPanelBottom, BorderLayout.SOUTH);
		mainPanel.setBackground(Color.white);
		mainFrame.add(mainPanel);
		mainFrame.getContentPane().setBackground(Color.WHITE);
		mainFrame.setVisible(true);

		focusToday(); //현재 날짜에 focus를 줌 (mainFrame.setVisible(true) 이후에 배치해야함)
		
		//Thread 작동(시계, bottomMsg 일정시간후 삭제)
		ThreadConrol threadCnl = new ThreadConrol();
		threadCnl.start();	
	}
	

	private void focusToday(){
		if(today.get(Calendar.DAY_OF_WEEK) == 1)
			dateButs[today.get(Calendar.WEEK_OF_MONTH)][today.get(Calendar.DAY_OF_WEEK)-1].requestFocusInWindow();
		else
			dateButs[today.get(Calendar.WEEK_OF_MONTH)-1][today.get(Calendar.DAY_OF_WEEK)-1].requestFocusInWindow();
	}
	
	private void readMemo(){
		try{
			File f = new File("MemoData/"+LoginPanel.lid+calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDayOfMon<10?"0":"")+calDayOfMon+".txt");
			//getID로 해당 아이디 메모 불러올 것
			if(f.exists()){
				BufferedReader in = new BufferedReader(new FileReader("MemoData/"+LoginPanel.lid+calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDayOfMon<10?"0":"")+calDayOfMon+".txt"));

				String memoAreaText= new String();
				while(true){
					String tempStr = in.readLine();
					if(tempStr == null) break;
					memoAreaText = memoAreaText + tempStr + System.getProperty("line.separator");
				}
				memoArea.setText(memoAreaText);
				in.close();	
			}
			else memoArea.setText("");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void showCal(){
		for(int i=0;i<CAL_HEIGHT;i++){
			for(int j=0;j<CAL_WIDTH;j++){
				String fontColor="black";
				if(j==0) fontColor="red";
				else if(j==6) fontColor="blue";
				
				File f =new File("MemoData/"+LoginPanel.lid+calYear+((calMonth+1)<10?"0":"")+(calMonth+1)+(calDates[i][j]<10?"0":"")+calDates[i][j]+".txt");
				if(f.exists()){
					dateButs[i][j].setText("<html><b><font color="+fontColor+">"+calDates[i][j]+"</font></b></html>");
				}
				else dateButs[i][j].setText("<html><font color="+fontColor+">"+calDates[i][j]+"</font></html>");

				JLabel todayMark = new JLabel("<html><font color=green>*</html>");
				dateButs[i][j].removeAll();
				if(calMonth == today.get(Calendar.MONTH) &&
						calYear == today.get(Calendar.YEAR) &&
						calDates[i][j] == today.get(Calendar.DAY_OF_MONTH)){
					dateButs[i][j].add(todayMark);
					dateButs[i][j].setToolTipText("Today");
				}
				
				if(calDates[i][j] == 0) dateButs[i][j].setVisible(false);
				else dateButs[i][j].setVisible(true);
			}
		}
	}
	private class ListenForCalOpButtons implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == todayBut){
				setToday();
				lForDateButs.actionPerformed(e);
				focusToday();
			}
			else if(e.getSource() == lYearBut) moveMonth(-12);
			else if(e.getSource() == lMonBut) moveMonth(-1);
			else if(e.getSource() == nMonBut) moveMonth(1);
			else if(e.getSource() == nYearBut) moveMonth(12);
			
			curMMYYYYLab.setText("<html><table width=100><tr><th><font size=5>"+((calMonth+1)<10?"&nbsp;":"")+(calMonth+1)+" / "+calYear+"</th></tr></table></html>");
			showCal();
		}
	}
	private class listenForDateButs implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int k=0,l=0;
			for(int i=0 ; i<CAL_HEIGHT ; i++){
				for(int j=0 ; j<CAL_WIDTH ; j++){
					if(e.getSource() == dateButs[i][j]){ 
						k=i;
						l=j;
					}
				}
			}
	
			if(!(k ==0 && l == 0)) calDayOfMon = calDates[k][l]; //today버튼을 눌렀을때도 이 actionPerformed함수가 실행되기 때문에 넣은 부분

			cal = new GregorianCalendar(calYear,calMonth,calDayOfMon);
			
			String dDayString = new String();
			int dDay=((int)((cal.getTimeInMillis() - today.getTimeInMillis())/1000/60/60/24));
			if(dDay == 0 && (cal.get(Calendar.YEAR) == today.get(Calendar.YEAR)) 
					&& (cal.get(Calendar.MONTH) == today.get(Calendar.MONTH))
					&& (cal.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH))) dDayString = "Today"; 
			else if(dDay >=0) dDayString = "D-"+(dDay+1);
			else if(dDay < 0) dDayString = "D+"+(dDay)*(-1);
			
			selectedDate.setText("<Html><font size=3>"+(calMonth+1)+"/"+calDayOfMon+"/"+calYear+"&nbsp;("+dDayString+")</html>");
			
			readMemo();
		}
	}
	
	private class ThreadConrol extends Thread {
		public void run(){
			boolean msgCntFlag = false;
			int num = 0;
			String curStr = new String();
			while(true){
				try{
					today = Calendar.getInstance();
					String amPm = (today.get(Calendar.AM_PM)==0?"AM":"PM");
					String hour;
							if(today.get(Calendar.HOUR) == 0) hour = "12"; 
							else if(today.get(Calendar.HOUR) == 12) hour = " 0";
							else hour = (today.get(Calendar.HOUR)<10?" ":"")+today.get(Calendar.HOUR);
					String min = (today.get(Calendar.MINUTE)<10?"0":"")+today.get(Calendar.MINUTE);
					String sec = (today.get(Calendar.SECOND)<10?"0":"")+today.get(Calendar.SECOND);
					infoClock.setText(amPm+" "+hour+":"+min+":"+sec);

					sleep(1000);
					String infoStr = "";
					
					if(infoStr != " " && (msgCntFlag == false || curStr != infoStr)){
						num = 5;
						msgCntFlag = true;
						curStr = infoStr;
					}
					else if(infoStr != " " && msgCntFlag == true){
						if(num > 0) num--;
						else{
							msgCntFlag = false;
						}
					}		
				}
				catch(InterruptedException e){
					System.out.println("Thread:Error");
				}
			}
		}
	}

	

}
