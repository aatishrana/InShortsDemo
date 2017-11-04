package com.aatishrana.inshortsdemo.model;

import com.aatishrana.inshortsdemo.model.itemData.CardItemData;

import java.util.List;

/**
 * Created by Aatish on 11/4/2017.
 */

public class CardItem
{
    private final String id, shareImage, shareText, title, type;
    private final long version, rank;
    private final List<String> categories;
    private final CardItemData data;

    public CardItem(String id, String shareImage, String shareText, String title, String type, long version, long rank, List<String> categories, CardItemData data)
    {
        this.id = id;
        this.shareImage = shareImage;
        this.shareText = shareText;
        this.title = title;
        this.type = type;
        this.version = version;
        this.rank = rank;
        this.categories = categories;
        this.data = data;
    }

    public String getId()
    {
        return id;
    }

    public String getShareImage()
    {
        return shareImage;
    }

    public String getShareText()
    {
        return shareText;
    }

    public String getTitle()
    {
        return title;
    }

    public String getType()
    {
        return type;
    }

    public long getVersion()
    {
        return version;
    }

    public long getRank()
    {
        return rank;
    }

    public List<String> getCategories()
    {
        return categories;
    }

    public CardItemData getData()
    {
        return data;
    }

    @Override
    public String toString()
    {
        return "CardItem{" +
                "\nid='" + id + '\'' +
                ",\n shareImage='" + shareImage + '\'' +
                ",\n shareText='" + shareText + '\'' +
                ",\n title='" + title + '\'' +
                ",\n type='" + type + '\'' +
                ",\n version=" + version +
                ",\n rank=" + rank +
                ",\n categories=" + categories +
                ",\n data=" + data +
                '}';
    }
}
