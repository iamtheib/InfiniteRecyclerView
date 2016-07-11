[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-InfiniteRecyclerView-green.svg?style=true)](https://android-arsenal.com/details/1/3277)
Infinite Recycler View
=================

An extension of the Recycler View which allows you have a Infinite scrolling list in you apps. This library offers you a custom recycler view and a custom adapter to use with the recycler view. By combining the two together, you get a callback when the user is about to reach the bottom of the list, which you can use to load more data.

You can also customize what the loading view at the bottom of the list looks like.

**Note:** This library was built to solve a case in one of the app I am working on. Hence, it only work with `LinearLayoutManager`

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
    compile 'com.sa90.infiniterecyclerview:library:1.1'
}

and replace your recycler view with:

`com.sa90.infiniterecyclerview.InfiniteRecyclerView`

And make you adapter extend from:
`com.sa90.infiniterecyclerview.InfiniteAdapter`

The InfiniteAdapter is a abstract class, hence classes extending from it need to implement the following methods:

 - `getLoadingViewHolder(ViewGroup parent)`: Returns the loading view to be shown at the bottom of the list.
 - `getCount()`: The count of the number of items in the list. This does not include the loading item
 - `getViewType(int position)`: Return the view type of the item at `position` for the purposes of view recycling. 0 index is reserved for the loading view. So this function cannot return 0.
 - `onCreateView(ViewGroup parent, int viewType)`: Called when RecyclerView needs a new ViewHolder of the given type to represent an item. This is the same as the onCreateViewHolder method in RecyclerView.Adapter, except that it internally detects and handles the creation on the loading footer

API For InfiniteRecyclerView
-------
 - `setOnLoadMoreListener`: Registers a callback to be notified when there is a need to load more data
 - `moreDataLoaded`: This informs the RecyclerView that data has been loaded. This also calls the attached adapter's `notifyDataSetChanged()` method, so the implementing class only needs to call this method
 - `setShouldLoadMore`: Set as false when you don't want the recycler view to load more data. This will also remove the loading view

Customization
-------
Currently the library offers the following customization options:

 - `irv_visible_threshold`: Controls the number of scrollable items left (threshold) in the list before `OnLoadMoreListener` will be called

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
