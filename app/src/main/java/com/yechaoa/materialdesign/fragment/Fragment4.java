package com.yechaoa.materialdesign.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yechaoa.materialdesign.R;
import com.yechaoa.materialdesign.adapter.StringAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Fragment4 extends Fragment {

    @BindView(R.id.recycleView)
    RecyclerView mRecycleView;
    Unbinder unbinder;
    private List<String> mList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment4, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        mList=new ArrayList<>();
        for (int i=0;i<20;i++){
            mList.add("MaterialDesign"+i);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setAdapter(new StringAdapter(getActivity(),mList));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
