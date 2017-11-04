package com.aatishrana.inshortsdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aatishrana.inshortsdemo.model.CardItem;
import com.aatishrana.inshortsdemo.model.itemData.CardItemImage;
import com.aatishrana.inshortsdemo.utils.Help;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aatish on 11/4/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private final int VIEW_TYPE_SINGLE_IMAGE = 0;
    private final int VIEW_TYPE_MULTIPLE_IMAGE = 1;

    private List<CardItem> data;

    public MyAdapter()
    {
        data = new ArrayList<>();
    }

    public void setData(List<CardItem> data)
    {
        Help.L("data set in adapter");
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_SINGLE_IMAGE)
            return new SingleImageVH(inflater.inflate(R.layout.item_image, parent, false));
        else if (viewType == VIEW_TYPE_MULTIPLE_IMAGE)
            return new MultiImageVH(inflater.inflate(R.layout.item_multi_image, parent, false));
        else
        {
            Help.E("viewType is null");
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        if (holder != null)
        {
            CardItem obj = data.get(position);
            if (holder instanceof SingleImageVH && obj.getType().equalsIgnoreCase("Image"))
            {
                SingleImageVH singleImageVH = (SingleImageVH) holder;
                CardItemImage cardData = (CardItemImage) obj.getData();
                Picasso.with(holder.itemView.getContext())
                        .load(cardData.getImgUrl())
//                        .placeholder(cardData.getPlaceHolderColor())
                        .into(singleImageVH.imageView);
            } else
                Help.E("holder is not SingleImageVH " + obj);
        } else
            Help.E("holder is null");
    }

    @Override
    public int getItemViewType(int position)
    {
        if (data.get(position).getType().equalsIgnoreCase("MULTI_IMAGE_CARD"))
            return VIEW_TYPE_MULTIPLE_IMAGE;
        else
            return VIEW_TYPE_SINGLE_IMAGE;
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }


    public class SingleImageVH extends RecyclerView.ViewHolder
    {
        ImageView imageView;

        public SingleImageVH(View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.item_image_iv);
        }
    }

    public class MultiImageVH extends RecyclerView.ViewHolder
    {

        public MultiImageVH(View itemView)
        {
            super(itemView);
        }
    }
}
