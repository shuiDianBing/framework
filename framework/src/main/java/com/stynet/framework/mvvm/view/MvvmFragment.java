package com.stynet.framework.mvvm.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.stynet.framework.FrameFragment;
import com.stynet.framework.mvvm.model.MvvmModel;

/**
 * Created by shuiDianBing on 11:44.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << view层:只负责view与用户交互操作数据显示等
 */
public abstract class MvvmFragment<Binding extends ViewDataBinding> extends FrameFragment {
    private Binding binding;
    private MvvmModel model;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //super.onCreateView(inflater,container,savedInstanceState);
        View layout = viewBinding(inflater,container,savedInstanceState);
        binding = DataBindingUtil.bind(layout);//绑定视图
        model = initModel();//初始mvvmModel
        return layout;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(null != binding)
            binding.unbind();
    }

    /**
     * 视图绑定{@link MvvmFragment#setBinding(View)}
     * @return view
     */
    protected abstract View viewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    /**
     * 设置视图绑定{@link MvvmFragment#viewBinding(LayoutInflater, ViewGroup, Bundle)}
     * @param layout
     */
    public void setBinding(View layout) {
        this.binding = DataBindingUtil.bind(getLayout());
    }

    public Binding getBinding() {
        return binding;
    }

    /**
     * 初始化mvvmModel{@link MvvmFragment#setModel(MvvmModel)}
     * @return
     */
    protected abstract MvvmModel initModel();

    /**
     * 设置mvvmModel{@link MvvmFragment#initModel()}
     * @param model
     */
    public void setModel(MvvmModel model) {
        this.model = model;
    }
}
