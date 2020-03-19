package com.stynet.framework.mvpvm.view;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.stynet.framework.FrameActivity;
import com.stynet.framework.mvpvm.presenter.MvpvmPresenter;
import com.stynet.framework.mvvm.view.MvvmActivity;

/**
 * Created by shuiDianBing on 11:42.
 * Refer to the website << 浅谈安卓中的MVP模式 https://blog.csdn.net/u012124438/article/details/51627220
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << view层:只负责view与用户交互操作数据显示等
 */
public abstract class MvpvmActivity<Binding extends ViewDataBinding,Presenter extends MvpvmPresenter> extends FrameActivity {
    private Binding binding;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,layoutId());//绑定视图
        presenter = initPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != binding)
            binding.unbind();
    }

    /**
     * 获取视图layoutId{@link MvpvmActivity#viewBinding()}{@link MvpvmActivity#setBinding(int)}
     * @return
     */
    protected abstract @LayoutRes int layoutId();

    /**
     * 视图绑定{@link MvpvmActivity#layoutId()}{@link MvpvmActivity#setBinding(int)}
     * @return view
     */
    protected abstract View viewBinding();

    /**
     * 设置视图绑定{@link MvpvmActivity#layoutId()}{@link MvpvmActivity#viewBinding()}
     * @param layoutId
     */
    protected void setBinding(@LayoutRes int layoutId) {
        binding = DataBindingUtil.setContentView(this,layoutId);
    }

    protected Binding getBinding(){
        return binding;
    }

    /**
     * 初始化 presenter{@link MvpvmActivity#setPresenter(MvpvmPresenter)}
     * @return
     */
    protected abstract Presenter initPresenter();

    /**
     * 初始化 presenter{@link MvpvmActivity#initPresenter()}
     * @param presenter
     */
    protected void setPresenter(Presenter presenter){
        this.presenter = presenter;
    }

    protected Presenter getPresenter(){
        return presenter;
    }
}
