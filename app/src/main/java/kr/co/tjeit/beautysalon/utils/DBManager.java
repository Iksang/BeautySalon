package kr.co.tjeit.beautysalon.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by tjoeun on 2017-09-12.
 */


/*
 * 용도 : 폰 내부의 Database에 직접 접근하는 코드들의 모음.
 *
 * 쿼리들이 모두 모여있다. => SQL을 별도의 클래스에 분리.
 */

public class DBManager {
//    1. DB 파일명
    static final String DATABASE_NAME = "dbfile.db";
//    ※ 반드시 확장자는 .db => SQLite에서 인식가능하게.

//    2. 테이블 이름

    static final String TABLE_USER ="Users";
    static final String TABLE_DESIGNER ="Designers";

//    3. 데이터베이스 버젼

//    앱 : 업데이트가 가능.
//    => 사용자가 다운로드 하지 않으면 적용되지 않음.
//    => DB구조가 변경될 경우, 업데이트시 반드시 명시해야함.

    static final int DB_VERSION = 1;

//    DB관련 기본정보 마무리.


//    필요변수 작성

//    1. DB => 파일, 안드로이드에서 파일 저장 : Context 객체 의존

    Context mContext;

//    2. DBManager 자신을 변수로.
//    DB는 하나의 파일. => 여러개의 프로세스가 동시에 작업하면 곤란.
//    DBManager 클래스는 단 하나의 객체만 생성되도록 강제. => Singletone (디자인패턴)

    private static DBManager mDBManager = null;

//    3. 실제로 데이터가 저장되는 데이터베이스 변수.
//    DBMS 객체 생성. (SQLite)
    private SQLiteDatabase mDatabase = null;

//    DB객체를 하나만 존재하도록 : SingleTone -> 구현

//    생성자는 private, 대신 getInstance 메쏘드를 public
//    getInstance가 하는일?
//    => 만약 mDBManager가 null이라면 새로 생성
//    => 이미 생성되어있다면, 생성되어있는 객체를 활용하도록 리턴.

//    mDBManager는 static. => 프로그램 시작시 단 한번 메모리에 올라감. 종료시까지 상주.

    public static DBManager getInstance(Context context){
        if(mDBManager == null){
            mDBManager = new DBManager(context);
        }
        return mDBManager;
    }

//    싱글톤 패턴이 완성되려면, 생성자를 아무도 콜 하지 못하게 막아야함.
//    그래야 하나의 객체로 유지.

//    생성자를 막는다 : 접근 권한을 막는다 => 아무도 접근 못하게 하려면? private

    private DBManager(Context context){

//        생성자에서 하는일?

//        1. mContext 초기화

        mContext = context;

//        2. 데이터베이스 열거나 없으면 만들기.

        mDatabase = mContext.openOrCreateDatabase(DATABASE_NAME,Context.MODE_PRIVATE,null);

//        3. 만약, DB를 열었는데 필요한 테이블이 없다면 만들어야함.
//         => mDatabase를 이용해, CREATE TABLE 구문 (SQL)을 실행
//         => DDL들을 모두 작성.

//        Users 테이블 작성 쿼리
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS "+TABLE_USER+
                        " (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "name TEXT, "+
                        "gender INTEGER, "+
                        "profileURL TEXT"+
                        ");"
        );


//        Designer 테이블 생성
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS "+TABLE_DESIGNER+
                        "(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "name TEXT, " +
                        "gender INTEGER, " +
                        "nickName TEXT, " +
                        "majorAge INTEGER"+
                        ");"
        );
    }

    public long insertUser(ContentValues addRowValues){

//        사용자 테이블에 데이터를 추가하기 위한 기능

//        ContentValues : DB에 데이터를 넣기 위한 HashMap

//        Hashmap : "키" - "값" 묶음.

        return mDatabase.insert(TABLE_USER,null,addRowValues);
    }

    public void insertUserBySQL(String name, int gender, String URL){
        mDatabase.execSQL(

                "INSERT INTO " + TABLE_USER +
                        "VALUES (" +
                        "null," +
                        "'"+name+"', "+
                        gender+", "+
                        "'"+URL+"');"
        );
    }

    public long insertDesigner(ContentValues addRowValues){

        return mDatabase.insert(TABLE_DESIGNER,null,addRowValues);
    }

//    데이터 조회 기능
//    모든 디자이너 목록 가져오기
    public Cursor getAllDesignersCursor(){

//        일반적인 CREATE TABLE, INSERT INTO 등등의 커리는 execSQL 로 실행.

//        SELECT 문 => 조회 결과를 표 형식으로 돌려받아야 함.
//        execSQL은 리턴타입이 void. (아무것도 돌려주지 않음.)
//        따라서 Cursor를 리턴해주는 rawQuery메쏘드를 이용
        return mDatabase.rawQuery("SELECT * FROM "+TABLE_DESIGNER,null);
    }

    public Cursor get20MajorDesigner(){
        return mDatabase.rawQuery("SELECT name FROM "+TABLE_DESIGNER+" WHERE majorAge = 20;",null);
    }

//    모든 디자이너 목록
    public Cursor getAllDesigners(){
        return mDatabase.rawQuery("SELECT *, avg(dc.rating) AS avgRating "+
                "FROM Designers AS d JOIN DesignCase AS dc "+
                "ON d.id = dc.designer_id "+
                "GROUP BY d.id;",null);
    }

}
