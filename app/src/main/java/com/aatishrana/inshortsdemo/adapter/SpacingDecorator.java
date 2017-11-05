package com.aatishrana.inshortsdemo.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Aatish on 11/4/2017.
 */

public class SpacingDecorator extends RecyclerView.ItemDecoration
{
    private int spacing;

    public SpacingDecorator(int spacing)
    {
        this.spacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
    {
        outRect.left = spacing;
        outRect.bottom = spacing;
    }
}
