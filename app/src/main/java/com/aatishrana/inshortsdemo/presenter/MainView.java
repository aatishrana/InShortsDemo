package com.aatishrana.inshortsdemo.presenter;

import com.aatishrana.inshortsdemo.model.CardItem;

import java.util.List;

/**
 * Created by Aatish on 11/4/2017.
 */
public interface MainView
{
    void render(boolean isLoading, boolean hasError, List<CardItem> data);
}
