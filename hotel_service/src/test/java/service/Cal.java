package service;

import java.util.Calendar;

/**
 * Title: todoedit
 * Description: todoedit
 * author: wenjun
 * date: 2018/5/29 9:33
 */
public class Cal {
    public static void main(String[] args) {
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(2018,4,1,0,0,0);
        end.set(2018,4,2,0,0,0);
        long sM = start.getTimeInMillis();
        long eM = end.getTimeInMillis();
        System.out.println(sM);
        System.out.println(eM);
        long temp = eM-sM;
        System.out.println(temp);

    }
}
