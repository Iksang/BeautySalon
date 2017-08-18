package kr.co.tjeit.beautysalon.activity.worker_activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import kr.co.tjeit.beautysalon.R;
import kr.co.tjeit.beautysalon.activity.BaseActivity;
import kr.co.tjeit.beautysalon.datas.DesignCase;
import kr.co.tjeit.beautysalon.utils.GlobalData;

public class WorkerMainActivity extends BaseActivity {



    private ListView reservationListView;
    private ArrayAdapter<DesignCase> reservationAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_main);



        bindViews();
        setupEvents();
        setValues();


    }

    @Override
    public void setupEvents() {
        super.setupEvents();
    }

    @Override
    public void setValues() {
        super.setValues();

        reservationAdapter = new ArrayAdapter<DesignCase>(mContext,
                android.R.layout.simple_list_item_1,
                GlobalData.globalDesignCase);

        reservationListView.setAdapter(reservationAdapter);



    }

    @Override
    public void bindViews() {
        super.bindViews();
        this.reservationListView = (ListView) findViewById(R.id.reservationListView);

    }


}
