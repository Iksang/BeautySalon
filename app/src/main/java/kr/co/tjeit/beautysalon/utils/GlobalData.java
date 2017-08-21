package kr.co.tjeit.beautysalon.utils;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.beautysalon.datas.DesignCase;
import kr.co.tjeit.beautysalon.datas.Designer;
import kr.co.tjeit.beautysalon.datas.User;

/**
 * Created by tjoeun on 2017-07-27.
 */

public class GlobalData {
    // 앱에서 공통적으로 사용되는 데이터를 임시 저장하는 클래스
    // 대부분의 변수/메쏘드는 public static으로
    // GlobalData.메쏘드(), GlobalData 변수

    public static List<DesignCase> globalDesignCase = new ArrayList<>();
    public static List<Designer> designers = new ArrayList<>();
    public static List<User> users = new ArrayList<>();

//    로그인한 사용자의 데이터. (임시로 생성)
    public static User loginUser = new User();
}
