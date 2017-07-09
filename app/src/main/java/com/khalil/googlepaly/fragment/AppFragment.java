package com.khalil.googlepaly.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.khalil.googlepaly.utils.UIUtils;

/**
 * Created by Khalil on 2017/7/9.
 */

class AppFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(UIUtils.getContext());
        textView.setGravity(Gravity.CENTER);
        textView.setText(this.getClass().getSimpleName());
        return textView;
    }
}
