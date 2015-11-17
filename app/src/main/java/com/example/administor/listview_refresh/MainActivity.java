package com.example.administor.listview_refresh;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administor.listview_refresh.Views.CustomView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    private SlidingLayout slidingLayout;
    private ListView contentList;
    private ArrayAdapter<String> contentListAdapter;
    private String[] contentItems = {
            "Content Item 1", "Content Item 2", "Content Item 3",
            "Content Item 4", "Content Item 5", "Content Item 6", "Content Item 7",
            "Content Item 8", "Content Item 9", "Content Item 10", "Content Item 11",
            "Content Item 12", "Content Item 13", "Content Item 14", "Content Item 15",
            "Content Item 16"
    };

    private ArrayList<String> mArrays = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0; i<contentItems.length; i++) {
            mArrays.add(contentItems[i]);
        }
//        slidingLayout = (SlidingLayout) findViewById(R.id.sliding_layout);
//        Button showLeftButton = (Button) findViewById(R.id.show_left_menu);
//        Button showRightButton = (Button) findViewById(R.id.show_right_menu);
//        contentList = (ListView) findViewById(R.id.contentList);
//        contentListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contentItems);
//        contentList.setAdapter(contentListAdapter);
//        mTextView = (TextView) findViewById(R.id.tv);
//        mTextView.setText("");
//        String product = Build.PRODUCT;
//        mTextView.append(product + "\n");
//        mTextView.append(Build.MANUFACTURER + "\n");
//        mTextView.append(Build.DISPLAY + "\n");
//        mTextView.append(Build.VERSION.SDK);
//        checkUpdate();

        final CustomView listView = (CustomView) findViewById(R.id.customview);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mArrays);
        listView.setAdapter(adapter);
        listView.setonRefreshListener(new CustomView.OnRefreshListener() {

            @Override
            public void onRefresh() {
                new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mArrays.add("add a new one");
                        return null;
                    }

                    protected void onPostExecute(Void result) {
                        adapter.notifyDataSetChanged();
                        listView.onRefreshComplete();
                    };

                }.execute();
            }


        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
