package com.easyticket.et.zxy.data;
import com.easyticket.et.zxy.util.AnalysisData;
import com.easyticket.et.zxy.util.EncryptData;
import org.litepal.crud.DataSupport;
import java.util.List;

public class AppStore{   //存储区数据结构

   private static AnalysisData analysisData = new AnalysisData();
   private static EncryptData encryptData = new EncryptData();

    public static final class Status{
        public static final int
                UNKNOW = 0,
                SUCCESS = 1,
                ACCOUNT_EXCEPTION=2,
                ACCOUNT_ERROR = 3,
                PWD_ERROR = 4,
                CHECKPASS = 5,
                ALTERDATA_ERROR = 6;
    }

	  public static class WifiInfo extends DataSupport{
          String Ssid;   //热点名称
          String Pwd;      //热点密码
          String name;    //场所名称
          int gateNums;    //闸机门数量

          public WifiInfo() {
			super();
		}

		public WifiInfo(String ssid, String pwd, String name, int gateNums) {
			super();
			Ssid =ssid;
			Pwd = pwd;
			this.name = name;
			this.gateNums = gateNums;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSsid() {
              return Ssid;
          }

          public void setSsid(String ssid) {
              Ssid = ssid;
          }

          public String getPwd() {
              return Pwd;
          }

          public void setPwd(String pwd) {
              Pwd = pwd;
          }

          public int getGateNums() {
              return gateNums;
          }

          public void setGateNums(int gateNums) {
              this.gateNums = gateNums;
          }
      }

   
   public  static class UserTicket extends DataSupport {
   
       String ticketCode;  //门票识别码
       String gateLocate;       //闸机门编号
       String macAddress;  //用户设备MAC地址
       int ticketType;    //用户请求的门票类型
       String phone;
       String areaid;

       public String getAreaid() {
           return areaid;
       }

       public void setAreaid(String areaid) {
           this.areaid = areaid;
       }

       public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserTicket() {
		super();
	 }

	public UserTicket(String ticketCode, String gateLocate,
			String macAddress,String phone,String areaid,int ticketType) {
		super();
	   this.areaid = areaid;
		this.ticketCode = ticketCode;
		this.gateLocate = gateLocate;
		this.macAddress = macAddress;
		this.phone = phone;
		this.ticketType = ticketType;
	}




       public String getTicketCode() {
           return ticketCode;
       }

       public void setTicketCode(String ticketCode) {
           this.ticketCode = ticketCode;
       }

       public String getGateLocate() {
           return gateLocate;
       }

       public void setGateLocate(String gateLocate) {
           this.gateLocate = gateLocate;
       }

       public String getMacAddress() {
           return macAddress;
       }

       public void setMacAddress(String macAddress) {
           this.macAddress = macAddress;
       }

       public int getTicketType() {
           return ticketType;
       }

       public void setTicketType(int ticketType) {
           this.ticketType = ticketType;
       }
   }
   

    public static class User extends DataSupport {
    	
  
        private String usrName;
        private String mobilePhone;  //若是商家，需要求商家提供门票数据中的电话
        private String usrPwd;
        private List<UserTicket> tickets;  //用户门票数据
        private Wallet wallet;   //钱包
        private String sex;
        private String areaid;
        private String locate;
    	public  String BUSSINESS;
        
    	public User(){
    		
    	}
    	
    	
    	
		public String getAreaid() {
			return areaid;
		}



		public void setAreaid(String areaid) {
			this.areaid = areaid;
		}



		public String getBUSSINESS() {
			return BUSSINESS;
		}



		public User(String usrName, String usrPwd,
				List<UserTicket> tickets, Wallet wallet, String sex,
				String mobilePhone,String locate) {
			super();
			
			this.usrName = usrName;
			this.usrPwd = usrPwd;
			this.tickets = tickets;
			this.wallet = wallet;
			this.sex = sex;
			this.mobilePhone = mobilePhone;
			
			this.locate = locate;
		}



		public User(String usrName, String usrPwd, String sex,
				String mobilePhone,String locate,String isBussiness,String areaid) {
			super();
			this.usrName = usrName;
			this.usrPwd = usrPwd;
			this.sex = sex;
			this.mobilePhone = mobilePhone;
			this.locate = locate;
			this.BUSSINESS = isBussiness;
			this.areaid = areaid;
			
		}
		
	
	

       


		
    


		public void setBUSSINESS(String bUSSINESS) {
			BUSSINESS = bUSSINESS;
		}



		public String getLocate() {
			return locate;
		}

		public void setLocate(String locate) {
			this.locate = locate;
		}


        public String getUsrName() {
            return usrName;
        }

        public void setUsrName(String usrName) {
            this.usrName = usrName;
        }

        

        public String getUsrPwd() {
            return usrPwd;
        }

        public void setUsrPwd(String usrPwd) {
            this.usrPwd = usrPwd;
        }

        public List<UserTicket> getTickets() {
            return tickets;
        }

        public void setTickets(List<UserTicket> tickets) {
            this.tickets = tickets;
        }

        public Wallet getWallet() {
            return wallet;
        }

        public void setWallet(Wallet wallet) {
            this.wallet = wallet;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

     
    }
    
    public static class Wallet extends DataSupport{
        String walletPwd;   //钱包密码
        double  balance;     //钱包余额
        String realName;     //用户真实姓名
        String bankIds;      //银行卡号

        
        public Wallet() {
			super();
		}

		public Wallet(String walletPwd, double balance, String realName,
				String bankIds) {
			super();
			this.walletPwd = walletPwd;
			this.balance = balance;
			this.realName = realName;
			this.bankIds = bankIds;
		}

		public String getWalletPwd() {
            return walletPwd;
        }

        public void setWalletPwd(String walletPwd) {
            this.walletPwd = walletPwd;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getBankIds() {
            return bankIds;
        }

        public void setBankIds(String bankIds) {
            this.bankIds = bankIds;
        }
    }


    public static class Bussiness extends DataSupport{
        private String usrCount;
        private String usrName;
        private String usrPwd;
        private String mobilePhone;
        private String realName;   //商家真实姓名
        private String locate;   //商家地址
        private String sex;  
        private List<WifiInfo> wifis;   //商家热点
        public final static boolean BUSSINESS=true;
        
        
        
        
        
        public Bussiness() {
			super();
		}

		public Bussiness(String usrCount, String usrName, String usrPwd,
				String mobilePhone, String realName, String locate, String sex,
				List<WifiInfo> wifis) {
			super();
			this.usrCount = usrCount;
			this.usrName = usrName;
			this.usrPwd = usrPwd;
			this.mobilePhone = mobilePhone;
			this.realName = realName;
			this.locate = locate;
			this.sex = sex;
			this.wifis = wifis;
		}

		WifiInfo getWifiInfoInstance() {
            return new WifiInfo();
        }

        public static boolean isBussiness() {
			return BUSSINESS;
		}
        
        public void setSex(String Sex){
        	sex = Sex;
        }
        
        public String getSex(){
        	return sex;
        }

		
        public String getUsrCount() {
            return usrCount;
        }

        public void setUsrCount(String usrCount) {
            this.usrCount = usrCount;
        }

        public String getUsrName() {
            return usrName;
        }

        public void setUsrName(String usrName) {
            this.usrName = usrName;
        }

        public String getUsrPwd() {
            return usrPwd;
        }

        public void setUsrPwd(String usrPwd) {
            this.usrPwd = usrPwd;
        }

        public String getMobilePhone() {
            return mobilePhone;
        }

        public void setMobilePhone(String mobilePhone) {
            this.mobilePhone = mobilePhone;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getLocate() {
            return locate;
        }

        public void setLocate(String locate) {
            this.locate = locate;
        }

        public List<WifiInfo> getWifis() {
            return wifis;
        }

        public void setWifis(List<WifiInfo> wifis) {
            this.wifis = wifis;
        }
    }



    public static class Ticket extends DataSupport{
        private String name;   //门票名称
        private String ticketCode;//门票识别码
        private double price;   //价格
        private String briefIntroduction;   //简介
        private double grade;    //评分
        private String imagePath;      //门票图片
        private String sales_number;   //门票销量
        private String labels;  //门票标签
        private String locate; //位置
        private int num;   //门票数量
        private String dealTime; //门票购买时间
        private String original_place; //起始地
        private String destination_place;//目的地
        private int ticketType;       //商家门票类型
        private String areaId; //商家景点ID号
        private String ticketId; //商家门票id号
        private String payStatus;//支付状态
        private String dealLine; // 有效时间
        private int gatesNum;



        public int getGatesNum() {
            return gatesNum;
        }

        public void setGatesNum(int gatesNum) {
            this.gatesNum = gatesNum;
        }

        public String getOriginal_destination_place(){
            return  original_place+" --> "+destination_place;
        }





        public String getDealLine() {
            return dealLine;
        }

        public void setDealLine(String dealLine) {
            this.dealLine = dealLine;
        }

        public String getTicketId() {
            return ticketId;
        }

        public void setTicketId(String ticketId) {
            this.ticketId = ticketId;
        }

        public  String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }




        public Ticket() {

		}

		public Ticket(String name, String ticketCode, double price,
				String briefIntroduction, double grade, String imagePath,
				String sales_number, String labels, String locate, int num,
				String dealTime, String original_place,int gatesNum,
				String destination_place,String dealLine,int ticketType,String areaid,String payStatus) {
            super();
            this.dealLine = dealLine;
            this.name = name;
            this.ticketCode = ticketCode;
            this.price = price;
            this.briefIntroduction = briefIntroduction;
            this.grade = grade;
            this.imagePath = imagePath;
            this.sales_number = sales_number;
            this.labels = labels;
            this.locate = locate;
            this.num = num;
            this.dealTime = dealTime;
            this.original_place = original_place;
            this.destination_place = destination_place;
            this.ticketType = ticketType;
            this.areaId = areaid;
            this.gatesNum = gatesNum;
            this.payStatus = payStatus;
        }

        public int getTicketType() {
            return ticketType;
        }

        public void setTicketType(int ticketType) {
            this.ticketType = ticketType;
        }

        public String getAreaId() {
			return areaId;
		}

		public void setAreaId(String areaId) {
			this.areaId = areaId;
		}

		public String getDealTime() {
			return dealTime;
		}

		public void setDealTime(String dealTime) {
			this.dealTime = dealTime;
		}

		public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTicketCode() {
            return ticketCode;
        }

        public void setTicketCode(String ticketCode) {
            this.ticketCode = ticketCode;
        }

        public double getPrice() {
            return price;
        }


		public void setPrice(double price) {
            this.price = price;
        }

        public String getBriefIntroduction() {
            return briefIntroduction;
        }

        public void setBriefIntroduction(String briefIntroduction) {
            this.briefIntroduction = briefIntroduction;
        }

        public double getGrade() {
            return grade;
        }

        public void setGrade(double grade) {
            this.grade = grade;
        }

        public String getImagePath() {
            return imagePath;
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }

        public String getSales_number() {
            return sales_number;
        }

        public void setSales_number(String sales_number) {
            this.sales_number = sales_number;
        }

        public String getLabels() {
            return labels;
        }

        public void setLabels(String labels) {
            this.labels = labels;
        }

        public String getLocate() {
            return locate;
        }

        public void setLocate(String locate) {
            this.locate = locate;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public String getOriginal_place() {
            return original_place;
        }

        public void setOriginal_place(String original_place) {
            this.original_place = original_place;
        }

        public String getDestination_place() {
            return destination_place;
        }

        public void setDestination_place(String destination_place) {
            this.destination_place = destination_place;
        }


    }
    
    public static class LoginData extends DataSupport{
    	private String areaid;
    	private String phone;
    	private String userName;
    	private String time;
    	private String pwd;
    	private String connectid;
    	
    	

		public LoginData(String areaid, String phone,
				String time,String pwd,String userName,String connectid) {
			super();
			this.areaid = areaid;
			this.phone = phone;
			this.time = time;
			this.pwd = pwd;
			this.userName = userName;
			this.connectid = connectid;
		}
		
		public LoginData(String phone,String userName,
				String time) {
			super();
			this.phone = phone;
			this.time = time;
			this.userName = userName;
		}


		public String getConnectid() {
			return connectid;
		}


		public void setConnectid(String connectid) {
			this.connectid = connectid;
		}


		public LoginData() {
			super();
		}


		public String getTime(){
			return time;
		}
		

		public void setTime(String time) {
			this.time = time;
		}

		public String getPwd() {
			return pwd;
		}

		public void setPwd(String pwd) {
			this.pwd = pwd;
		}

		

		public LoginData(String Phone,String pwd) {
				this.phone = Phone;
				this.pwd = pwd;
		}

		
		public String getAreaid() {
			return areaid;
		}

		public void setAreaid(String areaid) {
			this.areaid = areaid;
		}

		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
		
    }
    public  static class Developer extends DataSupport{
        private List<String> names;   //开发者姓名
        private String contact;  //联系方式
        private String email; //邮箱
        private String locate;  //地址
        private String briefIntroduction;//开发者简介
        private List<String> aboutProducts;  //产品介绍

        
        
        
        public Developer() {
			super();
		}

		public Developer(List<String> names, String contact, String email,
				String locate, String briefIntroduction,
				List<String> aboutProducts) {
			super();
			this.names = names;
			this.contact = contact;
			this.email = email;
			this.locate = locate;
			this.briefIntroduction = briefIntroduction;
			this.aboutProducts = aboutProducts;
		}

		public List<String> getNames() {
            return names;
        }

        public void setNames(List<String> names) {
            this.names = names;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getLocate() {
            return locate;
        }

        public void setLocate(String locate) {
            this.locate = locate;
        }

        public String getBriefIntroduction() {
            return briefIntroduction;
        }

        public void setBriefIntroduction(String briefIntroduction) {
            this.briefIntroduction = briefIntroduction;
        }

		
        public List<String> getAboutProducts() {
            return aboutProducts;
        }
		

        public void setAboutProducts(List<String> aboutProducts) {
            this.aboutProducts = aboutProducts;
        }
    }

}


