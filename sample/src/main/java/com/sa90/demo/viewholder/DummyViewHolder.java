package com.sa90.demo.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.sa90.demo.R;

/**
 * Created by Saurabh on 4/2/16.
 */
public class DummyViewHolder extends RecyclerView.ViewHolder {

    public TextView tv;

    public DummyViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.tv);
    }
}
