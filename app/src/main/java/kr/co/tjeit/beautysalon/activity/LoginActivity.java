package kr.co.tjeit.beautysalon.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import kr.co.tjeit.beautysalon.R;
import kr.co.tjeit.beautysalon.activity.user_activity.MainActivity;
import kr.co.tjeit.beautysalon.activity.worker_activity.WorkerMainActivity;
import kr.co.tjeit.beautysalon.activity.worker_activity.WorkerSignupActivity;
import kr.co.tjeit.beautysalon.utils.ContextUtil;

public class LoginActivity extends BaseActivity{


    //    직원 모드인지 아닌지 판별해주는 변수.
    boolean isWorkerMode = false;
    private TextView modeTxt;
    private EditText idEdt;
    private Button loginBtn;
    private android.widget.CheckBox autoLoginCkb;


    //    페이스북 로그인
    CallbackManager cm;
    private com.facebook.login.widget.LoginButton fbBtn;
    ProfileTracker pt;
    private Button signupBtn;


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

                ContextUtil.setUserId(mContext, idEdt.getText().toString());

                ContextUtil.setLoginUser(mContext, "A사용자", 1);

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

        autoLoginCkb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ContextUtil.setAutoLogin(mContext, isChecked);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(!isWorkerMode){
                    intent = new Intent(mContext, SignupActivity.class);
                }
                else{
                    intent = new Intent(mContext, WorkerSignupActivity.class);
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

        idEdt.setText(ContextUtil.getUserId(mContext));
        autoLoginCkb.setChecked(ContextUtil.getAutoLogin(mContext));
        cm = CallbackManager.Factory.create();
        fbBtn.registerCallback(cm, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

        pt = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                if (currentProfile != null) {
                    ContextUtil.setLoginUser(mContext, currentProfile.getName(), 0);
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                }

            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        cm.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.fbBtn = (LoginButton) findViewById(R.id.fbBtn);
        this.signupBtn = (Button) findViewById(R.id.signupBtn);
        this.loginBtn = (Button) findViewById(R.id.loginBtn);
        this.autoLoginCkb = (CheckBox) findViewById(R.id.autoLoginCkb);
        this.idEdt = (EditText) findViewById(R.id.idEdt);
        this.modeTxt = (TextView) findViewById(R.id.modeTxt);
    }
}

