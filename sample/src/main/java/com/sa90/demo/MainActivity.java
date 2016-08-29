package com.sa90.demo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.sa90.demo.adapter.SampleAdapter;
import com.sa90.infiniterecyclerview.InfiniteRecyclerView;
import com.sa90.infiniterecyclerview.listener.OnLoadMoreListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    InfiniteRecyclerView irv;
    SampleAdapter mSampleAdapter;
    ArrayList<String> mDummy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDummy = new ArrayList<>();
        setupDummyData();

        mSampleAdapter = new SampleAdapter(this, mDummy);
        irv = (InfiniteRecyclerView) findViewById(R.id.irv);
        irv.setHasFixedSize(true);
        irv.setAdapter(mSampleAdapter);
        irv.setLayoutManager(new LinearLayoutManager(this));
        irv.setOnLoadMoreListener(mLoadMoreListener);
    }

    private OnLoadMoreListener mLoadMoreListener = new OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            Log.v("Main", "Load more fired");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    addMoreData();
                    irv.moreDataLoaded(15, 10);
                    irv.setShouldLoadMore(false);
                }
            }, 5000);
        }
    };

    private void setupDummyData() {
        mDummy.add("Text 1");
        mDummy.add("Text 2");
        mDummy.add("Text 3");
        mDummy.add("Text 4");
        mDummy.add("Text 5");
        mDummy.add("Text 6");
        mDummy.add("Text 7");
        mDummy.add("Text 8");
        mDummy.add("Text 9");
        mDummy.add("Text 11");
        mDummy.add("Text 12");
        mDummy.add("Text 13");
        mDummy.add("Text 14");
        mDummy.add("Text 15");
    }

    private void addMoreData() {
        mDummy.add("Text 16");
        mDummy.add("Text 17");
        mDummy.add("Text 18");
        mDummy.add("Text 19");
        mDummy.add("Text 20");
        mDummy.add("Text 21");
        mDummy.add("Text 22");
        mDummy.add("Text 23");
        mDummy.add("Text 24");
        mDummy.add("Text 25");
    }
}
