package kr.co.tjeit.beautysalon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import kr.co.tjeit.beautysalon.activity.BaseActivity;

public class ReqPracticeActivity extends BaseActivity {

    private android.widget.EditText inputEdt;
    private android.widget.Button okBtn;
    private SeekBar ratingSeekbar;
    private Button ratingSelectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_practice);


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
                Intent intent = new Intent();
                intent.putExtra("requestPractice", inputEdt.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });


        ratingSelectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("selectAvg", ratingSeekbar.getProgress());
                setResult(RESULT_OK, intent);
                finish();
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
        this.ratingSelectBtn = (Button) findViewById(R.id.ratingSelectBtn);
        this.ratingSeekbar = (SeekBar) findViewById(R.id.ratingSeekbar);
        this.okBtn = (Button) findViewById(R.id.okBtn);
        this.inputEdt = (EditText) findViewById(R.id.inputEdt);
    }
}
