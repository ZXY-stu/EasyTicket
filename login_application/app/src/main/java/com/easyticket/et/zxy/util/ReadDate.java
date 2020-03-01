package com.easyticket.et.zxy.util;

/**
 * Created by zxy on 2018/3/15.
 */

public class ReadDate{
        public static   ReadDate readDate;
        private ReadDate(){
        }
        public  static  ReadDate builder(){
              create();
              return  readDate;
        }
        private static void create(){
            if(readDate == null)
            readDate = new ReadDate();
        }

        public String getTicketCode(){
            return null;
        }

}
