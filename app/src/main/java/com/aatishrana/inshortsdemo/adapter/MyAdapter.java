package com.aatishrana.inshortsdemo.adapter;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aatishrana.inshortsdemo.R;
import com.aatishrana.inshortsdemo.model.CardItem;
import com.aatishrana.inshortsdemo.model.itemData.CardItemImage;
import com.aatishrana.inshortsdemo.model.itemData.CardItemMulti;
import com.aatishrana.inshortsdemo.utils.Help;
import com.aatishrana.inshortsdemo.utils.MyImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.aatishrana.inshortsdemo.utils.Const.mediaTypeMultipleImage;
import static com.aatishrana.inshortsdemo.utils.Const.mediaTypeSingleImage;

/**
 * Created by Aatish on 11/4/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private final int VIEW_TYPE_SINGLE_IMAGE = 0;
    private final int VIEW_TYPE_MULTIPLE_IMAGE = 1;
    private MyClickListener myClickListener;
    private List<CardItem> data;

    public MyAdapter(MyClickListener myClickListener)
    {
        this.myClickListener = myClickListener;
        data = new ArrayList<>();
    }

    public void setData(List<CardItem> data)
    {
        this.data = data;
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder)
    {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_MULTIPLE_IMAGE)
            return new MultiImageVH(inflater.inflate(R.layout.item_multi_image, parent, false));
        return new SingleImageVH(inflater.inflate(R.layout.item_image, parent, false));
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position)
    {
        if (holder != null)
        {
            CardItem obj = data.get(position);
            if (holder instanceof SingleImageVH && obj.getType().equalsIgnoreCase(mediaTypeSingleImage))
            {
                SingleImageVH singleImageVH = (SingleImageVH) holder;
                CardItemImage cardData = (CardItemImage) obj.getData();
                singleImageVH.imageView.setAspectRatio(cardData.getHeight() / cardData.getWidth());

                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setShape(GradientDrawable.RECTANGLE);
                gradientDrawable.setColor(cardData.getPlaceHolderColor());
                Picasso.with(holder.itemView.getContext())
                        .load(cardData.getImgUrl())
                        .placeholder(gradientDrawable)
                        .into(singleImageVH.imageView);

                //only for first item
                if (position == 0)
                    holder.itemView.setAlpha(1f);

            } else if (holder instanceof MultiImageVH && obj.getType().equalsIgnoreCase(mediaTypeMultipleImage))
            {
                MultiImageVH multiImageVH = (MultiImageVH) holder;
                CardItemMulti innerCards = (CardItemMulti) obj.getData();

                LinearLayoutManager ll = new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
                try
                {
                    SnapHelper snapHelper = new StartSnapHelper();
                    snapHelper.attachToRecyclerView(multiImageVH.recyclerView);
                } catch (IllegalStateException ex)
                {
                }

                multiImageVH.recyclerView.setLayoutManager(ll);
                multiImageVH.recyclerView.setAdapter(new MyInnerAdapter(innerCards.getData(), myClickListener));

            } else
                Help.E("something wrong");
        }
    }

    @Override
    public int getItemViewType(int position)
    {
        if (data.get(position).getType().equalsIgnoreCase(mediaTypeMultipleImage))
            return VIEW_TYPE_MULTIPLE_IMAGE;
        else
            return VIEW_TYPE_SINGLE_IMAGE;
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    @Override
    public long getItemId(int position)
    {
        return data.get(position).getRank();
    }


    public class SingleImageVH extends RecyclerView.ViewHolder
    {
        MyImageView imageView;

        public SingleImageVH(View itemView)
        {
            super(itemView);
            imageView = (MyImageView) itemView.findViewById(R.id.item_image_iv);
            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (myClickListener != null)
                    {
                        CardItem obj = data.get(getAdapterPosition());
                        myClickListener.onClick(obj.getShareImage(), getAdapterPosition(), false);
                    }
                }
            });
        }
    }

    public class MultiImageVH extends RecyclerView.ViewHolder
    {
        RecyclerView recyclerView;

        public MultiImageVH(View itemView)
        {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.item_multi_image_recyclerView);
        }
    }

    public interface MyClickListener
    {
        void onClick(String url, int position, boolean fromMultiImages);
    }
}
