package kr.co.tjeit.beautysalon.activity;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

import kr.co.tjeit.beautysalon.R;
import kr.co.tjeit.beautysalon.activity.BaseActivity;
import kr.co.tjeit.beautysalon.utils.DBManager;

public class SignupActivity extends BaseActivity {

    private android.widget.EditText nameEdt;
    private android.widget.RadioButton manBtn;
    private android.widget.RadioButton womanBtn;
    private android.widget.RadioGroup radioGroup;
    private android.widget.Button okBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DBManager를 이용하여, Users 클래스에 데이터를 추가.
//                DBManager 안의 insertuser를 활용.


//                ContentValue의 활용법

//                cv.put으로 데이터를 추가
//                추가되는 데이터 : "테이블의 컬럼명", "실제 데이터"

                ContentValues cv = new ContentValues();
                cv.put("name",nameEdt.getText().toString());
                if(manBtn.isChecked()){
                    cv.put("gender",0);
                }
                else if(womanBtn.isChecked()){
                    cv.put("gender",1);
                }
                else{
                    Toast.makeText(mContext, "성별을 선택하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                cv.put("profileURL","tempURL");

                long insertedId = DBManager.getInstance(mContext).insertUser(cv);
                Toast.makeText(mContext, String.format(Locale.KOREA,"%d번 사용자 가입 완료",insertedId), Toast.LENGTH_SHORT).show();
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
        this.okBtn = (Button) findViewById(R.id.okBtn);
        this.radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        this.womanBtn = (RadioButton) findViewById(R.id.womanBtn);
        this.manBtn = (RadioButton) findViewById(R.id.manBtn);
        this.nameEdt = (EditText) findViewById(R.id.nameEdt);
    }
}
