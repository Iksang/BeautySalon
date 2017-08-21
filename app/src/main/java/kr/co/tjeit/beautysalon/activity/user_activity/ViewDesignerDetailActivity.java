package kr.co.tjeit.beautysalon.activity.user_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import kr.co.tjeit.beautysalon.R;
import kr.co.tjeit.beautysalon.activity.BaseActivity;
import kr.co.tjeit.beautysalon.adapters.ReviewAdapter;
import kr.co.tjeit.beautysalon.datas.DesignCase;
import kr.co.tjeit.beautysalon.datas.Designer;
import kr.co.tjeit.beautysalon.utils.GlobalData;

public class ViewDesignerDetailActivity extends BaseActivity {

    private Designer mDesigner = null;
    private android.widget.TextView nameTxt;
    private android.widget.TextView genderTxt;
    private android.widget.TextView nickNameTxt;
    private android.widget.TextView majorAgeTxt;
    private android.widget.TextView ratingTxt;
    private android.widget.ImageView star1;
    private android.widget.ImageView star2;
    private android.widget.ImageView star3;
    private android.widget.ImageView star4;
    private android.widget.ImageView star5;
    private android.widget.ListView reviewListView;
    private android.widget.Button makeReviewBtn;
    private android.widget.Button reservationBtn;

    private ReviewAdapter mAdapter;

    final int REQUEST_FOR_REVIEW = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_designer_detail);

        mDesigner = (Designer)getIntent().getSerializableExtra("designer");

        bindViews();
        setupEvents();
        setValues();

    }

    @Override
    public void setupEvents() {
        super.setupEvents();
        makeReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MakeReviewActivity.class);
                startActivityForResult(intent, REQUEST_FOR_REVIEW);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_FOR_REVIEW){
            if(resultCode == RESULT_OK){
                String reviewStr = data.getStringExtra("리뷰내용");
                int ratingNum = data.getIntExtra("평점",0);

//                이 화면에 나타난 디자이너의 리뷰목록을 하나 추가해주고
//                그 리스트뷰를 새로고침.
                DesignCase tempCase = new DesignCase(R.drawable.salon_logo,
                        Calendar.getInstance(),ratingNum+1, mDesigner, GlobalData.loginUser,15000,reviewStr);
                mDesigner.getPortfolio( ).add(tempCase);
                mAdapter.notifyDataSetChanged();

//                애니메이션을 이용해, 마지막칸으로 이동시켜주는 기능.
//                reviewListView.smoothScrollToPosition(mAdapter.getCount() - 1);
                reviewListView.smoothScrollToPosition(mDesigner.getPortfolio().size() - 1);

            }
        }

    }

    @Override
    public void setValues() {
        super.setValues();
        nameTxt.setText(mDesigner.getName());
        if(mDesigner.getGender()==0){
            genderTxt.setText("남자");
        }
        else if(mDesigner.getGender()==1){
            genderTxt.setText("여자");
        }
        nickNameTxt.setText(mDesigner.getNickName());
        majorAgeTxt.setText(mDesigner.getMajorAge()+"대");
        List<ImageView> stars = new ArrayList<>();
        stars.clear();
        stars.add(star1);
        stars.add(star2);
        stars.add(star3);
        stars.add(star4);
        stars.add(star5);
        for(ImageView iv : stars){
            iv.setVisibility(View.GONE);
        }
        for(int i = 0 ;  i < mDesigner.getAvgRating() ; i++){
            stars.get(i).setVisibility(View.VISIBLE);
        }

        mAdapter = new ReviewAdapter(mContext, mDesigner.getPortfolio());
        reviewListView.setAdapter(mAdapter);


    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.reservationBtn = (Button) findViewById(R.id.reservationBtn);
        this.makeReviewBtn = (Button) findViewById(R.id.makeReviewBtn);
        this.reviewListView = (ListView) findViewById(R.id.reviewListView);
        this.star5 = (ImageView) findViewById(R.id.star5);
        this.star4 = (ImageView) findViewById(R.id.star4);
        this.star3 = (ImageView) findViewById(R.id.star3);
        this.star2 = (ImageView) findViewById(R.id.star2);
        this.star1 = (ImageView) findViewById(R.id.star1);
        this.ratingTxt = (TextView) findViewById(R.id.ratingTxt);
        this.majorAgeTxt = (TextView) findViewById(R.id.majorAgeTxt);
        this.nickNameTxt = (TextView) findViewById(R.id.nickNameTxt);
        this.genderTxt = (TextView) findViewById(R.id.genderTxt);
        this.nameTxt = (TextView) findViewById(R.id.nameTxt);
    }
}
