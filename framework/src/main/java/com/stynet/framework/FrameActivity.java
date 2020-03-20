package com.stynet.framework;

import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 * Created by shuiDianBing on 11:16.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << view层activity view基类
 * 对应于Activity和xml，只负责view与用户交互操作数据显示等
 */
public class FrameActivity extends AppCompatActivity {
    private AlertDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     *
     * 重写 getResource 方法，防止系统字体影响，
     * 高版本要放在activity中{@link FrameActivity#getResources()}，如果不设置的话重写此方法
     * Android 应用全局字体调节或禁止随系统字体大小更改<< https://www.jianshu.com/p/dc78921a136b
     * @return
     */
    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration configuration = resources.getConfiguration();
        if(null != configuration && 1.0f != configuration.fontScale){//设置禁止系统更改本app字体大小
            configuration.fontScale = 1.0f;
            resources.updateConfiguration(configuration,resources.getDisplayMetrics());
        }
        return resources;//super.getResources();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoading();
    }

    /**
     * 沉浸状态栏
     * @param color 状态栏颜色
     */
    //@TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void statusBar(int color){// 5.0以上系统状态栏透明
        //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if(Color.TRANSPARENT > color && Color.WHITE < color)//判断颜色是否越界
                color = Color.TRANSPARENT;//超出颜色范围设置为透明
            window.setStatusBarColor(color);
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        //}
    }

    /**
     *
     * @param stringId 字符串资源id
     */
    public void displayLoading(@StringRes int stringId){
        displayLoading(getString(stringId));
    }

    /**
     * 进度条
     * @param hint
     */
    public void displayLoading(CharSequence hint) {
        if(null == loading) {
            ConstraintLayout layout = (ConstraintLayout) LayoutInflater.from(this).inflate(R.layout.loading,null);
            if(null != hint) {
                TextView textView = layout.findViewById(R.id.text);
                textView.setVisibility(View.VISIBLE);
                textView.setText(hint);
            }
            loading = new AlertDialog.Builder(this,R.style.loadingGrayLevel).setView(layout).create();
            loading.setCanceledOnTouchOutside(false);
            loading.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    finish();
                }
            });
        }
        loading.show();
    }

    /**
     * 隐藏进度条
     */
    protected void hideLoading(){
        if(null != loading && loading.isShowing())
            loading.dismiss();//loading.cancel();
    }
}
