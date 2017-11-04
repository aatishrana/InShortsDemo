package com.aatishrana.inshortsdemo.model.itemData;

/**
 * Created by Aatish on 11/4/2017.
 */

public class CardItemMultiImage
{
    private final int height, width;
    private final String id, storyUrl, image_url;
    private final String placeHolderAlt, placeHolderData, placeHolderType;

    public CardItemMultiImage(int height, int width, String id, String storyUrl, String image_url, String placeHolderAlt, String placeHolderData, String placeHolderType)
    {
        this.height = height;
        this.width = width;
        this.id = id;
        this.storyUrl = storyUrl;
        this.image_url = image_url;
        this.placeHolderAlt = placeHolderAlt;
        this.placeHolderData = placeHolderData;
        this.placeHolderType = placeHolderType;
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public String getId()
    {
        return id;
    }

    public String getStoryUrl()
    {
        return storyUrl;
    }

    public String getImage_url()
    {
        return image_url;
    }

    public String getPlaceHolderAlt()
    {
        return placeHolderAlt;
    }

    public String getPlaceHolderData()
    {
        return placeHolderData;
    }

    public String getPlaceHolderType()
    {
        return placeHolderType;
    }

    @Override
    public String toString()
    {
        return "CardItemMultiImage{" +
                "\n\theight=" + height +
                ",\n\t width=" + width +
                ",\n\t id='" + id + '\'' +
                ",\n\t storyUrl='" + storyUrl + '\'' +
                ",\n\t image_url='" + image_url + '\'' +
                ", \n\tplaceHolderAlt='" + placeHolderAlt + '\'' +
                ", \n\tplaceHolderData='" + placeHolderData + '\'' +
                ", \n\tplaceHolderType='" + placeHolderType + '\'' +
                '}';
    }
}
