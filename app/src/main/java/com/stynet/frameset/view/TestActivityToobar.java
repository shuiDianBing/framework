package com.stynet.frameset.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.stynet.frameset.R;
import com.stynet.framework.util.ToobarUtils;

/**
 * Created by shuiDianBing on 16:46.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 测试状态栏已经文字颜色
 */
public class TestActivityToobar extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        //    statusBar(Color.BLUE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toobar);
        //displayLoading(R.string.playLifeLoading);
        ToobarUtils.statusBarColor(this, ContextCompat.getColor(this,android.R.color.transparent));
        ToobarUtils.MIUISetStatusBarLightMode(this,true);
    }
}
