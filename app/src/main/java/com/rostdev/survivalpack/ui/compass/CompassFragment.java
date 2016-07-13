package com.rostdev.survivalpack.ui.compass;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rostdev.survivalpack.R;
import com.rostdev.survivalpack.databinding.FragCompassBinding;
import com.rostdev.survivalpack.ui.base.BaseFragment;

/**
 * Created by Rosty on 7/5/2016.
 */
public class CompassFragment extends BaseFragment<CompassPresenter> implements CompassContract.View{

    private FragCompassBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.frag_compass, container, false);
        getComponent().inject(this);

        return binding.getRoot();
    }

    @Override
    public void updateCompassData(float position) {
        binding.compass.updateData(position);
    }
}
