package com.hubuandroid.mywechat.adapers;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hubuandroid.mywechat.R;
import com.hubuandroid.mywechat.beans.ItemBean;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.InnerHolder> {

    private final List<ItemBean> mData;

    public ListViewAdapter(List<ItemBean> data){
        this.mData = data;
    }

    /**
     * 这个方法用于创建条目View
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ListViewAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //传进去的条目是view的界面
        //两个步骤
        //1.拿到view
        //2.创建InnerHolder
        View view = View.inflate(parent.getContext(), R.layout.item_list_view, null);
        return new InnerHolder(view);
    }

    /**
     * 这个方法用于绑定holder，一般用来设置数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull ListViewAdapter.InnerHolder holder, int position) {
        //在这里设置数据
        holder.setData(mData.get(position));

    }

    /**
     * 返回条目个数
     * @return
     */
    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }


    public class InnerHolder extends RecyclerView.ViewHolder {

        private ImageView mIcon;
        private TextView mTitle;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);

            //找到条目的控件
            mIcon = itemView.findViewById(R.id.list_view_icon);
            mTitle = itemView.findViewById(R.id.list_view_title);
        }

        /**
         * 这个方法用于设置数据
         * @param itemBean
         */
        public void setData(ItemBean itemBean) {
            mIcon.setImageResource(itemBean.icon);
            mTitle.setText(itemBean.title);
        }
    }
}
