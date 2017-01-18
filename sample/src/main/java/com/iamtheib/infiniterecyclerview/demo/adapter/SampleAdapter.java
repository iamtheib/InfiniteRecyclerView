package com.iamtheib.infiniterecyclerview.demo.adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.iamtheib.infiniterecyclerview.demo.R;
import com.iamtheib.infiniterecyclerview.demo.viewholder.DummyViewHolder;
import com.iamtheib.infiniterecyclerview.demo.viewholder.LoadingViewHolder;
import com.iamtheib.infiniterecyclerview.InfiniteAdapter;

import java.util.List;

/**
 * Created by Saurabh on 6/2/16.
 */
public class SampleAdapter extends InfiniteAdapter<RecyclerView.ViewHolder> {

    private List<String> sampleData;
    private Context mContext;

    public SampleAdapter(Context context, List<String> dummyData) {
        mContext = context;
        sampleData = dummyData;
    }

    @Override
    public RecyclerView.ViewHolder getLoadingViewHolder(ViewGroup parent) {
        View loadingView = LayoutInflater.from(mContext).inflate(R.layout.list_loading_view, parent, false);
        return new LoadingViewHolder(loadingView);
    }

    @Override
    public int getCount() {
        return sampleData.size();
    }

    @Override
    public int getViewType(int position) {
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View dummyView = LayoutInflater.from(mContext).inflate(R.layout.item_dummy, parent, false);
        return new DummyViewHolder(dummyView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            ObjectAnimator animator = ObjectAnimator.ofFloat(loadingViewHolder.loadingImage, "rotation", 0, 360);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setInterpolator(new LinearInterpolator());
            animator.setDuration(1000);
            animator.start();
            return;
        }
        else {
            ((DummyViewHolder) holder).tv.setText(sampleData.get(position));
        }

        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getVisibleThreshold() {
        return 2;
    }
}
