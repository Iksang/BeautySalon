package kr.co.tjeit.beautysalon.activity.user_activity;

import android.os.Bundle;
import android.widget.TextView;

import kr.co.tjeit.beautysalon.R;
import kr.co.tjeit.beautysalon.activity.BaseActivity;
import kr.co.tjeit.beautysalon.utils.ContextUtil;

public class MyProfileActivity extends BaseActivity {

    private android.widget.TextView userIdTxt;
    private TextView userNameTxt;
    private TextView userGenderTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);


        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {
        super.setupEvents();
    }

    @Override
    public void setValues() {
        super.setValues();
        userIdTxt.setText(ContextUtil.getUserId(mContext));
        userNameTxt.setText(ContextUtil.getLoginUser(mContext).getName());
        if(ContextUtil.getLoginUser(mContext).getGender()==0){
            userGenderTxt.setText("남성");
        }
        else{
            userGenderTxt.setText("여성");
        }
    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.userGenderTxt = (TextView) findViewById(R.id.userGenderTxt);
        this.userNameTxt = (TextView) findViewById(R.id.userNameTxt);
        this.userIdTxt = (TextView) findViewById(R.id.userIdTxt);
    }
}
