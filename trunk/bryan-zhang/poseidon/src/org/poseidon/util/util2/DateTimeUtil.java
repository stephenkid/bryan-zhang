package org.poseidon.util.util2;

import java.sql.Timestamp;
import java.util.Date;

/**
 * DateTimeUtil
 * 
 * @author 何晓滨
 * @version 创建时间：Sep 1, 2008 3:00:29 PM
 */
public abstract class DateTimeUtil {

    /**
     * 得到当前时间（Date类型）
     * @return 当前时间（Date类型）
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /**
     * 得到当前时间（Timestamp类型）
     * @return 当前时间（Timestamp类型）
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 得到当前时间（19位长度的String类型）
     * @return 当前时间（19位长度的String类型）
     */
    public static String getCurrentTime() {
        return getCurrentTimestamp().toString().substring(0, 19);
    }
    
    public static String getCurrentTime(Timestamp timeStamp) {
        return timeStamp.toString().substring(0, 19);
    }
    
    public static String getStringTime() {
         String time = getCurrentTimestamp().toString().substring(0, 19);
         return time.substring(0,4)+time.substring(5,7)+time.substring(8,10)
         +time.substring(11,13)+time.substring(14,16)+time.substring(17,19);
    }

    public static void main(String[] args){
    	System.out.println(getCurrentDate());
    	System.out.println(getCurrentTimestamp());
    	System.out.println(getCurrentTime());
    	System.out.println(getStringTime());
    }
}
