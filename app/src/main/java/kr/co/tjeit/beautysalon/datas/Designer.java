package kr.co.tjeit.beautysalon.datas;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by tjoeun on 2017-07-26.
 */

public class Designer extends Person implements Serializable {

    private String nickName;
    private int majorAge;
    private float avgRating;
    private ArrayList<DesignCase> portfolio;


    // 1. Person 상속 받게
    // 2. 상속받고 있는 (중복되는) 변수들은 지워주자.
    // 3. getter / setter
    // 4. 생성자 (이름, 성별 추가로 입력받는)
    // 5. 아무것도 안받는 생성자


    public Designer() {

    }

    public Designer(String name, int gender, String nickName, int majorAge, float avgRating, ArrayList<DesignCase> portfolio) {
        setName(name);
        setGender(gender);
        this.nickName = nickName;
        this.majorAge = majorAge;
        this.avgRating = avgRating;
        this.portfolio = portfolio;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getMajorAge() {
        return majorAge;
    }

    public void setMajorAge(int majorAge) {
        this.majorAge = majorAge;
    }

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public ArrayList<DesignCase> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(ArrayList<DesignCase> portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public String toString() {
        String returningStr = this.nickName + " / " + this.avgRating;
        return returningStr;
    }
}
