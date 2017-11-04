package com.aatishrana.inshortsdemo.base;

/**
 * Created by Aatish on 11/4/2017.
 */

public interface Presenter<V> {
    void onViewAttached(V view);

    void onViewDetached();

    void onDestroyed();
}
