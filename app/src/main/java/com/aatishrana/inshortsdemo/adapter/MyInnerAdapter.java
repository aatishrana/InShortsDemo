package com.aatishrana.inshortsdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aatishrana.inshortsdemo.R;
import com.aatishrana.inshortsdemo.model.itemData.CardItemMultiImage;
import com.aatishrana.inshortsdemo.utils.MyImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Aatish on 11/4/2017.
 */

public class MyInnerAdapter extends RecyclerView.Adapter<MyInnerAdapter.ViewHolder>
{
    private List<CardItemMultiImage> data;
    private MyAdapter.MyClickListener myClickListener;

    public MyInnerAdapter(List<CardItemMultiImage> data, MyAdapter.MyClickListener myClickListener)
    {
        this.data = data;
        this.myClickListener = myClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.item_inner_image, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        CardItemMultiImage obj = data.get(position);
        holder.imageView.setAspectRatio(obj.getHeight() / obj.getWidth());
        Picasso.with(holder.itemView.getContext())
                .load(obj.getImage_url())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        MyImageView imageView;

        public ViewHolder(View itemView)
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
                        CardItemMultiImage obj = data.get(getAdapterPosition());
                        myClickListener.onClick(obj.getStoryUrl(), getAdapterPosition(), true);
                    }
                }
            });
        }
    }
}
