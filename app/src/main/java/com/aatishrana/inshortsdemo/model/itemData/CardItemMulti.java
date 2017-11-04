package com.aatishrana.inshortsdemo.model.itemData;

import java.util.List;

/**
 * Created by Aatish on 11/4/2017.
 */

public class CardItemMulti implements CardItemData
{
    private List<CardItemMultiImage> data;

    public CardItemMulti(List<CardItemMultiImage> data)
    {
        this.data = data;
    }

    public List<CardItemMultiImage> getData()
    {
        return data;
    }

    @Override
    public String toString()
    {
        return "CardItemMulti{" +
                "data=" + data +
                '}';
    }
}
