package tudie.com.pulltorefreshlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.adapter.CommonAdapter;
import com.adapter.ViewHolder;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public List<String> data = new ArrayList<>();
    private CommonAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PullToRefreshListView pullToRefreshRV = (PullToRefreshListView) findViewById(R.id.pullToRefreshRV);
        GetList();
        adapter = new CommonAdapter<String>(this, R.layout.item_mode, data) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.textView, s);
            }
        };
        pullToRefreshRV.setAdapter(adapter);
        pullToRefreshRV.setMode(PullToRefreshBase.Mode.BOTH);
        //设置刷新回调
        pullToRefreshRV.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                pullToRefreshRV.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshRV.onRefreshComplete();
                        //模拟没有数据的情况
                        data.clear();
                        GetList();
                        adapter.notifyDataSetChanged();
                    }
                }, 3000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                pullToRefreshRV.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshRV.onRefreshComplete();
                        //模拟加载数据的情况
                        GetList();
                        adapter.notifyDataSetChanged();

                    }
                }, 3000);
            }
        });

    }


    private void GetList() {
        for (int i = 0; i < 5; i++) {
            data.add(i + "Data");
        }

    }
}
