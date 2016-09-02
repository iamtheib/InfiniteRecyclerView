[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-InfiniteRecyclerView-green.svg?style=true)](https://android-arsenal.com/details/1/3277)
Infinite Recycler View
=================

A RecyclerView Adapter which allows you to have an Infinite scrolling list in your apps. This library offers you a custom adapter to use with any recycler view. You get a callback when the user is about to reach the bottom (or top, depending on your configuration), of the list, which you can use to load more data.

You can also customize what the loading view at the bottom of the list looks like.

**Note:** This library should be able to work with any layout manager, but has only been tested with `LinearLayoutManager`

Change-logs
-------
Check out the [Release Notes](https://github.com/saurabharora90/InfiniteRecyclerView/releases "Releases") for the change-logs in each release.

Demo
-------
This is the sample app in action:

![Demo](https://raw.githubusercontent.com/saurabharora90/InfiniteRecyclerView/master/assets/demo.gif)

Usage
-------
Add a dependency to your `build.gradle`:

    dependencies {
        compile 'com.sa90.infiniterecyclerview:library:1.3'
    }

You can use any variation of RecyclerView in your layout, just make sure your adapter extends:

`com.sa90.infiniterecyclerview.InfiniteAdapter`

InfiniteAdapter is a abstract class, hence classes extending from it need to implement the following methods:

 - `getLoadingViewHolder(ViewGroup parent)`: Returns the loading view to be shown at the bottom of the list
 - `getCount()`: The count of the number of items in the list. This does not include the loading item
 - `getViewType(int position)`: Return the view type of the item at `position` for the purposes of view recycling. 0 index is reserved for the loading view. So this function cannot return 0
 - `onCreateView(ViewGroup parent, int viewType)`: Called when RecyclerView needs a new ViewHolder of the given type to represent an item. This is the same as the onCreateViewHolder method in RecyclerView.Adapter, except that it internally detects and handles the creation on the loading footer
 - `onBindViewHolder(RecyclerView.ViewHolder holder, int position)`: Subclasses should override this method, to actually bind the view data, but always call `super.onBindViewHolder(holder, position)` to enable the adapter to calculate whether the load more callback should be invoked
 - *Optional* `getVisibleThreshold()`: Returns the number of scrollable items that should be left (threshold) in the list before `OnLoadMoreListener` will be called. You can override this to return a preferred threshold, or leave it to use the default

API For InfiniteAdapter
-------
 - `setOnLoadMoreListener`: Registers a callback to be notified when there is a need to load more data
 - `moreDataLoaded(int, int)`: This informs the adapter that data has been loaded. This also calls `notifyItemRangeInserted(int, int)` method, so the implementing class only needs to call this method
 - `setShouldLoadMore(boolean)`: Set as false when you don't want the recycler view to load more data. This will also remove the loading view
 - `setIsReversedScrolling(boolean)`: Set as true if you want the endless scrolling to be as the user scrolls to the top of the list, instead of bottom

License
-------

    Copyright 2016 Saurabh Arora

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
