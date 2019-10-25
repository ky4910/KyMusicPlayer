package com.example.kimberjin.kymusicplayer.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.example.kimberjin.kymusicplayer.application.GlobalVal;
import com.example.kimberjin.kymusicplayer.service.PlayerService;

/**
 * Created by ky4910 on 2019/10/11
 */
public abstract class BaseFragment extends Fragment {

    Activity mActivity = getActivity();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    protected abstract void initView(View view);

    protected PlayerService getPlayerService(){
        if (GlobalVal.getPlayService() == null){
            throw new NullPointerException("BaseFragment PlayerService is null");
        }
        return GlobalVal.getPlayService();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }
}
