package com.stynet.frameset.view;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.stynet.frameset.R;
import com.stynet.frameset.route.Path;
import com.stynet.framework.FrameActivity;
import com.stynet.framework.mvvm.view.MvvmActivity;

/**
 * Created by shuiDianBing on 12:16.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function <<
 */
@Route(path = Path.ACTIVITY_MVVM)
public class ActivityMvvm extends FrameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        displayLoading("玩命加载中。。。");
    }
}
