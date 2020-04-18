package com.example.user.semiprojectteam2.tab1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.semiprojectteam2.LoginActivity;
import com.example.user.semiprojectteam2.R;
import com.example.user.semiprojectteam2.Util;
import com.example.user.semiprojectteam2.data.MemoBean;
import com.example.user.semiprojectteam2.data.SaveBean;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1Activity extends Fragment {

    public Fragment1Activity() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment1, container, false);

        MemoBean memoBean = (MemoBean)getActivity().getIntent().getSerializableExtra(MemoBean.class.getName());

        //TODO 여기에 View를 찾고 이벤트를 등록하고 등등의 처리를 할 수 있다.
        EditText edtText = view.findViewById(R.id.edtText);

        if(memoBean != null) {
            edtText.setText(memoBean.getMemo());
        }

        return view;
    }

}
