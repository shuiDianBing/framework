package com.stynet.framework.mvpvm.presenter;

import com.stynet.framework.FramePresenter;
import com.stynet.framework.mvpvm.connector.Viewport;
import com.stynet.framework.mvpvm.model.MvpvmModel;

/**
 * Created by shuiDianBing on 11:40.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << presenter层:调用具体的逻辑实现（比如 请求网络等）
 */
public class MvpvmPresenter<View extends Viewport,Model extends MvpvmModel> extends FramePresenter {
    private View viewport;
    private Model model;

    public MvpvmPresenter(View viewport){
        this.viewport = viewport;
    }

    public View getViewport() {
        return viewport;
    }

    public void setViewport(View viewport) {
        this.viewport = viewport;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
