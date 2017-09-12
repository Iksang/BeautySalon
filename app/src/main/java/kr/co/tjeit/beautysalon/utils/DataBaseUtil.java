package kr.co.tjeit.beautysalon.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.beautysalon.datas.Designer;

/**
 * Created by tjoeun on 2017-09-12.
 */

// 용도 : DB <-> 화면 중간 중재.
//    화면을 그릴때 : 데이터 => List, User, Designer
//    DB에서 던져주는것 : Cursor
//    중간에서 Cursor를 이용해 List, User, Designer 형태로 가공

//    데이터 삽입
//    직원 회원 가입

public class DataBaseUtil {

    public static List<Designer> getDesignersFromDB(Context context){
        List<Designer> mList = new ArrayList<>();

        Cursor c = DBManager.getInstance(context).getAllDesigners();

        if(c != null){
            while(c.moveToNext()){
                Designer d = new Designer();
                d.setName(c.getString(1));
                d.setGender(c.getInt(2));
                d.setNickName(c.getString(3));
                d.setMajorAge(c.getInt(4));
                d.setAvgRating(c.getFloat(c.getColumnIndex("avgRating")));

                mList.add(d);
            }
        }

        return mList;
    }

    public static long signupDesigner(Context context, Designer d){

        ContentValues cv = new ContentValues();
        cv.put("name",d.getName());
        cv.put("gender",d.getGender());
        cv.put("nickName",d.getNickName());
        cv.put("majorAge",d.getMajorAge());

        return DBManager.getInstance(context).insertDesigner(cv);
    }
}
