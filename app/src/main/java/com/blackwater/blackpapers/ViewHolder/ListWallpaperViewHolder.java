package com.blackwater.blackpapers.ViewHolder;

import com.blackwater.blackpapers.Interface.itemClickListener;
import com.blackwater.blackpapers.R;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

public class ListWallpaperViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    itemClickListener itemClickListener;

    public ImageView wallpaper;

    public void setItemClickListener(itemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ListWallpaperViewHolder(View itemView) {
        super(itemView);
        wallpaper = (ImageView)itemView.findViewById(R.id.image);
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v, getAdapterPosition());
    }
}
