package com.stynet.framework.mvvm.view;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.stynet.framework.FrameActivity;
import com.stynet.framework.FrameModel;
import com.stynet.framework.mvvm.model.MvvmModel;

/**
 * Created by shuiDianBing on 11:42.
 * Refer to the website << 浅谈安卓中的MVP模式 https://blog.csdn.net/u012124438/article/details/51627220
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << view层:只负责view与用户交互操作数据显示等
 */
public abstract class MvvmActivity<Binding extends ViewDataBinding>extends FrameActivity {
    private Binding binding;
    private FrameModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.bind(viewBinding());//绑定视图
        model = initModel();//初始mvvmModel
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != binding)
            binding.unbind();
    }

    /**
     * 视图绑定{@link MvvmActivity#setBinding(int)}
     * @return view
     */
    protected abstract View viewBinding();

    /**
     * 设置视图绑定{@link MvvmActivity#viewBinding()}
     * @param layoutId
     */
    protected void setBinding(@LayoutRes int layoutId) {
        binding = DataBindingUtil.setContentView(this,layoutId);
    }

    /**
     *
     * @return
     */
    public Binding getBinding() {
        return binding;
    }

    /**
     * 初始化mvvmModel{@link MvvmActivity#setModel(FrameModel)}
     * @return
     */
    protected abstract FrameModel initModel();

    /**
     * 设置mvvmModel{@link MvvmActivity#initModel()}
     * @param model
     */
    protected void setModel(FrameModel model){
        this.model = model;
    }
}
