package com.rostdev.survivalpack.ui.info;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rostdev.survivalpack.R;
import com.rostdev.survivalpack.databinding.ItemSensorInfoBinding;
import com.rostdev.survivalpack.model.SensorInfo;
import com.rostdev.survivalpack.ui.MvpViewContext;
import com.rostdev.survivalpack.ui.base.BaseAdapter;

import javax.inject.Inject;

/**
 * Created by Rosty on 7/7/2016.
 */
public class InfoAdapter extends BaseAdapter<SensorInfo, InfoAdapter.Holder>{

    @Inject
    public InfoAdapter(@MvpViewContext Context context) {
        super(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sensor_info, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        SensorInfo item = data[position];

        holder.binder.title.setText(item.getTitle());
        holder.binder.value.setText(item.getInfo());
        holder.binder.image.setImageResource(item.getImage());

        super.onBindViewHolder(holder, position);
    }

    class Holder extends RecyclerView.ViewHolder {

        private ItemSensorInfoBinding binder;

        public Holder(View container) {
            super(container);

            binder = DataBindingUtil.bind(container);
        }
    }
}
