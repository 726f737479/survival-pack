package com.rostdev.survivalpack.ui.support;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.view.View;

import com.rostdev.survivalpack.R;
import com.rostdev.survivalpack.databinding.ActSupportBinding;
import com.rostdev.survivalpack.ui.base.BaseActivity;

/**
 * Created by Rosty on 7/13/2016.
 */
public class SupportActivity extends BaseActivity<SupportPresenter> implements SupportContract.View, View.OnClickListener{

    private ActSupportBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.act_support);
        binding.btnSend.setOnClickListener(this);

        getComponent().inject(this);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorAccentDark));

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        presenter.onSendPressed(
                binding.editTitle.getText().toString(),
                binding.editTitle.getText().toString()
        );
    }

    @Override
    public void showFailToast(int messageResId) {

        Snackbar.make(binding.toolbar, getString(messageResId), Snackbar.LENGTH_LONG);
    }

    @Override
    public void clearForm() {

        binding.editTitle.getText().clear();
        binding.editTitle.getText().clear();
    }
}
