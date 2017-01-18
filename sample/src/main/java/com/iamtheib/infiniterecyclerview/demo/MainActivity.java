package com.iamtheib.infiniterecyclerview.demo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.iamtheib.infiniterecyclerview.InfiniteAdapter;
import com.iamtheib.infiniterecyclerview.demo.adapter.SampleAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView dummyRV;
    SampleAdapter mSampleAdapter;
    ArrayList<String> mDummyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDummyData = new ArrayList<>();
        setupDummyData();

        mSampleAdapter = new SampleAdapter(this, mDummyData);
        dummyRV = (RecyclerView) findViewById(R.id.dummy_rv);
        dummyRV.setHasFixedSize(true);
        dummyRV.setAdapter(mSampleAdapter);
        dummyRV.setLayoutManager(new LinearLayoutManager(this));
        mSampleAdapter.setOnLoadMoreListener(mLoadMoreListener);
    }

    private InfiniteAdapter.OnLoadMoreListener mLoadMoreListener = new InfiniteAdapter.OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            Log.v("Main", "Load more fired");

            final int currSize = mDummyData.size();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    addMoreData();
                    mSampleAdapter.moreDataLoaded(currSize, mDummyData.size() - currSize);
                    mSampleAdapter.setShouldLoadMore(false);
                }
            }, 5000);
        }
    };

    private void setupDummyData() {
        mDummyData.add("Text 1");
        mDummyData.add("Text 2");
        mDummyData.add("Text 3");
        mDummyData.add("Text 4");
        mDummyData.add("Text 5");
        mDummyData.add("Text 6");
        mDummyData.add("Text 7");
        mDummyData.add("Text 8");
        mDummyData.add("Text 9");
        mDummyData.add("Text 11");
        mDummyData.add("Text 12");
        mDummyData.add("Text 13");
        mDummyData.add("Text 14");
        mDummyData.add("Text 15");
    }

    private void addMoreData() {
        mDummyData.add("Text 16");
        mDummyData.add("Text 17");
        mDummyData.add("Text 18");
        mDummyData.add("Text 19");
        mDummyData.add("Text 20");
        mDummyData.add("Text 21");
        mDummyData.add("Text 22");
        mDummyData.add("Text 23");
        mDummyData.add("Text 24");
        mDummyData.add("Text 25");
    }
}
