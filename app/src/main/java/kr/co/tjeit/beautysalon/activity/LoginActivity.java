package kr.co.tjeit.beautysalon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import kr.co.tjeit.beautysalon.R;
import kr.co.tjeit.beautysalon.activity.user_activity.MainActivity;
import kr.co.tjeit.beautysalon.activity.worker_activity.WorkerMainActivity;

public class LoginActivity extends BaseActivity{


//    직원 모드인지 아닌지 판별해주는 변수.
    boolean isWorkerMode = false;
    private TextView modeTxt;
    private EditText idEdt;
    private Button loginBtn;
    private android.widget.CheckBox autoLoginCkb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        isWorkerMode = getIntent().getBooleanExtra("직원모드",false);
        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                if(isWorkerMode){
                    intent = new Intent(mContext,WorkerMainActivity.class);
                }
                else{
                    intent = new Intent(mContext,MainActivity.class);
                }
                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {
        super.setValues();
        if(isWorkerMode){
            modeTxt.setVisibility(View.VISIBLE);
        }
        else{
            modeTxt.setVisibility(View.GONE);
        }
    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.autoLoginCkb = (CheckBox) findViewById(R.id.autoLoginCkb);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
        this.modeTxt = (TextView) findViewById(R.id.modeTxt);
    }
}

