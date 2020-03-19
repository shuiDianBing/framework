package com.stynet.frameset.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.stynet.frameset.R;
import com.stynet.frameset.route.Path;
import com.stynet.framework.FrameActivity;

/**
 * Created by shuiDianBing on 12:11.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << mvp例子
 */
@Route(path = Path.ACTIVITY_MVP)
public class ActivityMvp extends FrameActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        displayLoading("玩命加载中。。。");
    }
}
