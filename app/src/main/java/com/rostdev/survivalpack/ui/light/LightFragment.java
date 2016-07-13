package com.rostdev.survivalpack.ui.light;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rostdev.survivalpack.R;
import com.rostdev.survivalpack.databinding.FragLightBinding;
import com.rostdev.survivalpack.ui.base.BaseFragment;

/**
 * Created by Rosty on 7/5/2016.
 */
public class LightFragment extends BaseFragment<LightPresenter> implements LightContract.View {

    private FragLightBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.frag_light, container, false);
        getComponent().inject(this);

        binding.light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onLightSwitchEvent();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void toggleLightIcon(boolean light) {

        binding.light.toggleState(light);
    }
}
