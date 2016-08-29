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
 * Implementing Activities/fragments should also call {@link InfiniteRecyclerView#moreDataLoaded(int, int)}
 * to inform the recycler view that more data has been loaded.
 */
public class InfiniteRecyclerView extends RecyclerView {

    private static final int VISIBLE_THRESHOLD = 5;

    private boolean loading, shouldLoadMore;
    // Used to indicate the infinite scrolling should be bottom up
    private boolean isReversedScrolling;
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
        isReversedScrolling = false;
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

            if (shouldLoadMore && !loading) {
                int firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();
                boolean hasReachedThreshold;
                if (isReversedScrolling) {
                    hasReachedThreshold = firstVisibleItem <= mVisibleThreshold;
                } else {
                    int visibleItemCount = getChildCount();
                    int totalItemCount = mAdapter.getItemCount();
                    hasReachedThreshold = (totalItemCount - visibleItemCount) <= (firstVisibleItem + mVisibleThreshold);
                }

                if (hasReachedThreshold) {
                    loading = true;
                    if (onLoadMoreListener != null)
                        onLoadMoreListener.onLoadMore();
                }
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
    @Deprecated
    public void moreDataLoaded() {
        loading = false;
        mAdapter.notifyDataSetChanged();
    }

    /**
     * This informs the RecyclerView that <code>itemCount</code> more data has been loaded,
     * starting from <code>positionStart</code>
     *
     * This also calls the attached adapter's {@link RecyclerView.Adapter#notifyItemRangeInserted(int, int)} method,
     * so the implementing class only needs to call this method
     *
     * @param positionStart Position of the first item that was inserted
     * @param itemCount Number of items inserted
     *
     */
    @SuppressWarnings("unused")
    public void moreDataLoaded(int positionStart, int itemCount) {
        loading = false;
        mAdapter.notifyItemRangeInserted(positionStart, itemCount);
    }

    /**
     * Set as false when you don't want the recycler view to load more data. This will also remove the loading view
     * @param loadMore
     */
    @SuppressWarnings("unused")
    public void setShouldLoadMore(boolean loadMore) {
        this.shouldLoadMore = loadMore;
        mAdapter.setShouldLoadMore(shouldLoadMore);
    }

    /**
     * Set as true if you want the endless scrolling to be as the user scrolls to the top
     * of the list, instead of bottom
     * @param reversed
     */
    public void setIsReversedScrolling(boolean reversed) {
        this.isReversedScrolling = reversed;
        mAdapter.setIsReversedScrolling(isReversedScrolling);
    }
}
