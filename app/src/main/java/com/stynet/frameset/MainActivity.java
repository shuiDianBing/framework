package com.stynet.frameset;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.stynet.frameset.view.TestActivityNetwork;
import com.stynet.framework.FrameActivity;
import com.stynet.framework.mvp.view.MvpActivity;

public class MainActivity extends FrameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        //    statusBar(Color.BLUE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //displayLoading(R.string.playLifeLoading);
    }

    public void onNetWork(View view){
        startActivity(new Intent(this, TestActivityNetwork.class));
    }
}
