package com.stynet.frameset.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.stynet.frameset.R;
import com.stynet.framework.Printf;
import com.stynet.framework.network.NetworkUtils;

/**
 * Created by shuiDianBing on 11:03.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 测试NetworkUtils
 */
public class TestActivityNetwork extends AppCompatActivity {
    private static final String TAG = TestActivityNetwork.class.getName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);
    }

    /**
     * 测试网络链接
     * @param view
     */
    public void connect(View view){
        if(NetworkUtils.isConnected(this))
            ((TextView)view).setText("connected << 以连接");
        else
            ((TextView)view).setText("Unconnected << 未连接");
    }
}
