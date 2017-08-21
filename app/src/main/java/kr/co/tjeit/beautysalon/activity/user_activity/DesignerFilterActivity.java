package kr.co.tjeit.beautysalon.activity.user_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import kr.co.tjeit.beautysalon.R;
import kr.co.tjeit.beautysalon.activity.BaseActivity;

public class DesignerFilterActivity extends BaseActivity {

    private android.widget.Button okBtn;
    private android.widget.ToggleButton manSelectToggleBtn;
    private android.widget.ToggleButton womanSelectToggleBtn;

    boolean manOk = true;
    boolean womanOk = true;
    private android.widget.SeekBar ratingSeekbar;
    private android.widget.TextView minAvgTxt;
    private int minRating = 0;
    private android.widget.EditText nickNameEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designer_filter);



        manOk = getIntent().getBooleanExtra("남성선택",true);
        womanOk = getIntent().getBooleanExtra("여성선택",true);
        minRating = getIntent().getIntExtra("최소평점", 0);

        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        super.setupEvents();

//        확인 버튼을 눌렀을때, 일단 화면을 종료

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent finishIntent = new Intent();
//                남주 포함 여부 첨부함
                finishIntent.putExtra("남자선택여부",manSelectToggleBtn.isChecked());
//                여자 포함 여부 첨부
                finishIntent.putExtra("여자선택여부",womanSelectToggleBtn.isChecked());
//                최소 평점 첨부
                finishIntent.putExtra("선택된평점", ratingSeekbar.getProgress());
//                입력된 닉네임 첨부
                finishIntent.putExtra("닉네임검색", nickNameEdt.getText().toString());
//                결과 설정 (확인, 첨부데이터)
                setResult(RESULT_OK, finishIntent);
                finish();
            }
        });

        ratingSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minAvgTxt.setText(progress + "");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    @Override
    public void setValues() {
        manSelectToggleBtn.setChecked(manOk);
        womanSelectToggleBtn.setChecked(womanOk);
        ratingSeekbar.setProgress(minRating);
        super.setValues();
    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.nickNameEdt = (EditText) findViewById(R.id.nickNameEdt);
        this.minAvgTxt = (TextView) findViewById(R.id.minAvgTxt);
        this.ratingSeekbar = (SeekBar) findViewById(R.id.ratingSeekbar);
        this.womanSelectToggleBtn = (ToggleButton) findViewById(R.id.womanSelectToggleBtn);
        this.manSelectToggleBtn = (ToggleButton) findViewById(R.id.manSelectToggleBtn);
        this.okBtn = (Button) findViewById(R.id.okBtn);
    }
}
