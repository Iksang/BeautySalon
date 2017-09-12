package kr.co.tjeit.beautysalon.utils;

import android.content.Context;
import android.content.SharedPreferences;

import kr.co.tjeit.beautysalon.datas.User;

/**
 * Created by tjoeun on 2017-08-18.
 */

public class ContextUtil {

//    로그인한 사용자의 정보를 "담는데" 이용할 변수.
//    만약, loginUser가 null이라면 => 로그인한 사람이 없다? => 비 로그인 상태.
//    만약, loginuser에 어떤 객체가 들어있다면 (==null이 아니라면)
//    로그인한 사람이 있다? => 로그인 상태.

    private static User loginUser = null;

    private static final String prefName = "BeautyPref";
    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";
    private static final String USER_GENDER= "USER_GENDER";
    private static final String AUTO_LOGIN = "AUTO_LOGIN";

    public static void setUserId(Context context, String userId){
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_ID,userId).commit();
    }

    public static String getUserId(Context context){
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return  pref.getString(USER_ID,"아이디가없습니다.");

    }


    public static void setAutoLogin(Context context, boolean isAutologin){
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putBoolean(AUTO_LOGIN, isAutologin).commit();
    }

    public static boolean getAutoLogin(Context context){
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return  pref.getBoolean(AUTO_LOGIN, false);
    }

    public static void setLoginUser(Context context, String name, int gender){
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);

        pref.edit().putString(USER_NAME,name).commit();
        pref.edit().putInt(USER_GENDER,gender).commit();

        loginUser = new User();
    }

    public static User getLoginUser(Context context){
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        if(loginUser != null){
            loginUser.setName(pref.getString(USER_NAME,""));
            loginUser.setGender(pref.getInt(USER_GENDER,-1));
        }

        return loginUser;
    }

    public static void logout(Context context){
        loginUser = null;
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_NAME,"").commit();
        pref.edit().putInt(USER_GENDER,-1).commit();
    }


}
