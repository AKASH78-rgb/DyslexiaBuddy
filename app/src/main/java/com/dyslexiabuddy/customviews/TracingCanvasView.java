package com.dyslexiabuddy.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

public class TracingCanvasView extends View {

    private Paint drawPaint;
    private Paint guidePaint;
    private Path drawPath;
    private String targetText = "A";
    private float touchTolerance = 4f;
    private float mX, mY;

    public TracingCanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        drawPath = new Path();

        drawPaint = new Paint();
        drawPaint.setColor(Color.parseColor("#2EC4B6"));
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(36f);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        guidePaint = new Paint();
        guidePaint.setColor(Color.parseColor("#CBD5E1"));
        guidePaint.setAntiAlias(true);
        guidePaint.setStyle(Paint.Style.STROKE);
        guidePaint.setStrokeWidth(12f);
        guidePaint.setPathEffect(new DashPathEffect(new float[]{20f, 20f}, 0));
        guidePaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewWidth = getWidth();
        int viewHeight = getHeight();

        if (viewWidth == 0 || viewHeight == 0) return;

        guidePaint.setTextSize(viewHeight * 0.55f);
        Rect bounds = new Rect();
        guidePaint.getTextBounds(targetText, 0, targetText.length(), bounds);
        
        float x = viewWidth / 2f;
        float y = (viewHeight / 2f) + (bounds.height() / 2f);

        canvas.drawText(targetText, x, y, guidePaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(x, y);
                mX = x;
                mY = y;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - mX);
                float dy = Math.abs(y - mY);
                if (dx >= touchTolerance || dy >= touchTolerance) {
                    drawPath.quadTo(mX, mY, (x + mX) / 2, (y + mX) / 2);
                    mX = x;
                    mY = y;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                drawPath.lineTo(mX, mY);
                invalidate();
                break;
        }
        return true;
    }

    public void setTargetText(String text) {
        this.targetText = text;
        clearCanvas();
    }

    public void clearCanvas() {
        drawPath.reset();
        invalidate();
    }
}
