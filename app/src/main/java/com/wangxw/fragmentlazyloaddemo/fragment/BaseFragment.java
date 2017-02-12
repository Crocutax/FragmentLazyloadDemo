package com.wangxw.fragmentlazyloaddemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangxw.fragmentlazyloaddemo.MainActivity;
import com.wangxw.fragmentlazyloaddemo.R;

/**
 * Created by wangxw on 2017/2/10 0010 15:21.
 * E-mail:wangxw725@163.com
 * function:
 */
public abstract class BaseFragment extends Fragment {

    private String mTextviewContent;
    private MainActivity mMainActivity;

    //Fragment的View加载完毕的标记
    private boolean isViewCreated;
    //Fragment对用户可见的标记
    private boolean isUIVisible;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item, container, false);
        initView(view);
        return view ;
    }

    private void initView(View view) {
        TextView textView = (TextView) view.findViewById(R.id.textview);
        mTextviewContent = getFragmentTextviewContent();
        textView.setText(mTextviewContent);

        mMainActivity = (MainActivity) getActivity();
        printLog(mTextviewContent +"加载了");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //isVisibleToUser这个boolean值表示:该Fragment的UI 用户是否可见
        if (isVisibleToUser) {
            isUIVisible = true;
            lazyLoad();
        } else {
            isUIVisible = false;
        }
    }

    private void lazyLoad() {
        //这里进行双重标记判断,是因为setUserVisibleHint会多次回调,并且会在onCreateView执行前回调,必须确保onCreateView加载完毕且页面可见,才加载数据
        if (isViewCreated && isUIVisible) {
            loadData();
            //数据加载完毕,恢复标记,防止重复加载
            isViewCreated = false;
            isUIVisible = false;

            printLog(mTextviewContent+"可见,加载数据");
        }
    }


    protected abstract void loadData();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //页面销毁,恢复标记
        isViewCreated = false;
        isUIVisible = false;

        printLog(mTextviewContent +"销毁了");
    }

    private void printLog(String logStr) {
        TextView logView = new TextView(getContext());
        logView.setText(logStr);
        mMainActivity.addLog(logView);
    }

    /**Fragment中TextView显示的内容*/
    protected abstract String getFragmentTextviewContent();
}
