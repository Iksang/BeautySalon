package kr.co.tjeit.beautysalon.datas;

import java.io.Serializable;
import java.util.Calendar;

import kr.co.tjeit.beautysalon.utils.DateTimeUtil;

/**
 * Created by tjoeun on 2017-07-26.
 */

public class DesignCase implements Serializable {
    private int caseImgId;
    private Calendar createdOn;
    private int userRating;
    private Designer designer;
    private User user;
    private int cost;
    private String userReview;

    public DesignCase() {
    }

    public DesignCase(int caseImgId, Calendar createdOn, int userRating, Designer designer, User user, int cost, String userReview) {
        this.caseImgId = caseImgId;
        this.createdOn = createdOn;
        this.userRating = userRating;
        this.designer = designer;
        this.user = user;
        this.cost = cost;
        this.userReview = userReview;
    }

    public int getCaseImgId() {
        return caseImgId;
    }

    public void setCaseImgId(int caseImgId) {
        this.caseImgId = caseImgId;
    }

    public Calendar getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Calendar createdOn) {
        this.createdOn = createdOn;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public Designer getDesigner() {
        return designer;
    }

    public void setDesigner(Designer designer) {
        this.designer = designer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getUserReview() {
        return userReview;
    }

    public void setUserReview(String userReview) {
        this.userReview = userReview;
    }

    @Override
    public String toString() {
        String dateTimeStr = DateTimeUtil.getDateTimeString(createdOn);
        String str = this.user.getName() + " / " + dateTimeStr;
        return str;

    }
}
