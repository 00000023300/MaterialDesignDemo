package com.example.materialdesigndemo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.materialdesigndemo.R;
import com.example.materialdesigndemo.adapter.NewsAdapter;
import com.example.materialdesigndemo.adapter.NewsGridAdapter;
import com.example.materialdesigndemo.model.News;
import com.example.materialdesigndemo.utils.HttpsUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    //常量：聚合数据的url网址合handle的消息
    private static final String NEWS_URL="http://v.juhe.cn/toutiao/index";
    private static final int GET_NEWS = 1;

    //控件对象
    private Toolbar toolbar;
    private RecyclerView rvNews;
    private SwipeRefreshLayout refreshLayout;

    //数据处理对象
    private NewsHandler handler;
    private List<News> newsList;
    private NewsAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        handler=new NewsHandler(this);
        initView();
        initData();
    }

    private void initData() {
        String appKey = "a25670c0eb8d41bc76f25de6b556547a";
        String url = NEWS_URL+"?key="+appKey+"&type=top";
        Request request = new Request.Builder().url(url).build();
        HttpsUtil.getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("NewsListActivity",e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    //1.获取响应主体的json字符串
                    String json = response.body().string();
                    //2.使用FastJson库解析json字符串
                JSONObject obj =  JSON.parseObject(json);
                JSONObject result =  obj.getJSONObject("result");
                if(result!=null){
                    JSONArray data = result.getJSONArray("data");
                    if(data != null && !data.isEmpty()){
                        Message msg = handler.obtainMessage();
                        msg.what=GET_NEWS;
                        msg.obj = data.toJSONString();
                        handler.sendMessage(msg);
                    }
                }
                }else{
                    Log.e("NewsListActivity",response.message());
                }
            }
        });

    }

    private void initView() {
        //1.初始化Toolbar
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("RecyclerView示例");
        setSupportActionBar(toolbar);

        //2.初始化RecyclerView列表合自动刷新布局
        rvNews = findViewById(R.id.rv_news);
        refreshLayout = findViewById(R.id.refresh);

        //3.设置SwipeRefreshLayout的事件监听合进度条颜色
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

        static class NewsHandler extends Handler{
        private WeakReference<Activity> ref;
        public NewsHandler(Activity activity){
            this.ref = new WeakReference<>(activity);
        }

            @Override
            public void handleMessage(Message msg) {
            final RecyclerViewActivity activity = (RecyclerViewActivity) this.ref.get();
            if(msg.what == GET_NEWS){
                //获取数据
                String json = (String) msg.obj;
                activity.newsList = JSON.parseArray(json,News.class);

                //设置RecyclerView的分割线合布局
//                DividerItemDecoration decoration = new DividerItemDecoration(activity);
//                activity.rvNews.addItemDecoration(decoration);
                activity.rvNews.setLayoutManager(new LinearLayoutManager(activity));

                //设置Adapter
                activity.adapter = new NewsAdapter(activity.newsList);
                activity.rvNews.setAdapter(activity.adapter);

                //设置事件监听
                activity.adapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener(){
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(activity, NewsDetailActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("news", activity.newsList.get(position));
                        intent.putExtras(bundle);
                        activity.startActivity(intent);
                    }
                });
            }
                super.handleMessage(msg);
            }
        }

    @Override
    public void onRefresh() {
        initData();
        refreshLayout.postDelayed(new Runnable(){
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        },3000);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recycler_style,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_linear:
                rvNews.setLayoutManager(new LinearLayoutManager(this));
                adapter = new NewsAdapter(newsList);
                rvNews.setAdapter(adapter);
                break;
            case R.id.item_grid:
//                rvNews.setLayoutManager(new GridLayoutManager(this,2));
//                NewsGridAdapter adapter = new NewsGridAdapter(newsList);
//                rvNews.setAdapter(adapter);
//                break;
            case R.id.item_stagger:
//                rvNews.setLayoutManager(new StaggeredGridLayoutManager(3,LinearLayoutManager.VERTICAL));
//                adapter = new NewsGridAdapter(newsList);
//                rvNews.setAdapter(adapter);
//                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
