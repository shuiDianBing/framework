package com.stynet.frameset;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.stynet.framework.FrameActivity;
import com.stynet.framework.mvp.view.MvpActivity;

public class MainActivity extends FrameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        //    statusBar(Color.BLUE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayLoading(R.string.playLifeLoading);
    }
}
