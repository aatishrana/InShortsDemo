package com.aatishrana.inshortsdemo.model.itemData;

/**
 * Created by Aatish on 11/4/2017.
 */

public class CardItemImage implements CardItemData
{
    private final int height, width;
    private final String imgUrl;
    private final String placeHolderAlt, placeHolderData, placeHolderType;

    public CardItemImage(int height, int width, String imgUrl, String placeHolderAlt, String placeHolderData, String placeHolderType)
    {
        this.height = height;
        this.width = width;
        this.imgUrl = imgUrl;
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

    public String getImgUrl()
    {
        return imgUrl;
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
        return "CardItemImage{" +
                "\n\theight=" + height +
                ",\n\t width=" + width +
                ",\n\t imgUrl='" + imgUrl + '\'' +
                ",\n\t placeHolderAlt='" + placeHolderAlt + '\'' +
                ",\n\t placeHolderData='" + placeHolderData + '\'' +
                ",\n\t placeHolderType='" + placeHolderType + '\'' +
                '}';
    }
}
