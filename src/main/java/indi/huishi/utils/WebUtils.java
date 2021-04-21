package indi.huishi.utils;

/**
 * @Author: Huishi
 * @Date: 2021/4/17 23:40
 */


public class WebUtils {

    /**
    将字符串转为int
     */
    public static int parseInt(String string,int defaultValue){
        try {
            System.out.println("输入的string"+string);
//            if(string!=null) {
            return Integer.parseInt(string);
//            } else{
//                System.out.println("null");
//                return defaultValue;
//            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
        return defaultValue;
    }

}
