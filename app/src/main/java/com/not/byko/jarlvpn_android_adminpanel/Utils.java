package com.not.byko.jarlvpn_android_adminpanel;

import java.util.Calendar;

public class Utils {

    public static Boolean checkJwtBeforeRequest(String jwt, long jwtExpireDate){
        if(!jwt.equals("") && jwtExpireDate != 0){

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(jwtExpireDate);
            calendar.add(Calendar.HOUR, 24);

            if(calendar.getTime().getTime() > System.currentTimeMillis()){
                return true;
            }
        }
        return false;
    }
}
