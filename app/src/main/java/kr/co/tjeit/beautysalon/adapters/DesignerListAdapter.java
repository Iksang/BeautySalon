package kr.co.tjeit.beautysalon.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjeit.beautysalon.R;
import kr.co.tjeit.beautysalon.datas.DesignCase;
import kr.co.tjeit.beautysalon.datas.Designer;
import kr.co.tjeit.beautysalon.utils.DateTimeUtil;

/**
 * Created by tjoeun on 2017-08-18.
 */

public class DesignerListAdapter extends ArrayAdapter<Designer>{
    private Context mContext; // 어느화면에서 써먹는지
    // 이를 기록해두면 종종 활용하게 됨. 써먹을데가 많다.
    private List<Designer> mList; // 표시할 데이터 배열
    // 접근하는 장소가 많아서 멤버변수로 빼서 접근이 용이하게.
    private LayoutInflater inf; // ~~_list_item.xml 파일을 그리는 역할
    // 메모리 절약을 위해서 멤버변수로 빼냄
    // 한번 생성하고, 재활용 계속 하려고.



    public DesignerListAdapter(Context context, List<Designer> list) {
        super(context, R.layout.designer_list_item, list);
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

            row = inf.inflate(R.layout.designer_list_item, null);
            // LayoutInflater를 이용하여 리스트뷰의 한 줄을 그려내는 방법

        }

        Designer data = mList.get(position);

        ImageView designerProfileImg = (ImageView)row.findViewById(R.id.designerProfileImg);
        TextView designerNickNameTxt = (TextView)row.findViewById(R.id.designerNickNameTxt);
        TextView designerNameTxt = (TextView)row.findViewById(R.id.designerNameTxt);
        TextView majorAgeTxt = (TextView)row.findViewById(R.id.majorAgeTxt);
        TextView genderDesignerTxt = (TextView)row.findViewById(R.id.genderDesignerTxt);

        designerNickNameTxt.setText(data.getName()+"("+data.getNickName()+")");
        designerNameTxt.setText("");
        majorAgeTxt.setText("주력분야 : "+data.getMajorAge()+"대");
        if(data.getGender()==0){
            genderDesignerTxt.setText("남자");
        }
        else{
            genderDesignerTxt.setText("여자");
        }

        ArrayList<ImageView> stars = new ArrayList<>();
        stars.clear();
        stars.add((ImageView)row.findViewById(R.id.star1));
        stars.add((ImageView)row.findViewById(R.id.star2));
        stars.add((ImageView)row.findViewById(R.id.star3));
        stars.add((ImageView)row.findViewById(R.id.star4));
        stars.add((ImageView)row.findViewById(R.id.star5));

        for(ImageView v : stars) {
            v.setVisibility(View.INVISIBLE);
        }

        for(int i = 0 ; i < data.getAvgRating() ; i++){
            stars.get(i).setVisibility(View.VISIBLE);
        }

        return row;


    }
}
