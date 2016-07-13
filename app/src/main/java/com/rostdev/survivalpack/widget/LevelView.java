package com.rostdev.survivalpack.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import com.rostdev.survivalpack.R;

/**
 * Created by Rosty on 7/6/2016.
 */
public class LevelView extends View {

    public static final int BALANCE_DURATION = 1000;
    public static final int ANIM_DURATION = 500;

    private Paint paintCircle;
    private Paint paintAnim;

    private float topX;
    private float topY;

    private float botX;
    private float botY;

    private boolean updated;

    private long balanceTime;
    private boolean balanced;

    private float balanceRadius;
    private float radius;

    public LevelView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void setupPaintsIfNeeded(float radius) {

        if (paintCircle == null) {

            paintCircle = new Paint();
            paintCircle.setAntiAlias(true);
            paintCircle.setStrokeWidth(radius * 0.1f);
            paintCircle.setStyle(Paint.Style.STROKE);
            paintCircle.setMaskFilter(null);
        }

        if (paintAnim == null){

            paintAnim = new Paint();
            paintAnim.setAntiAlias(true);
            paintAnim.setColor(Color.WHITE);
            paintAnim.setStyle(Paint.Style.FILL);
            paintAnim.setMaskFilter(null);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (!updated) return;

        radius = Math.max(getMeasuredWidth(), getMeasuredHeight()) * 0.1f;

        setupPaintsIfNeeded(radius);

        paintCircle.setColor(ContextCompat.getColor(getContext(), R.color.white));
        if (!balanced) canvas.drawCircle(botX, botY, radius, paintCircle);

        paintCircle.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));

        canvas.drawCircle(topX, topY, !balanced ? radius : radius + balanceRadius / 5, paintCircle);
        canvas.drawCircle(botX, botY, balanceRadius, paintAnim);
    }

    public void updateData(float gravityX, float gravityY) {

        updated = true;

        topX = getMeasuredWidth() / 2f + getMeasuredWidth() / 2f * gravityX / 10f;
        topY = getMeasuredHeight() / 2f + getMeasuredHeight() / 2f * gravityY / 10f;
        botX = getMeasuredWidth() / 2f - getMeasuredWidth() / 2f * gravityX / 10f;
        botY = getMeasuredHeight() / 2f - getMeasuredHeight() / 2f * gravityY / 10f;

        if (Math.abs(gravityX) < 0.02 && Math.abs(gravityY) < 0.02){

            if (balanceTime == 0) balanceTime = System.currentTimeMillis();
            if (balanced) return;

            balanced = System.currentTimeMillis() - balanceTime > BALANCE_DURATION;
            if (balanced) startAnimation();

        }else {

            if (balanced) startAnimation();

            balanceRadius = 0;
            balanceTime = 0;
            balanced = false;
        }

        invalidate();
    }

    public void startAnimation(){

        BalanceAnimation animation = new BalanceAnimation();
        animation.setDuration(500);

        startAnimation(animation);
    }

    class BalanceAnimation extends Animation {

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation transformation) {

            balanceRadius = balanced
                    ? radius * interpolatedTime
                    : radius - radius * interpolatedTime;

            invalidate();
        }
    }
}
