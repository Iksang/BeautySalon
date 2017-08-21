package kr.co.tjeit.beautysalon.activity.user_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import kr.co.tjeit.beautysalon.R;
import kr.co.tjeit.beautysalon.activity.BaseActivity;

public class MakeReviewActivity extends BaseActivity {

    private android.widget.EditText reviewEdt;
    private android.widget.Button okBtn;
    private android.widget.RadioGroup ratingRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_review);
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

                if(ratingRG.getCheckedRadioButtonId() != -1){
                    Intent intent = new Intent();
                    intent.putExtra("리뷰내용", reviewEdt.getText().toString());
                    int rating = ratingRG.indexOfChild(findViewById(ratingRG.getCheckedRadioButtonId()))+1;
                    intent.putExtra("평점",rating);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else{
                    Toast.makeText(mContext, "평점을 선택해주세요", Toast.LENGTH_SHORT).show();
                }
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
        this.reviewEdt = (EditText) findViewById(R.id.reviewEdt);
        this.ratingRG = (RadioGroup) findViewById(R.id.ratingRG);
    }

}
