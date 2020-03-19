package com.stynet.framework.mvp.view;

import android.os.Bundle;

import com.stynet.framework.FrameActivity;
import com.stynet.framework.mvp.presenter.MvpPresenter;

/**
 * Created by shuiDianBing on 11:42.
 * Refer to the website << 浅谈安卓中的MVP模式 https://blog.csdn.net/u012124438/article/details/51627220
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << view层:只负责view与用户交互操作数据显示等
 */
public abstract class MvpActivity<Presenter extends MvpPresenter> extends FrameActivity {
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = initPresenter();
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
