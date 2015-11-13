package com.hugo.goopleplay.base;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by hq on 2015/10/19.
 */
public abstract class BaseFragment extends Fragment {
    protected static final String ARG_PARAM1 = "param1";
    protected static final String ARG_PARAM2 = "param2";

    protected static String mParam1;
    protected static String mParam2;

    private OnCallActivity mListener;

    protected OnCallActivity getOnCallActivity() {
        return mListener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    protected View bindView(@LayoutRes int layoutRes) {
        View view = LayoutInflater.from(getActivity()).inflate(layoutRes, null);
        ButterKnife.bind(this, view);
        return  view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnCallActivity) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
        initEvent();
    }

    protected abstract void initEvent();

    /**
     * 绑定数据，回显数据，设置监听等
     *
     * @param savedInstanceState
     */
    protected abstract void initData(Bundle savedInstanceState);

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    protected void runOnUiThread(Runnable run) {

        getActivity().runOnUiThread(run);
    }

    public interface OnCallActivity {

    }



}
