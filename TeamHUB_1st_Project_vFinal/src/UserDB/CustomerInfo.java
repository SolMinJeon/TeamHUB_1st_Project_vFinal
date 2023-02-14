package UserDB;

public class CustomerInfo {
   
   String id;
   String pw;
   String name;
   String birthday;
   String sex;
   String phone;
   int total;
   String grade;
   double salePrice;

   CustomerInfo(String id, String pw, String name,
         String birthday, String sex, String phone, int total,String grade) {
      this.id = id;
      this.pw = pw;
      this.name = name;
      this.birthday = birthday;
      this.sex = sex;
      this.phone = phone;
      this.total = total;
      this.grade = grade;
      //grade -> 할인율 
   }

   public int salePrice(String grade, int price) {
      switch(grade) {
         case "VVIP" : {
            salePrice = price * 0.9;
         }break;
         
         case "VIP" : {
            salePrice = price * 0.93;
         }break;
         
         case "GOLD" : {
            salePrice = price * 0.96;
         }break;
         
         case "SILVER" : {
            salePrice = price;
         }break;
      }
      return (int)salePrice;
   }
      
   public String gradeCheck(int total) {
      if(total >= 1000000) {
         setGrade("VVIP");
      }
      else if(total>=500000) {
         setGrade("VIP");
      }   
      else if(total>=100000) {
         setGrade("GOLD");
      }
      else {
         setGrade("SILVER");
      }
      return grade;
   }

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getPw() {
      return pw;
   }
   public void setPw(String pw) {
      this.pw = pw;
   }
   public int getTotal() {
      return total;
   }
   public void setTotal(int total) {
      this.total += total;
   }
   public void setGrade(String grade) {
      this.grade = grade;
   }
   public String getGrade() {
      return grade;
   }
   public String getBirthday() {
      return birthday;
   }
   public void setBirthday(String birthday) {
      this.birthday = birthday;
   }
   public String getSex() {
      return sex;
   }
   public void setSex(String sex) {
      this.sex = sex;
   }
   public String getPhone() {
      return phone;
   }
   public void setPhone(String phone) {
      this.phone = phone;
   }
   
}