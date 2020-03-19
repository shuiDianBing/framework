package com.stynet.framework.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.stynet.framework.FrameFragment;
import com.stynet.framework.mvp.presenter.MvpPresenter;

/**
 * Created by shuiDianBing on 11:44.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << view层:只负责view与用户交互操作数据显示等
 */
public abstract class MvpFragment<Presenter extends MvpPresenter> extends FrameFragment {
    private Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        presenter = initPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 初始化 presenter{@link MvpFragment#setPresenter(MvpPresenter)}
     * @return
     */
    protected abstract Presenter initPresenter();

    /**
     * 初始化 presenter{@link MvpFragment#initPresenter()}
     * @param presenter
     */
    protected void setPresenter(Presenter presenter){
        this.presenter = presenter;
    }

    protected Presenter getPresenter(){
        return presenter;
    }
}
