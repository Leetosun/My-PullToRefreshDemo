package com.leomo.re.my_pulltorefreshdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView listView;

    private List<String> dataList;

    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        dataList = new ArrayList<>();
        setData();
        adapter = new Adapter(MainActivity.this, dataList);
        listView = (PullToRefreshListView) findViewById(R.id.lv_refresh);
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setAdapter(adapter);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                dataList.clear();
                count = 1;
                /*
                 * setData(); adapter.notifyDataSetChanged(); listView.postDelayed(new Runnable() {
                 * @Override public void run() { listView.onRefreshComplete(); } }, 1000);
                 */
                new LoadTask(MainActivity.this).execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                /*
                 * setData(); adapter.notifyDataSetChanged(); listView.postDelayed(new Runnable() {
                 * @Override public void run() { listView.onRefreshComplete(); } }, 1000);
                 */
                new LoadTask(MainActivity.this).execute();
            }
        });
    }

    private static class LoadTask extends AsyncTask<Void, Void, String> {

        private MainActivity mainActivity;

        public LoadTask(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                Thread.sleep(1000);
                mainActivity.setData();
                return "success";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equals("success")) {
                mainActivity.adapter.notifyDataSetChanged();
                mainActivity.listView.onRefreshComplete();
            }
        }
    }

    private int count = 1;

    private void setData() {
        for (int i = 0; i < 10; i++) {
            dataList.add("Hello " + count);
            count++;
        }
    }
}
