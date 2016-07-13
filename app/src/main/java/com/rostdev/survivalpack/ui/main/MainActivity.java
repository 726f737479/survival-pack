package com.rostdev.survivalpack.ui.main;

import android.databinding.DataBindingUtil;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.MenuItem;

import com.rostdev.survivalpack.R;
import com.rostdev.survivalpack.databinding.ActMainBinding;
import com.rostdev.survivalpack.ui.base.BaseActivity;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    private ActMainBinding binding;
    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.act_main);
        bottomBar = BottomBar.attach(binding.content, savedInstanceState);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu);

        getComponent().inject(this);

        setupBottomBar();
        setupNavigation();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        binding.drawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

    public void setupNavigation(){

        binding.navigation.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        binding.drawer.closeDrawers();
                        presenter.onNavSelected(menuItem.getItemId());

                        return true;
                    }
                });
    }

    public void setupBottomBar() {

        bottomBar.noNavBarGoodness();
        bottomBar.setItems(R.menu.main_menu);
        bottomBar.setElevation(0);

        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                presenter.onTabSelected(menuItemId);
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
            }
        });

        bottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.tab_compass_dark));
        bottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.tab_level_dark));
        bottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.tab_light_dark));
        bottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.tab_info_dark));
    }

    @Override
    public void updateColor(int colorRes, int colorDarkRes) {

        binding.toolbar.setBackgroundColor(ContextCompat.getColor(MainActivity.this, colorRes));
        getWindow().setStatusBarColor(ContextCompat.getColor(MainActivity.this, colorDarkRes));
    }

    @Override
    public void showFragment(Fragment fragment) {

        try {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, fragment)
                    .disallowAddToBackStack()
                    .commit();

        } catch (IllegalStateException ignored) {}
    }
}
