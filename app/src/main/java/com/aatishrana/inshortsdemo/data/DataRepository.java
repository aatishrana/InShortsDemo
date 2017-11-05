package com.aatishrana.inshortsdemo.data;

import com.aatishrana.inshortsdemo.model.CardItem;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Aatish on 11/5/2017.
 */

public interface DataRepository
{
    Observable<List<CardItem>> getData();
}

