package com.sa90.infiniterecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.sa90.infiniterecyclerview.listener.OnLoadMoreListener;

/**
 * Created by Saurabh on 6/2/16.
 * This supports a callback to notify when to load more items.
 *
 * Implementing Activities/fragments should also call {@link InfiniteRecyclerView#moreDataLoaded()}
 * to inform the recycler view that more data has been loaded.
 */
public class InfiniteRecyclerView extends RecyclerView {

    private static final int VISIBLE_THRESHOLD = 5;

    private boolean loading, shouldLoadMore;
    private OnLoadMoreListener onLoadMoreListener;
    private LinearLayoutManager mLayoutManager;
    private InfiniteAdapter mAdapter;
    private int mVisibleThreshold;

    public InfiniteRecyclerView(Context context) {
        super(context);
        init(null);
    }

    public InfiniteRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.InfiniteRecyclerView, 0, 0);
        init(attr);
    }

    public InfiniteRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.InfiniteRecyclerView, 0, 0);
        init(attr);
    }

    private void init(TypedArray attr) {
        addOnScrollListener(mScrollListener);
        loading = false;
        shouldLoadMore = true;
        if(attr!=null) {
            mVisibleThreshold = attr.getInt(R.styleable.InfiniteRecyclerView_irv_visible_threshold, VISIBLE_THRESHOLD);
        }
        else
            mVisibleThreshold = VISIBLE_THRESHOLD;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof InfiniteAdapter)
            mAdapter = (InfiniteAdapter) adapter;
        else
            throw new UnsupportedOperationException("This RecyclerView's adapter should extend from InfiniteScrollAdapter");
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        if(layout instanceof LinearLayoutManager)
            mLayoutManager = (LinearLayoutManager)layout;
        else
            throw new UnsupportedOperationException("This recycler view can only be used with LinearLayoutManager");
    }

    @SuppressWarnings("unused")
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    private OnScrollListener mScrollListener = new OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = getChildCount();
            int totalItemCount = mAdapter.getItemCount();
            int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

            if (!loading &&
                    (totalItemCount - visibleItemCount) <= (firstVisibleItem + mVisibleThreshold)) {
                // End has been reached
                loading = true;
                if(onLoadMoreListener!=null)
                    onLoadMoreListener.onLoadMore();
            }
        }
    };

    /**
     * This informs the RecyclerView that data has been loaded.
     *
     * This also calls the attached adapter's {@link Adapter#notifyDataSetChanged()} method,
     * so the implementing class only needs to call this method
     */
    @SuppressWarnings("unused")
    public void moreDataLoaded() {
        loading = false;
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Set as false when you don't want the recycler view to load more data
     * @param loadMore
     */
    @SuppressWarnings("unused")
    public void setShouldLoadMore(boolean loadMore) {
        this.shouldLoadMore = loadMore;
        mAdapter.setShouldLoadMore(shouldLoadMore);
    }
}
