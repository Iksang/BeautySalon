package kr.co.tjeit.beautysalon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import kr.co.tjeit.beautysalon.R;

public class SelectLoginModeActivity extends BaseActivity {


    private Button userLoginBtn;
    private Button workerLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login_mode);

        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        // 1. 사용자 로그인 버튼을 눌렀을때?
        userLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 실제로 해야될 일 -> 로그인 화면으로 이동
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.putExtra("직원모드",false);
                startActivity(intent);
            }
        });

        workerLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.putExtra("직원모드",true);
                startActivity(intent);

            }
        });
    }

    @Override
    public void setValues() {
        super.setValues();
    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.workerLoginBtn = (Button) findViewById(R.id.workerLoginBtn);
        this.userLoginBtn = (Button) findViewById(R.id.userLoginBtn);
    }
}
