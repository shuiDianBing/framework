package com.stynet.frameset;

import android.os.Bundle;

import com.stynet.framework.FrameActivity;

public class MainActivity extends FrameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayLoading("玩命加载中。。。");
    }
}
