package com.aatishrana.inshortsdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aatishrana.inshortsdemo.model.CardItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aatish on 11/4/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>
{
    private List<CardItem> data;

    public MyAdapter()
    {
        data = new ArrayList<>();
    }

    public void setData(List<CardItem> data)
    {
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        CardItem obj = data.get(position);
        holder.item_image_tv.setText(obj.toString());
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView item_image_tv;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            item_image_tv = (TextView) itemView.findViewById(R.id.item_image_tv);
        }
    }
}
