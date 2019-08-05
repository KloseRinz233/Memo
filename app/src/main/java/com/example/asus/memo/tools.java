package com.example.asus.memo;

import java.util.Arrays;

public class tools {
    public static String[] createDay (int d){
        String[] day=new String[d];
        for(int i=1;i<=d;i++){
            day[i-1]=String.valueOf(i);
        }
        return day;
    }
    public static int checkMonth (int y,int m){
       if(m==1||m==3||m==5||m==7||m==8||m==10||m==12)
           return 31;
        if(m==4||m==6||m==9||m==11)
            return 30;
        if(m==2){
            if((y%4==0&&y%100!=0)||y%400==0)
                return 29;
            else
                return 28;
        }
        else
            return 0;
    }
}
