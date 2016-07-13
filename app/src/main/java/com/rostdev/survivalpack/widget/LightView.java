package com.rostdev.survivalpack.widget;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;

import com.rostdev.survivalpack.R;
import com.rostdev.survivalpack.databinding.ViewLightBinding;

/**
 * Created by Rosty on 7/11/2016.
 */
public class LightView extends FrameLayout {

    private ViewLightBinding binding;
    private boolean active;

    public LightView(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        binding = DataBindingUtil.inflate(inflater, R.layout.view_light, this, true);
    }

    public void toggleState(boolean active){

        this.active = active;

        ActivationAnimation anim = new ActivationAnimation();
        anim.setDuration(300);
        startAnimation(anim);
    }

    class ActivationAnimation extends Animation {

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation transformation) {

            float alpha = active
                    ? 1f * interpolatedTime
                    : 1f - 1f * interpolatedTime;

            binding.active.setAlpha(alpha);
        }
    }
}
