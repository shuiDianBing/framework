package com.stynet.framework.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.stynet.framework.R;

import java.util.List;

/**
 * Created by shuiDianBing on 17:24.
 * Refer to the website << nullptr
 * QQ << 1226085282 &  Email << 1226085282@qq.com
 * function << 加载更多
 */
public abstract class Adaptor extends RecyclerView.Adapter {
    private List list;
    private LoadMoreListener loadMoreListener;
    private boolean over;

    public interface LoadMoreListener{
        void onLoadMore();
    }

    public Adaptor(List list) {
        this.list = list;
    }


    @Override
    public int getItemCount() {
        return null == list ?0: list.size()+1;
    }
    @Override
    public int getItemViewType(int position) {
        return list.size()> position ? super.getItemViewType(position):1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new FooterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.load_more,viewGroup,false)){};
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setId(i);
        if(list.size()==i) {
            Context context = viewHolder.itemView.getContext();
            if (over) {
                ((FooterViewHolder) viewHolder).setTextHint(context.getString(R.string.bottomLine));
                ((FooterViewHolder) viewHolder).setVisibility(View.GONE);
            } else if (getItemCount() - 1 == i && null != loadMoreListener) {
                ((FooterViewHolder) viewHolder).setTextHint(context.getString(R.string.playLifeLoading));
                ((FooterViewHolder) viewHolder).setVisibility(View.VISIBLE);
                loadMoreListener.onLoadMore();
            }
        }
    }

    /**
     * 刷新
     * @param list 新数据
     * @param isRefresh 是否刷新
     */
    public void update(List list, boolean isRefresh){
        if(null != list) {
            if (isRefresh || null == this.list)
                this.list = list;
            else
                this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public LoadMoreListener getLoadMoreListener() {
        return loadMoreListener;
    }

    public void setLoadMoreListener(LoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    protected class FooterViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar progressBar;
        private TextView text;
        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setBackgroundColor(0x00000000);
            progressBar = itemView.findViewById(R.id.progressBar);
            text = itemView.findViewById(R.id.text);
        }

        public void setVisibility(int visibility){
            progressBar.setVisibility(visibility);
        }

        public void setTextHint(String hint) {
            text.setText(hint);
        }
    }
}
