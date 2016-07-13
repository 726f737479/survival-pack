package com.rostdev.survivalpack.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.rostdev.survivalpack.R;

/**
 * Created by Rosty on 7/5/2016.
 */
public class CompassView extends View implements View.OnClickListener{

    public static int CIRCLE_START = -90;
    public static int CIRCLE_FULL = 360;
    public static double CIRCLE_HALF = CIRCLE_FULL / 2;

    private Paint paintOuter;
    private Paint paintInner;

    private float position;
    private boolean updated;

    private boolean direction;
    private float directionDegree;

    private RectF rect;

    public CompassView(Context context, AttributeSet attrs) {

        super(context, attrs);

        setClickable(true);
        setOnClickListener(this);
    }

    private void setupPaintsIfNeeded(float radius, float pointX, float pointY) {

        if (paintOuter == null) {

            paintOuter = new Paint();
            paintOuter.setAntiAlias(true);
            paintOuter.setStrokeWidth(radius * 0.04f);
            paintOuter.setStyle(Paint.Style.STROKE);
        }

        if (paintInner == null) {

            paintInner = new Paint();
            paintInner.setAntiAlias(true);
            paintInner.setTextSize(radius * 0.5f);
            paintInner.setStrokeWidth(radius * 0.04f);
            paintInner.setStyle(Paint.Style.FILL);
            paintInner.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        }

        if (rect == null){

            float size = Math.min(pointX, pointY);
            float position = Math.max(pointX, pointY);

            rect = new RectF(
                    size - radius, position - radius,
                    size + radius, position + radius);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (!updated) return;

        float pointX = getMeasuredWidth() / 2;
        float pointY = getMeasuredHeight() / 2;
        float radius = Math.max(pointX, pointY) * 0.5f;

        setupPaintsIfNeeded(radius, pointX, pointY);
        String label = Math.round(position > 0 ? position : CIRCLE_FULL + position) + "\u00b0";

        float textPointX = pointX - paintInner.measureText(label) * 0.45f;
        float textPointY = pointY + radius * 0.2f;

        canvas.drawText(label, textPointX, textPointY, paintInner);

        paintOuter.setColor(ContextCompat.getColor(getContext(), R.color.white_40));
        canvas.drawArc(rect, CIRCLE_START, CIRCLE_FULL, true, paintOuter);

        paintOuter.setColor(ContextCompat.getColor(getContext(), R.color.white));
        canvas.drawArc(rect, CIRCLE_START, getSweepDirAngle(), false, paintOuter);

        pointX = (float) (((double) pointX) + (((double) radius)
                * Math.sin((((double) (-position)) / CIRCLE_HALF) * Math.PI)));

        pointY = (float) (((double) pointY) - (((double) radius)
                * Math.cos((((double) (-position)) / CIRCLE_HALF) * Math.PI)));

        canvas.drawCircle(pointX, pointY, radius * 0.1f, paintInner);
    }

    public void updateData(float position) {

        this.updated = true;
        this.position = position;

        invalidate();
    }

    @Override
    public void onClick(View view) {

        direction = !direction;
        directionDegree = position;
    }

    private float getSweepDirAngle(){

        float difference = Math.round(directionDegree - position);

        if (Math.abs(difference) > CIRCLE_HALF) {

            if (difference > 0) difference -= CIRCLE_FULL;
            else difference += CIRCLE_FULL;
        }

        if (!direction) return CIRCLE_FULL;
        if (difference == 0) return CIRCLE_FULL;

        return difference > 0
                ? -CIRCLE_FULL + difference
                : CIRCLE_FULL + difference;
    }
}

