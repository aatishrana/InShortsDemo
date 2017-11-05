package com.aatishrana.inshortsdemo.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Aatish on 11/5/2017.
 */

public class MyInnerLinearLayout extends LinearLayoutManager
{
    private int lastPosition = -1;

    public MyInnerLinearLayout(Context context, int orientation, boolean reverseLayout)
    {
        super(context, orientation, reverseLayout);
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        animate(dx);
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    private void animate(int dx)
    {
        if (dx > 0)
        {
            //scrolling right
            int position = findFirstVisibleItemPosition();
            View view = findViewByPosition(position);

            if (view != null && position != lastPosition)
            {
                ObjectAnimator scalex = ObjectAnimator.ofFloat(view, "scaleX", 1.05f);
                scalex.setDuration(300);
                ObjectAnimator scaley = ObjectAnimator.ofFloat(view, "scaleY", 1.05f);
                scaley.setDuration(300);

                AnimatorSet animset = new AnimatorSet();
                animset.play(scaley).with(scalex);
                animset.start();

                lastPosition = position;
            }
        } else
        {
            //scrolling left
            int position = findFirstVisibleItemPosition();
            View view = findViewByPosition(position + 1);
            if (view != null)
            {
                ObjectAnimator scalex = ObjectAnimator.ofFloat(view, "scaleX", 0.95f);
                scalex.setDuration(300);
                ObjectAnimator scaley = ObjectAnimator.ofFloat(view, "scaleY", 0.95f);
                scaley.setDuration(300);

                AnimatorSet animset = new AnimatorSet();
                animset.play(scaley).with(scalex);
                animset.start();

            }

        }
    }
}
