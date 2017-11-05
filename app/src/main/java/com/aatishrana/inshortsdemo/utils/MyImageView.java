package com.aatishrana.inshortsdemo.utils;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Aatish on 11/5/2017.
 */

public class MyImageView extends AppCompatImageView
{
    private float aspectRatio = 1.77f;

    public MyImageView(Context context)
    {
        super(context);
    }

    public MyImageView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void setAspectRatio(float ratio)
    {
        aspectRatio = ratio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        final int width = MeasureSpec.getSize(widthMeasureSpec);
        heightMeasureSpec = View.MeasureSpec.makeMeasureSpec((int) (width * aspectRatio), View.MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
