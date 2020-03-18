package com.stynet.framework.mvvm.view;
import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.stynet.framework.FrameActivity;

/**
 * Created by shuiDianBing on 11:42.
 * Refer to the website << 浅谈安卓中的MVP模式 https://blog.csdn.net/u012124438/article/details/51627220
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << view层:只负责view与用户交互操作数据显示等
 */
public class MvvmActivity extends FrameActivity {
    private ViewDataBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     *
     * @param layoutId
     */
    public void setBinding(@LayoutRes int layoutId) {
        binding = DataBindingUtil.setContentView(this,layoutId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != binding)
            binding.unbind();
    }
}
