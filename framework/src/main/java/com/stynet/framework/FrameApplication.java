package com.stynet.framework;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;

/**
 * Created by shuiDianBing on 15:17.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 统一管理
 */
public class FrameApplication extends Application {
    /**
     *
     * 重写 getResource 方法，防止系统字体影响，低版本的可行(具体Android版本有兴趣者自己式)，
     * 高版本要放在activity中{@link FrameActivity#getResources()}，如果不设置的话重写此方法
     * Android 应用全局字体调节或禁止随系统字体大小更改<< https://www.jianshu.com/p/dc78921a136b
     * @return
     */
    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration configuration = resources.getConfiguration();//不要直接new因为这样系统的属性遗失
        if(null != configuration && 1.0f != configuration.fontScale){//设置禁止系统更改本app字体大小
            configuration.fontScale = 1.0f;
            resources.updateConfiguration(configuration,resources.getDisplayMetrics());
        }
        return resources;//super.getResources();
    }
}
