package com.stynet.framework;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

/**
 * Created by shuiDianBing on 11:17.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 视图层fragment view基类
 * 对应于Activity和xml，只负责view与用户交互操作数据显示等
 */
public class FrameFragment extends AppCompatDialogFragment {
    private View layout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(null != layout){
            ViewGroup parent = (ViewGroup) layout.getParent();
            if(null != parent)
                parent.removeView(layout);
        }
        return layout;
    }

    public void setLayout(View layout) {
        this.layout = layout;
    }

    public View getLayout() {
        return layout;
    }
}
