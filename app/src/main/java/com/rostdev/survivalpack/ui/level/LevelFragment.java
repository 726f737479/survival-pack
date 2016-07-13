package com.rostdev.survivalpack.ui.level;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rostdev.survivalpack.R;
import com.rostdev.survivalpack.databinding.FragLevelBinding;
import com.rostdev.survivalpack.ui.base.BaseFragment;

/**
 * Created by Rosty on 7/5/2016.
 */
public class LevelFragment extends BaseFragment<LevelPresenter> implements LevelContract.View {

    private FragLevelBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.frag_level, container, false);
        getComponent().inject(this);

        return binding.getRoot();
    }

    @Override
    public void updateLevelData(float gravityX, float gravityY) {
        binding.level.updateData(gravityX, gravityY);
    }
}
