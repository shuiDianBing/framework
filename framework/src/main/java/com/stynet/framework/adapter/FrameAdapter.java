package com.stynet.framework.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by shuiDianBing on 18:10.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 封装绑定适配
 */
public abstract class FrameAdapter extends Adaptor{
    public FrameAdapter(List list) {
        super(list);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder, i);
        viewHolder.itemView.setId(i);
        viewHolder.itemView.setTag(getList().get(i));
    }

    public class BindingViewHolder<Binding extends ViewDataBinding> extends RecyclerView.ViewHolder{
        private Binding binding;
        public BindingViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
        }
        public ViewDataBinding getBinding(){
            return binding;
        }
    }
}
