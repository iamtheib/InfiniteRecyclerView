package com.sa90.demo.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.sa90.demo.R;

/**
 * Created by Saurabh on 6/2/16.
 */
public class LoadingViewHolder extends RecyclerView.ViewHolder {

    public ImageView loadingImage;

    public LoadingViewHolder(View itemView) {
        super(itemView);
        loadingImage = (ImageView) itemView.findViewById(R.id.loadingImage);
    }
}
