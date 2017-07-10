package com.khalil.googlepaly.fragment;

import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.khalil.googlepaly.R;
import com.khalil.googlepaly.base.BaseFragment;
import com.khalil.googlepaly.utils.UIUtils;
import com.khalil.googlepaly.view.LoadingPager;

/**
 * Created by Khalil on 2017/7/9.
 */

class CategoryFragment extends BaseFragment {

    @Override
    public LoadingPager.LoadingResult initData() {
        SystemClock.sleep(1000);
        return LoadingPager.LoadingResult.SUCCESS;
    }

    @Override
    public View initSuccessView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.pager_test, null);
        String simpleName = this.getClass().getSimpleName();
        ((TextView) view.findViewById(R.id.test_text)).setText(simpleName);
        return view;
    }
}
