package com.sa90.infiniterecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Saurabh on 6/2/16.
 */
public abstract class InfiniteAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter {

    private static final int VIEW_TYPE_LOADING = 0;
    private boolean shouldLoadMore = true;

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_LOADING)
            return getLoadingViewHolder(parent);
        else
            return onCreateView(parent, viewType);
    }

    @Override
    public final int getItemCount() {
        int actualCount = getCount();
        if(getCount() == 0 || !shouldLoadMore)
            return actualCount;
        else
            return actualCount + 1;
    }

    @Override
    public final int getItemViewType(int position) {
        if(isLoadingView(position))
            return VIEW_TYPE_LOADING;
        else {
            int viewType = getViewType(position);
            if(viewType == VIEW_TYPE_LOADING)
                throw new IndexOutOfBoundsException("0 index is reserved for the loading view");
            else
                return viewType;
        }
    }

    private boolean isLoadingView(int position) {
        boolean value = false;
        if(position == getCount() && shouldLoadMore)
            value = true;

        return value;
    }

    public void setShouldLoadMore(boolean shouldLoadMore) {
        this.shouldLoadMore = shouldLoadMore;
    }

    /**
     * Returns the loading view to be shown at the bottom of the list.
     * @return loading view
     */
    public abstract RecyclerView.ViewHolder getLoadingViewHolder(ViewGroup parent);

    /**
     * The count of the number of items in the list. This does not include the loading item
     * @return number of items in list
     */
    public abstract int getCount();

    /**
     * Return the view type of the item at <code>position</code> for the purposes
     * of view recycling.
     *
     * <p>0 index is reserved for the loading view. So this function cannot return 0.
     *
     * @param position position to query
     * @return integer value identifying the type of the view needed to represent the item at
     *                 <code>position</code>. Type codes need not be contiguous.
     */
    public abstract int getViewType(int position);

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent
     * an item. This is the same as the onCreateViewHolder method in RecyclerView.Adapter,
     * except that it internally detects and handles the creation on the loading footer
     * @param parent
     * @param viewType
     * @return
     */
    public abstract VH onCreateView(ViewGroup parent, int viewType);
}
