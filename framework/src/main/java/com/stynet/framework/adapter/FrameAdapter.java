package com.stynet.framework.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by shuiDianBing on 18:10.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 封装绑定适配
 */
public abstract class FrameAdapter extends RecyclerView.Adapter<FrameAdapter.BindingViewHolder>{
    private List list;

    public FrameAdapter(List list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void update(List list, boolean isRefresh){
        if(null != list) {
            if (isRefresh || null == this.list)
                this.list = list;
            else
                this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    protected List getList() {
        return list;
    }

    public class BindingViewHolder<Binding extends ViewDataBinding> extends RecyclerView.ViewHolder{
        private Binding binding;
        public BindingViewHolder(Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public Binding getBinding(){
            return binding;
        }
    }
}
