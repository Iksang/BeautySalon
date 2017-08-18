package kr.co.tjeit.beautysalon.activity.user_activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import kr.co.tjeit.beautysalon.R;
import kr.co.tjeit.beautysalon.ReqPracticeActivity;
import kr.co.tjeit.beautysalon.activity.BaseActivity;
import kr.co.tjeit.beautysalon.adapters.DesignerListAdapter;
import kr.co.tjeit.beautysalon.utils.GlobalData;

public class MainActivity extends BaseActivity {

    int REQUEST_FOR_DESIGNER_FITER = 1;
    int REQUEST_FOR_REQPRACTICE_ACTIVITY = 2;

    private android.widget.ListView designerListView;
    private DesignerListAdapter mAdapter;
    private android.widget.ImageView filterBtn;

    boolean manSelect = true;
    boolean womanSelect = true;
    private android.widget.Button reqTestBtn;

    // 일반 사용자가 로그인 했을때 나타나는 화면

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setupEvents();
        setValues();



    }

    @Override
    public void setupEvents() {
        super.setupEvents();


        designerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, ViewDesignerDetailActivity.class);
                intent.putExtra("designer", GlobalData.designers.get(position));
                startActivity(intent);
            }
        });



        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DesignerFilterActivity.class);
//                startActivity(intent);
//                데이터를 돌려받기 위한 startActivity
                intent.putExtra("남성선택",manSelect);
                intent.putExtra("여성선택",womanSelect);
                startActivityForResult(intent,REQUEST_FOR_DESIGNER_FITER);
            }
        });

        reqTestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ReqPracticeActivity.class);
                startActivityForResult(intent,REQUEST_FOR_REQPRACTICE_ACTIVITY);
            }
        });

    }
    
//    언젠가될진 모르지만, 언젠가 데이터를 돌려받는다.
//    돌려받는 이벤트에 대응 되는 메쏘드를 구현
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        어떤 요청이었는지.
        if(requestCode == REQUEST_FOR_DESIGNER_FITER){
//            성별 필터에 달아두었던 1번 요청.
            if(resultCode==RESULT_OK){
//                Toast.makeText(mContext, "성별 필터 확인.", Toast.LENGTH_SHORT).show();
                manSelect = data.getBooleanExtra("남자선택여부",true);
                womanSelect= data.getBooleanExtra("여자선택여부",true);

                Toast.makeText(mContext, "남자 : "+manSelect+", 여자 : "+womanSelect, Toast.LENGTH_SHORT).show();
            }

        }

    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode == REQUEST_FOR_REQPRACTICE_ACTIVITY){
//            if(resultCode==RESULT_OK){
//                Toast.makeText(mContext, data.getStringExtra("requestPractice"), Toast.LENGTH_SHORT).show();
//
//
//            }
//        }
//    }

    @Override
    public void setValues() {
        super.setValues();
        mAdapter = new DesignerListAdapter(mContext, GlobalData.designers);
        designerListView.setAdapter(mAdapter);
    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.designerListView = (ListView) findViewById(R.id.designerListView);
        this.filterBtn = (ImageView) findViewById(R.id.filterBtn);
        this.reqTestBtn = (Button) findViewById(R.id.reqTestBtn);

    }
}
