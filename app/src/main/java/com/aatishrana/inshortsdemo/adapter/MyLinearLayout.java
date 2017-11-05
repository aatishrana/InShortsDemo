package com.aatishrana.inshortsdemo.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Aatish on 11/4/2017.
 */

public class MyLinearLayout extends LinearLayoutManager
{
    private int lastPosition = -1;
    private final int defaultPx, selectedPx;
    private int dy;

    public MyLinearLayout(Context context, int orientation, boolean reverseLayout)
    {
        super(context, orientation, reverseLayout);
        defaultPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, context.getResources().getDisplayMetrics());
        selectedPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources().getDisplayMetrics());
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state)
    {
        this.dy = dy;
        animate();
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    private void animate()
    {
        if (dy > 0)
        {
            //scrolling up
            int position = findFirstCompletelyVisibleItemPosition();
            View view = findViewByPosition(position);

            if (view != null && position != lastPosition)
            {
                ObjectAnimator scalex = ObjectAnimator.ofFloat(view, "scaleX", 1.05f);
                scalex.setDuration(300);
                ObjectAnimator scaley = ObjectAnimator.ofFloat(view, "scaleY", 1.05f);
                scaley.setDuration(300);
                ObjectAnimator alpha2 = ObjectAnimator.ofFloat(view, "alpha", 1f);
                alpha2.setDuration(300);
                AnimatorSet animset = new AnimatorSet();
                animset.play(alpha2).with(scaley).with(scalex);
                animset.start();

//                RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
//                lp.setMargins(selectedPx, defaultPx, selectedPx, defaultPx);
//                view.setLayoutParams(lp);
//                view.setAlpha(1f);
                lastPosition = position;
            }
        } else
        {
            //scrolling down
            int position = findFirstCompletelyVisibleItemPosition();
            View view = findViewByPosition(position + 1);
            if (view != null)
            {
                ObjectAnimator scalex = ObjectAnimator.ofFloat(view, "scaleX", 0.95f);
                scalex.setDuration(300);
                ObjectAnimator scaley = ObjectAnimator.ofFloat(view, "scaleY", 0.95f);
                scaley.setDuration(300);
                ObjectAnimator alpha2 = ObjectAnimator.ofFloat(view, "alpha", 0.2f);
                alpha2.setDuration(300);
                AnimatorSet animset = new AnimatorSet();
                animset.play(alpha2).with(scaley).with(scalex);
                animset.start();

//                RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
//                lp.setMargins(defaultPx, defaultPx, defaultPx, defaultPx);
//                view.setLayoutParams(lp);
//                view.setAlpha(0.2f);
            }

        }
    }
}
