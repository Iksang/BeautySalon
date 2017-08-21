package kr.co.tjeit.beautysalon.activity.user_activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.beautysalon.R;
import kr.co.tjeit.beautysalon.ReqPracticeActivity;
import kr.co.tjeit.beautysalon.activity.BaseActivity;
import kr.co.tjeit.beautysalon.adapters.DesignerListAdapter;
import kr.co.tjeit.beautysalon.datas.Designer;
import kr.co.tjeit.beautysalon.utils.GlobalData;

public class MainActivity extends BaseActivity {

    final int REQUEST_FOR_DESIGNER_FITER = 1;
    final int REQUEST_FOR_REQPRACTICE_ACTIVITY = 2;

    private android.widget.ListView designerListView;
    private DesignerListAdapter mAdapter;
    private android.widget.ImageView filterBtn;

    // 남자가 보여져야 하는지?
    boolean manSelect = true;
    // 여자가 보여져야 하는지?
    boolean womanSelect = true;
    // 몇점 이상의 디자이너를 보여줄건지?
    int minRating = 0;

    String nickNameSerch;

    private android.widget.Button reqTestBtn;

    // 화면에 출력되는 디자이너 목록을 담는 리스트
    List<Designer> mDisplayDesignerList = new ArrayList<>();

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
                intent.putExtra("designer", mDisplayDesignerList.get(position));
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
                intent.putExtra("최소평점",minRating);
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
                minRating = data.getIntExtra("선택된평점",0);
                nickNameSerch = data.getStringExtra("닉네임검색");

                filterAndRefreshListView();

            }

        }
        else if(requestCode == REQUEST_FOR_REQPRACTICE_ACTIVITY){
            if (resultCode == RESULT_OK){
                Log.d("seekbar",data.getIntExtra("selectAvg",-1)+"");
            }
        }

    }

    private void filterAndRefreshListView() {
        // 실제로 데이터들을 걸러주는 부분
        // 걸러준다 ? 일단 싸그리 비우고,
        // 전체적으로 다시 검사해서 조건에 맞는 객체들만 출력.
        // 출력? mDisplay리스트 에 추가해준다.

        // 일단 출력용 리스트 싹 비움.
        mDisplayDesignerList.clear();

        // 필터 => 조건에 맞는 객체들만 화면에보여준다.
        // 원본들은 보존하고, 원본을 하나하나 검사해서
        // 조건이 맞을경우 화면에 표시되도록.

        for(Designer ds : GlobalData.designers){

            // 성별이 올바른지 기록하는 boolean
            boolean genderOk = false;


            // 남자가 선택되어야 하는지?
            if(manSelect){
                if(ds.getGender()==0)
                    // 상황이 맞으므로, 성별이 올바르다고 기록.
                    genderOk = true;
            }

            // 여성에 대해 확인
            if(womanSelect){
                if(ds.getGender()==1)
                    genderOk = true;
            }


            // 최소평점보다 높은지.

            // 평점이 적절한지를 기록하는 변수
            boolean ratingOk = false;


            if(ds.getAvgRating()>=minRating){
                ratingOk = true;
            }

//           닉네임 검사

            boolean nickNameOk= false;
//            String의 기능 응용.
//            1. contains => 앞의 String이 뒤(괄호내부)의 String을 포함하는지 t/f
//            2. toLowerCase => 이 메쏘드를 실행하는 String의 모든 영문자를 소문자로.
//              cf) toUpperCase => 모든 영문자를 대문자로.

//            응용! 대소문자 구분 없이 검색하게 해보자.
//            모든걸 전부 강제로 소문자로 변환해주면.
//            대문자를 입력했건 소문자를 입력했건 전부 소문자가 되므로
//            대문자 소문자 관계 없이 검색하는게 가능해진다.
//            if (ds.getNickName().toLowerCase().contains(nickNameSerch.toLowerCase())) {
//                nickNameOk = true;
//            }
            if (ds.getNickName().toLowerCase().startsWith(nickNameSerch.toLowerCase())) {
                nickNameOk = true;
            }




            // 상황이 맞는지 재확인해서 실제로 데이터를 추가
            if(genderOk&&ratingOk&&nickNameOk){
                mDisplayDesignerList.add(ds);
            }

        }


        mAdapter.notifyDataSetChanged();

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
        // 뭐 하는 함수?
        // 액티비티가 처음 생성될 때 필요한 데이터/화면 값 설정.
        super.setValues();

        // 처음에는 조건없이 모든 디자이너를 화면에 출력해야함.
        // 화면에 표시될 List에, Global데이터의 모든 디자이너를 추가.
        mDisplayDesignerList.addAll(GlobalData.designers);

        // 글로벌 데이터를 바로 화면에 띄우는게 아니라.
        // 화면 표시용 리스트를 기반으로 나타내도록 설정.
        // => 필터 / 검색 등 임시로 결과를 추려내야할 경우에
        mAdapter = new DesignerListAdapter(mContext, mDisplayDesignerList);
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
