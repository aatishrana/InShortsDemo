package com.aatishrana.inshortsdemo.base;

import android.content.Context;
import android.support.v4.content.Loader;

/**
 * Created by Aatish on 11/4/2017.
 */

public final class PresenterLoader<T extends Presenter> extends Loader<T>
{

    private final PresenterFactory<T> factory;
    private T presenter;

    PresenterLoader(Context context, PresenterFactory<T> factory)
    {
        super(context);
        this.factory = factory;
    }

    @Override
    protected void onStartLoading()
    {
        if (presenter != null)
        {
            deliverResult(presenter);
            return;
        }

        forceLoad();
    }

    @Override
    protected void onForceLoad()
    {
        presenter = factory.create();

        deliverResult(presenter);
    }


    @Override
    protected void onReset()
    {
        if (presenter != null)
        {
            presenter.onDestroyed();
            presenter = null;
        }
    }

    public T getPresenter()
    {
        return presenter;
    }
}
