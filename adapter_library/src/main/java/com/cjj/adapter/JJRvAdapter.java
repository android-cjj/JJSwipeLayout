package com.cjj.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by cjj on 2015/11/30.
 *  J （J代表cjj,说笑，别打我，J是model的类型）
 *  T （T代表Viewholder）
 */
public abstract class JJRvAdapter<J,T> extends RecyclerView.Adapter<JJRvAdapter.RVViewHolder<T>> implements DataHandler<J>{

    private int mLayout;

    private Class<T> mHolderClass;

    private List<J> mList;

    private JJRvAdapter(){}

    public JJRvAdapter(List<J> list, int layout, Class<T> holderClass) {
        this.mLayout = layout;
        this.mList = list;
        this.mHolderClass = holderClass;
    }

    public JJRvAdapter(J[] mArray, int layout, Class<T> holderClass) {
        this.mLayout = layout;
        this.mList = CastUtil.arrayToList(mArray);
        this.mHolderClass = holderClass;
    }


    @Override
    public int getItemCount() {
        return mList==null?0:mList.size();
    }

    @Override
    public void updateData(List<J> list) {
        this.mList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public void updateData(J[] array) {
        this.mList = CastUtil.arrayToList(array);
        this.notifyDataSetChanged();
    }

    @Override
    public void removeData(int pos) {
        if(mList == null)return;
        mList.remove(pos);
        this.notifyDataSetChanged();
    }

    @Override
    public void removeData(J item) {
        if(mList == null)return;
        this.mList.remove(item);
        this.notifyDataSetChanged();
    }

    @Override
    public void addData(List<J> list) {
        if(mList == null)return;
        this.mList.addAll(mList);
        this.notifyItemRangeInserted(this.mList.size() - 1, mList.size());
    }

    @Override
    public void addData(J[] arrays) {
        if(mList == null)return;
        int listSize = this.mList.size();
        int length = arrays.length;
        for(int i=0;i<length;i++){
            this.mList.add(arrays[i]);
        }
        this.notifyItemRangeInserted(listSize, arrays.length);
    }

    @Override
    public void addData(J item) {
        if(mList == null)return;
        this.mList.add(item);
        int insertPosition = mList.size() - 1;
        this.notifyItemInserted(insertPosition);
    }

    @Override
    public RVViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(mLayout, null);
        return new RVViewHolder<>(itemView, mHolderClass);
    }

    @Override
    public void onBindViewHolder(RVViewHolder<T> holder, int position) {
        onBindData(position,holder.t,mList.get(position));
    }

    public static class RVViewHolder<T> extends RecyclerView.ViewHolder{
        public T t;
        public RVViewHolder(View itemView,Class<?> cls) {
            super(itemView);
            t = (T) InflateViewHolder.inflateViewHolder(itemView,cls);
        }
    }

    public abstract void onBindData(int pos, T holder, J item);

}
