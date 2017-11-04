package com.aatishrana.inshortsdemo.base;

/**
 * Created by Aatish on 11/4/2017.
 */

public interface PresenterFactory<T extends Presenter>
{
    T create();
}
