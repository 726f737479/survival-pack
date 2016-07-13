package com.rostdev.survivalpack.ui.info;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rostdev.survivalpack.R;
import com.rostdev.survivalpack.databinding.FragInfoBinding;
import com.rostdev.survivalpack.model.SensorInfo;
import com.rostdev.survivalpack.ui.base.BaseFragment;

import javax.inject.Inject;

/**
 * Created by Rosty on 7/5/2016.
 */
public class InfoFragment extends BaseFragment<InfoPresenter> implements InfoContract.View {

    @Inject
    public InfoAdapter adapter;
    private FragInfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.frag_info, container, false);
        getComponent().inject(this);

        binding.recycler.setEmptyView(binding.empty);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setAdapter(adapter);

        return binding.getRoot();
    }

    @Override
    public void updateSensorInfoData(SensorInfo[] sensorInfo) {
        adapter.updateData(sensorInfo);
    }
}
