package kr.co.tjeit.beautysalon.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.co.tjeit.beautysalon.R;
import kr.co.tjeit.beautysalon.datas.DesignCase;
import kr.co.tjeit.beautysalon.datas.Designer;

/**
 * Created by tjoeun on 2017-08-21.
 */

public class ReviewAdapter extends ArrayAdapter<DesignCase>{
    private Context mContext; // 어느화면에서 써먹는지
    // 이를 기록해두면 종종 활용하게 됨. 써먹을데가 많다.
    private List<DesignCase> mList; // 표시할 데이터 배열
    // 접근하는 장소가 많아서 멤버변수로 빼서 접근이 용이하게.
    private LayoutInflater inf; // ~~_list_item.xml 파일을 그리는 역할
    // 메모리 절약을 위해서 멤버변수로 빼냄
    // 한번 생성하고, 재활용 계속 하려고.



    public ReviewAdapter(Context context, List<DesignCase> list) {
        super(context, R.layout.portfolio_list_item, list);
        // 1. 어디서 표시할 건지 -> context
        // 2. 어떤 모양으로 보여줄건지 -> ?? layout에다가 새 xml 만들어서 가져다 넣음
        // 3. 어떤 데이터들을 보여줄건지 -> list

        // 생성자 만들고, super(3가지 파라미터) 를 대응해줘야 에러가 안남.
        // 우선을 에러가 안나게 이 두가지 작업부터 코딩.
        // layout이 필요해져서, res -> layout에 new layout resource file도 해준다.

        mContext = context; // 어디에서 사용되는지를 자체 기록
        mList = list; // 어떤 데이터를 뿌려줄지도 자체 기록
        inf = LayoutInflater.from(mContext); // xml을 그려주는 객체를 초기화

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        // super.getView를 그대로 실행하면, 원하는 위치에 원하는 데이터를 찍어넣는게 불가능.
        // 커스터마이징 할때는 super 문장을 전부 삭제하고,
        // 처음부터 직접 코딩.


        // 커스터마이징의 알파와 오메가
        // 만들어둔 layout 파일에 유효한 데이터를 출력시켜주는 부분
        View row = convertView;

        if(row == null) {
            // layout을 새로 그려줘야하는 경우
            // 새로 그려준다 : 재활용 할 수 없는 타이밍
            // 리스트뷰가 처음 그려져야 하는 경우

            row = inf.inflate(R.layout.portfolio_list_item, null);
            // LayoutInflater를 이용하여 리스트뷰의 한 줄을 그려내는 방법

        }

        DesignCase data = mList.get(position);

        ImageView profileImg = (ImageView)row.findViewById(R.id.profileImg);
        Glide.with(mContext).load(data.getUser().getProfilePicurePath()).into(profileImg);
        // xml에는 분명 CircleImageView 형태로 정의됨.
        // 그런데 자바에서 ImageView 형태로 저장을 하고
        // 심지어 ImageView형태로 캐스팅까지 하는데도 아무 문제가 없다.
//        왜? => 1. JAVA의 객체지향적 특성중 하나인, 다형성.
//        2. CircleImageView extends ImageView

//        Java의 객체지향으로서의 특징 3가지. 1)상속 2)캡슐화 3)다형성

//        다형성.? => 여러가지 형태로 존재할 수 있다.

//        다형성 1. Overloading
//        다형성 2. 부모는 자식을 담아둘 수 있다.

//        라이브러리 1. 인터넷 이미지 불러오기 => 이미지 로더! AUIL, Piccaso
//        라이브러리 2. 동그란 이미지뷰 => CircleImageView
//        개발의 폭이 매우 넓어진다. => 라이브러리 : 다양한 종류의 레고 블럭

//        라이브러리 단점 : 깊이가 안깊어진다
//        라이브러리 공부. => 분석

        TextView nameTxt = (TextView)row.findViewById(R.id.nameTxt);
        nameTxt.setText(data.getUser().getName());

        TextView reviewTxt = (TextView)row.findViewById(R.id.reviewTxt);
        reviewTxt.setText(data.getUserReview());

        TextView createdOnTxt = (TextView)row.findViewById(R.id.createdOnTxt);
//        날짜를 (Calendar를) String으로 출력하고자 할떄, SimpleDateFormat을 활용
//        생성자에, 원하는 출력 형태를 넣어주면됨.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        만들어진 날짜형태를 이용해, String을 만들어준다. dateFormat.format(원하는Calendar.getTime)
        String dateStr = dateFormat.format(data.getCreatedOn().getTime());
        createdOnTxt.setText(dateStr);

        ImageView star1 = (ImageView)row.findViewById(R.id.star1);
        ImageView star2 = (ImageView)row.findViewById(R.id.star2);
        ImageView star3 = (ImageView)row.findViewById(R.id.star3);
        ImageView star4 = (ImageView)row.findViewById(R.id.star4);
        ImageView star5 = (ImageView)row.findViewById(R.id.star5);


        ImageView[] stars = {star1,star2,star2,star3,star4,star5};

        for(ImageView iv : stars){
            iv.setVisibility(View.GONE);
        }

//        필요한 만큼 출력
        int starCount = data.getUserRating();

        for(int i = 0 ; i<starCount ; i++){
            stars[i].setVisibility(View.VISIBLE);
        }

        return row;


    }
}
