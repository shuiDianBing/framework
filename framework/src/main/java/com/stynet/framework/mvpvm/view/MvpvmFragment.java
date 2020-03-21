package com.stynet.framework.mvpvm.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.stynet.framework.FrameFragment;
import com.stynet.framework.mvpvm.presenter.MvpvmPresenter;

/**
 * Created by shuiDianBing on 11:44.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << view层:只负责view与用户交互操作数据显示等
 */
public abstract class MvpvmFragment<Binding extends ViewDataBinding,Presenter extends MvpvmPresenter> extends FrameFragment {
    private Binding binding;
    private Presenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //super.onCreateView(inflater,container,savedInstanceState);
        View layout = viewBinding();
        binding = DataBindingUtil.bind(layout);//绑定视图
        presenter = initPresenter();
        return layout;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(null != binding)
            binding.unbind();
    }

    /**
     * 视图绑定{@link MvpvmFragment#setBinding(View)}
     * @return view
     */
    protected abstract View viewBinding();

    /**
     * 设置视图绑定{@link MvpvmFragment#viewBinding()}
     * @param layout
     */

    public void setBinding(View layout) {
        this.binding = DataBindingUtil.bind(getLayout());
    }

    public Binding getBinding() {
        return binding;
    }

    /**
     * 初始化 presenter{@link MvpvmFragment#setPresenter(MvpvmPresenter)}
     * @return
     */
    protected abstract Presenter initPresenter();

    /**
     * 初始化 presenter{@link MvpvmFragment#initPresenter()}
     * @param presenter
     */
    protected void setPresenter(Presenter presenter){
        this.presenter = presenter;
    }

    protected Presenter getPresenter(){
        return presenter;
    }

}
