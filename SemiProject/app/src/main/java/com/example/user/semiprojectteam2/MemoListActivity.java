package com.example.user.semiprojectteam2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.user.semiprojectteam2.adapter.MemoAdapter;
import com.example.user.semiprojectteam2.data.MemberBean;
import com.example.user.semiprojectteam2.data.MemoBean;
import com.example.user.semiprojectteam2.data.SaveBean;
import com.example.user.semiprojectteam2.tab1.MemoWriteActivity;
import com.google.gson.Gson;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MemoListActivity extends AppCompatActivity {

    private ListView mLstMemo;
    private MemoAdapter mAdapter;
    public static List<MemoBean> mMemoList = new ArrayList<MemoBean>();
    private Button mBtnLoginInfo, mBtnNewMemo;
    private SaveBean mSaveBean;
    private TextView mTxtDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_list);

        mLstMemo = findViewById(R.id.lstMemo);
        mBtnLoginInfo = findViewById(R.id.btnLoginInfo);
        mBtnNewMemo = findViewById(R.id.btnNewMemo);

        mBtnLoginInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MemoListActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        mBtnNewMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(MemoListActivity.this, MemoWriteActivity.class);
                startActivity(j);
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        // 리스트 데이터를 만든다
        String jsonStr = Util.openFile(MemoListActivity.this, SaveBean.class.getName());
        Gson gson = new Gson();
        mSaveBean = gson.fromJson(jsonStr, SaveBean.class);

        // Adapter를  생성한다. (Adapter에 리스트 데이터를 넘겨준다.)
        mAdapter = new MemoAdapter(MemoListActivity.this, mSaveBean.getMemoBeanList());

        // 최종적으로 Adapter를 ListView에 세팅한다.
        mLstMemo.setAdapter(mAdapter);

    }
}
