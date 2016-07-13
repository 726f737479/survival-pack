package com.rostdev.survivalpack.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rostdev.survivalpack.R;
import com.rostdev.survivalpack.ui.MvpViewContext;

/**
 * Created by Rosty on 7/8/2016.
 */
public  abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH>{

    protected Animation animation;

    protected T[] data;
    private int lastPosition = -1;

    public BaseAdapter(@MvpViewContext Context context) {

        animation = AnimationUtils.loadAnimation(context, R.anim.list_item_anim);
    }

    public void updateData(T[] data) {

        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {

        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.length;
    }

    private void setAnimation(View viewToAnimate, int position){

        if (position > lastPosition) {

            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
