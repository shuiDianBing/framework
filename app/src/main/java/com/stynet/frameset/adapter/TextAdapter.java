package com.stynet.frameset.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stynet.frameset.R;
import com.stynet.frameset.databinding.ItemTextBiding;
import com.stynet.framework.adapter.FrameAdapter;

import java.util.List;

/**
 * Created by shuiDianBing on 16:55.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function <<
 */
public class TextAdapter extends FrameAdapter {
    private View.OnClickListener clickListener;

    public TextAdapter(List list,View.OnClickListener clickListener) {
        super(list);
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return getList().size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TextViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        ((ItemTextBiding)((BindingViewHolder)viewHolder).getBinding()).setString(getList().get(i).toString());
    }

    public class TextViewHolder extends BindingViewHolder{
        public TextViewHolder(View view) {
            super(view);
            view.setOnClickListener(clickListener);
        }
    }
}
