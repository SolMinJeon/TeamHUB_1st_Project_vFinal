package MainPage;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
import UserDB.History;
import UserDB.MyInfo;
import frame.Cursor1;
import frame.DetailedFoodInfo1;
import frame.Font1;
import frame.FrameBase;
import frame.MyPage1;
import login.LoginPanel;
import login.RoundedButton;

public class CartPanel extends JPanel {

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

   ArrayList<History> arr = new ArrayList<>();
   ArrayList<History> arr1 = new ArrayList<>();

   public CartPanel() {
   }

   public CartPanel(MyInfo m, CustomerInfo c, CartDAO cart) {

      this.c = c;
      this.m = m;

      setBackground(new Color(255, 255, 255));
      setLayout(null);
      setSize(500, 800);

      box = new ArrayList<>();

      ArrayList<ImageIcon> itemImg = new ArrayList<>();

      pa = new JPanel();
      pa.setBounds(25, 600, 452, 40);
      pa.setBackground(Color.WHITE);
      pa.setLayout(new FlowLayout());

      itemImg.clear();

      for (int i = 0; i < CartDAO.cartMap.get(c.getId()).size(); i++) {
         itemImg.add(new ImageIcon(CartDAO.cartMap.get(c.getId()).get(i).getProductImage()));
      }

      if ((CartDAO.cartMap.get(c.getId()).size() > 0)) {

         panelCount = (int) Math
               .ceil(Double.parseDouble(String.valueOf(CartDAO.cartMap.get(c.getId()).size())) / 4.0);
         
         for (int i = 0; i < panelCount; i++) {
            pgNum.add(new JPanel());
            JPanel top = new JPanel();
            top.setBackground(new Color(255, 255, 255));
            top.setBackground(new Color(0,0,0));
            top.setLayout(null);
            
            RoundedButton payBtn = new RoundedButton("구매하기");
            payBtn.setFont(new Font1().bigFont());
            payBtn.setBackground(new Color(255,255,255));
            payBtn.setBounds(180,650, 150, 50);
            add(payBtn);
            
            payBtn.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
      
                  String grade = c.gradeCheck((c.getTotal()));
      
                  if (payMoney == 0) {
                     JOptionPane.showMessageDialog(null, "구매할 수 있는 상품이 없습니다.");
                  } else {
                	  int result = JOptionPane.showConfirmDialog(null,
                              "총 금액은 " +payMoney +"원 입니다.\n"+"할인된 금액 "+c.salePrice(grade, payMoney) + "원을 결제 하시겠습니까?", "Confirm",
                              JOptionPane.YES_NO_CANCEL_OPTION);
                     if (result == JOptionPane.CLOSED_OPTION) {
                        JOptionPane.showMessageDialog(null, "결제를 취소하셨습니다.");
                     } else if (result == JOptionPane.YES_OPTION) {
                        // 나중에 로그인 유저 결제금액 올려주는 method 추가
                        JOptionPane.showMessageDialog(null, c.salePrice(grade, payMoney) + "원을 결제하셨습니다.");
                        
                        Calendar today = Calendar.getInstance();
                        int Year = today.get(Calendar.YEAR); 
                        int Month = today.get(Calendar.MONTH);
                        int Day = today.get(Calendar.DAY_OF_MONTH);
                        try {
                           BufferedWriter out = new BufferedWriter(new FileWriter("MemoData/"+LoginPanel.lid+Year+((Month+1)<10?"0":"")+(Month+1)+(Day<10?"0":"")+Day+".txt", true));
      
                           for(int i=0; i<arr.size(); i++) {
                              out.write(arr.get(i).getName() + " : " + arr.get(i).getKcal() + "kcal\n");
                           }
                           out.write("---------------------------------------------------\n");                     
                           out.close();
                        } catch (IOException e1) {
                           e1.printStackTrace();
                        }
                        
                        for (int i = 0; i < CartDAO.cartMap.get(c.getId()).size(); i++) {
                           CartDAO.cartMap.get(c.getId()).clear();
                        }
      
                        FrameBase.getDispose();
                        FrameBase.getInstance(new Category1(m,c, cart));
                        c.setTotal(c.salePrice(grade, payMoney));
      
                     } else {
                        JOptionPane.showMessageDialog(null, "결제를 취소하셨습니다.");
                     }
      
                  }
               }
            });
            
            pgNum.get(i).add(top);
         }
         pgNum.get(0).setVisible(true);

         for (int j = 0; j < panelCount; j++) {
            //JButton pageBtn = new JButton(String.valueOf(j + 1));
            RoundedButton pageBtn = new RoundedButton(String.valueOf(j + 1));

            if (Integer.parseInt(pageBtn.getText()) == panelCount) {

               pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).setBounds(30, 130, 430, 450);
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

                  JLabel nameInfo = new JLabel("상품명 : " + CartDAO.cartMap.get(c.getId()).get(i).getName()); // 상품명
                  nameInfo.setFont(new Font1().smallFont());
                  arr.add(new History(CartDAO.cartMap.get(c.getId()).get(i).getName(), CartDAO.cartMap.get(c.getId()).get(i).getKcal()));
                  
                  JLabel itemInfo = new JLabel("상품 가격 : " + CartDAO.cartMap.get(c.getId()).get(i).getPrice()
                        * CartDAO.cartMap.get(c.getId()).get(i).getCnt()
                        );
                  itemInfo.setFont(new Font1().smallFont());
                  JLabel cntInfo = new JLabel("수량 ");
                  cntInfo.setFont(new Font1().smallFont());

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
                  removeBtn.setFont(new Font1().smallFont());

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
                           FrameBase.getInstance(new CartPanel(m,c, cart));
                        }
                     }
                  });

                  JButton editBtn = new JButton("변경");
                  editBtn.setBackground(new Color(255, 255, 245));
                  editBtn.setFont(new Font1().smallFont());

                  editBtn.setBounds(350, 0 + ((i % 4) * 115), 65, 50);
                  editBtn.putClientProperty("id", CartDAO.cartMap.get(c.getId()).get(i).getId());
                  pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(editBtn);

                  editBtn.addActionListener(new ActionListener() {
                     @Override
                     public void actionPerformed(ActionEvent e) {

                        int id = Integer.parseInt(String.valueOf((editBtn.getClientProperty("id"))));
                        if (cart.change(id, Integer.parseInt(cntText.getText()), c.getId())) {
                           FrameBase.getDispose();
                           FrameBase.getInstance(new CartPanel(m,c, cart));
                        }
                     }
                  });
               }
            }

            else {

               pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).setBounds(30, 130, 430, 450);
               pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).setBackground(Color.WHITE);
               pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).setLayout(null);

               for (int i = (Integer.parseInt(pageBtn.getText()) - 1) * 4; i < Integer.parseInt(pageBtn.getText())
                     * 4; i++) {

                  Image image = itemImg.get(i).getImage();
                  Image newImg = image.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
                  // 이미지
                  ImageIcon newIcon = new ImageIcon(newImg);
                  JLabel img = new JLabel(newIcon);
                  img.setBounds(0, 0 + ((i % 4) * 110), 200, 100);
                  pgNum.get(Integer.parseInt(pageBtn.getText()) - 1).add(img);
                  
                  JLabel nameInfo = new JLabel("상품명 : " + CartDAO.cartMap.get(c.getId()).get(i).getName()); // 상품명
                  nameInfo.setFont(new Font1().smallFont());
                  arr.add(new History(CartDAO.cartMap.get(c.getId()).get(i).getName(), CartDAO.cartMap.get(c.getId()).get(i).getKcal()));
                  
                  JLabel itemInfo = new JLabel("상품 가격 : " + CartDAO.cartMap.get(c.getId()).get(i).getPrice()
                          * CartDAO.cartMap.get(c.getId()).get(i).getCnt()
                          );
                  itemInfo.setFont(new Font1().smallFont());
                  JLabel cntInfo = new JLabel("수량 ");
                  cntInfo.setFont(new Font1().smallFont());

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
                  removeBtn.setFont(new Font1().smallFont());

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
                           FrameBase.getInstance(new CartPanel(m,c, cart));
                        }
                     }
                  });

                  JButton editBtn = new JButton("변경");
                  editBtn.setFont(new Font1().smallFont());
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
                           FrameBase.getInstance(new CartPanel(m,c, cart));
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
      System.out.println(pgNum.size());
      
      // 총 결제 금액
      payMoney = cart.totalMoney(c.getId());

      RoundedButton payBtn = new RoundedButton("구매하기");
      payBtn.setFont(new Font1().bigFont());
      payBtn.setBackground(new Color(255,255,255));
      payBtn.setBounds(180,650, 150, 50);


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

      // 페이지 넘기는거 구현 해야함
      payBtn.addMouseListener(new MouseAdapter() { // 마우스 이벤트
            @Override
            public void mouseEntered(MouseEvent e) { // 마우스 들어왔을때
               setCursor(new Cursor1().Cursor2());
            }

            @Override
            public void mouseExited(MouseEvent e) { // 마우스 나왔을때
               setCursor(new Cursor1().Cursor1());
            }
         });
      
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


      // 백
      btnBack.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            FrameBase.getDispose();
            FrameBase.getInstance(new Category1(m,c, cart));
         }
      });
      
      add(payBtn);
      
      payBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            String grade = c.gradeCheck((c.getTotal()));

            if (payMoney == 0) {
               JOptionPane.showMessageDialog(null, "구매할 수 있는 상품이 없습니다.");
            } else {
            	int result = JOptionPane.showConfirmDialog(null,
                        "총 금액은 " +payMoney +"원 입니다.\n"+"할인된 금액 "+c.salePrice(grade, payMoney) + "원을 결제 하시겠습니까?", "Confirm",
                        JOptionPane.YES_NO_CANCEL_OPTION);
               if (result == JOptionPane.CLOSED_OPTION) {
                  JOptionPane.showMessageDialog(null, "결제를 취소하셨습니다.");
               } else if (result == JOptionPane.YES_OPTION) {
                  // 나중에 로그인 유저 결제금액 올려주는 method 추가
                  JOptionPane.showMessageDialog(null, c.salePrice(grade, payMoney) + "원을 결제하셨습니다.");
                  
                  Calendar today = Calendar.getInstance();
                  int Year = today.get(Calendar.YEAR); 
                  int Month = today.get(Calendar.MONTH);
                  int Day = today.get(Calendar.DAY_OF_MONTH);
                  try {
                     BufferedWriter out = new BufferedWriter(new FileWriter("MemoData/"+LoginPanel.lid+Year+((Month+1)<10?"0":"")+(Month+1)+(Day<10?"0":"")+Day+".txt", true));

                     for(int i=0; i<arr.size(); i++) {
                        out.write(arr.get(i).getName() + " : " + arr.get(i).getKcal() + "kcal\n");
//                        out.write(arr1.get(i).getName() + " : " + arr1.get(i).getKcal() + "kcal\n")
                     }
                     out.write("---------------------------------------------------\n");                     
                     out.close();
                  } catch (IOException e1) {
                     e1.printStackTrace();
                  }
                  
                  for (int i = 0; i < CartDAO.cartMap.get(c.getId()).size(); i++) {
                     CartDAO.cartMap.get(c.getId()).clear();
                  }

                  FrameBase.getDispose();
                  FrameBase.getInstance(new Category1(m,c, cart));
                  c.setTotal(c.salePrice(grade, payMoney));

               } else {
                  JOptionPane.showMessageDialog(null, "결제를 취소하셨습니다.");
               }

            }
         }
      });

   }

}