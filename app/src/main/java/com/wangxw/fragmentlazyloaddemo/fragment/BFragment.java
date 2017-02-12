package com.wangxw.fragmentlazyloaddemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangxw.fragmentlazyloaddemo.R;

/**
 * Created by wangxw on 2017/2/10 0010 14:58.
 * E-mail:wangxw725@163.com
 * function:
 */
public class BFragment extends BaseFragment {


    @Override
    protected void loadData() {
        Log.i("wxw","BFragment可见,可以加载数据了");
    }

    @Override
    protected String getFragmentTextviewContent() {
        return "Page_1";
    }
}
