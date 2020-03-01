package com.easyticket.et.zxy.data;


import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by zxy on 2018/3/15.
 */


public class UserInfo extends DataSupport{

    private  int userId;
    private String refundResult;    //申请结果
    private String name;
    private AppStore.Ticket ticket;

    public String getRefundResult() {
        return refundResult;
    }

    public void setRefundResult(String refundResult) {
        this.refundResult = refundResult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId(){
        return  userId;
    }

    public void setUserId(int userId){
        this.userId = userId;
    }
    public class WifiInfo {
        private String ssid, pwd;

        public void setSsid(String ssid) {
            this.ssid = ssid;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getSsid() {
            return ssid;
        }

        public String getPwd() {
            return pwd;
        }
    }

    public class TicketInfo {
        private String username;   //用户名
        private String ticketCode;   //门票识别码
        private String useDeadLine;  //门票使用期限
        private String orderNum;      //订单号
        private int price;            //票价
        private String ticketDetail;  //门票详情
        private List<Integer> imageResId;   //门票介绍图片

        public String getUsername() {
            return username;
        }

        public String getTicketCode() {
            return ticketCode;
        }

        public String getUseDeadLine() {
            return useDeadLine;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setTicketCode(String ticketCode) {
            this.ticketCode = ticketCode;
        }

        public void setUseDeadLine(String useDeadLine) {
            this.useDeadLine = useDeadLine;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getTicketDetail() {
            return ticketDetail;
        }

        public void setTicketDetail(String ticketDetail) {
            this.ticketDetail = ticketDetail;
        }

        public void setImageResId(List<Integer> imageResId) {
            this.imageResId = imageResId;
        }

        public List<Integer> getImageResId() {
            return imageResId;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }

    public class ActiveInfo {
        private String macAddress;  //设备MAC地址
        private String ticketCode;  //用户门票识别码
        private String gatesNum;   //闸机门编号
        private int count; //赋值计数器

        public void setMacAddress(String macAddress) {
            this.macAddress = macAddress;
        }

        public void setTicketCode(String ticketCode) {
            this.ticketCode = ticketCode;
        }

        public void setGatesNum(String gatesNum) {
            this.gatesNum = gatesNum;
        }

        public String getMacAddress() {
            return macAddress;
        }

        public String getTicketCode() {
            return ticketCode;
        }

        public String getGatesNum() {
            return gatesNum;
        }
    }
}

