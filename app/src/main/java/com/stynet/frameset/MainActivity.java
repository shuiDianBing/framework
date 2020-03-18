package com.stynet.frameset;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.stynet.framework.FrameActivity;

public class MainActivity extends FrameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            statusBar(Color.BLUE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayLoading("玩命加载中。。。");
    }
}
