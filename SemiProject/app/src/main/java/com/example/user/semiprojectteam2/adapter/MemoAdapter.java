package com.example.user.semiprojectteam2.adapter;

/**
 * Created by user on 2018-07-17.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.BaseAdapter;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.semiprojectteam2.MemoListActivity;
import com.example.user.semiprojectteam2.R;
import com.example.user.semiprojectteam2.data.MemberBean;
import com.example.user.semiprojectteam2.data.MemoBean;
import com.example.user.semiprojectteam2.UpdateActivity;
import com.example.user.semiprojectteam2.data.SaveBean;
import com.example.user.semiprojectteam2.data.Util;
import com.example.user.semiprojectteam2.tab1.MemoWriteActivity;
import com.example.user.semiprojectteam2.tab2.MemoDetailActivity;
import com.google.gson.Gson;

import java.util.List;

public class MemoAdapter extends BaseAdapter {

    // 멤버변수수
    private Context mContext;
    private List<MemoBean> mList;
    private LayoutInflater mInflater;

    private EditText mEdtText;

    // 생성자
    public MemoAdapter(Context context, List<MemoBean> list) {
        mContext = context;
        mList = list;
        // 인플레이터 서비스 이용
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        // 리스트 갯수
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        // 고유의 데이터가 넘어감
        return mList.get(position);
    }

    @Override
    public long getItemId(int i) {
        // 고유의 인덱스 번호
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 인플레이터로 뷰를 가져온다.
        convertView = mInflater.inflate(R.layout.list_view, null);


        final MemoBean bean = mList.get(position);

        TextView txtMemo = convertView.findViewById(R.id.txtMemo);
        txtMemo.setText(bean.getMemo());

        ImageView imgView = convertView.findViewById(R.id.imgProfile);
        if(bean.getImgPath() != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(bean.getImgPath());
            imgView.setImageBitmap(bitmap);
        }
        TextView txtDate = convertView.findViewById(R.id.txtDate);

        txtDate.setText( bean.getRegDate() );

        mEdtText = convertView.findViewById(R.id.edtText); // 이름 일치하면 오류 풀릴 것

        Button btnUpdate = convertView.findViewById(R.id.btnUpdate);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);
        Button btnView = convertView.findViewById(R.id.btnView);

        btnUpdate.setTag(position);
        btnDelete.setTag(position);
        btnView.setTag(position);

        // 삭제 버튼 이벤트
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemoBean memoBean = new MemoBean();

                Gson gson = new Gson();

                String jsonStr = Util.openFile(mContext, SaveBean.class.getName());
                SaveBean saveBean = gson.fromJson(jsonStr, SaveBean.class);

                // 선택된 삭제 버튼의 메모된 인덱스 번호를 취득
                Integer selIdx = (Integer) v.getTag();

                if (selIdx != null) {
                    //화면상 삭제
                    mList.remove(selIdx.intValue()); // 해당 인덱스의 데이터 삭제

                    //저장
                    saveBean.getMemoBeanList().remove(selIdx.intValue());
                    String jsonStr2 = gson.toJson(saveBean);
                    Util.saveFile(mContext,SaveBean.class.getName(),jsonStr2);

                    MemoAdapter.this.notifyDataSetInvalidated(); // 어댑터 리프레쉬
                }
            }
        });

        // 수정 버튼 이벤트
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer selIdx = (Integer) v.getTag();

                if (selIdx != null) {

                    // 선택된 행(row)의 인덱스 번호의 데이터를 취득해 MemoBean으로!
                    MemoBean bean = mList.get(selIdx.intValue());

                    // 선택된 행의 인덱스 값을 보낸다.
                    bean.setSelIdx(selIdx.intValue());

                    // 꺼내온 데이터를 Intent에 실어 보낸다.
                    Intent i = new Intent(mContext, MemoWriteActivity.class); // 이름 일치하면 오류 풀릴 것
                    i.putExtra(MemoBean.class.getName(), bean);
                    mContext.startActivity(i);
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer selIdx = (Integer) v.getTag();

                if (selIdx != null) {

                    // 선택된 행(row)의 인덱스 번호의 데이터를 취득해 MemoBean으로!
                    MemoBean bean = mList.get(selIdx.intValue());

                    // 선택된 행의 인덱스 값을 보낸다.
                    bean.setSelIdx(selIdx.intValue());

                    Intent j = new Intent(mContext, MemoDetailActivity.class);
                    j.putExtra(MemoBean.class.getName(), bean);
                    mContext.startActivity(j);
                }
            }
        });

        txtMemo.setText(bean.getMemo());

        return convertView;
    }
}