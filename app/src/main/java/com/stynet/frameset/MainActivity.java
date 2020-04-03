package com.stynet.frameset;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.stynet.frameset.adapter.TextAdapter;
import com.stynet.frameset.route.ActivityMap;
import com.stynet.framework.FrameActivity;
import java.util.List;

public class MainActivity extends FrameActivity{
    private TextAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        //    statusBar(Color.BLUE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //displayLoading(R.string.playLifeLoading);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));

        ActivityMap activityMap = new ActivityMap();
        List<String> list = activityMap.getList(this);

        recyclerView.setAdapter(adapter = new TextAdapter(list, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityMap.startActivity(MainActivity.this,v.getTag().toString());
            }
        }));
    }
}
