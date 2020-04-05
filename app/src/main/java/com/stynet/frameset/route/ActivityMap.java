package com.stynet.frameset.route;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.stynet.frameset.R;
import com.stynet.frameset.view.TestActivityToobar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shuiDianBing on 17:13.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function <<
 */
public class ActivityMap {
    private Map<String,String> map;

    public final List<String> getList(Activity activity){
        String[] function = activity.getResources().getStringArray(R.array.function);
        String[] functionClass = activity.getResources().getStringArray(R.array.functionClass);

        map = new HashMap();
        for(int index = 0;index < function.length;index++)
            map.put(function[index],functionClass[index]);

        return Arrays.asList(function);
    }

    public void startActivity(Activity activity,String key){
        try {
            Class cls = Class.forName(map.get(key));
            if(Activity.class.isAssignableFrom(cls))
                activity.startActivity(new Intent(activity, cls));
            else
                Toast.makeText(activity,"is no Activity",Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
