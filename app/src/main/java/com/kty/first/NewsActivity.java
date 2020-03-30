package com.kty.first;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] mDataset={"1","2"};
    RequestQueue queue;

    //b0e9dce621354d4bb4805cf8809d65b0   뉴스 API 키
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news);
        recyclerView =  findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        queue= Volley.newRequestQueue(this);
        getNews();
        // specify an adapter (see also next example)
    }
    public void getNews(){
        // Instantiate the RequestQueue.
        String url ="http://newsapi.org/v2/top-headlines?country=kr&apiKey=b0e9dce621354d4bb4805cf8809d65b0";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //리스폰스 받아서 리스트에 넣어줌
                        try {
                            JSONObject jsonObject= new JSONObject(response);
                            JSONArray articleArray=  jsonObject.getJSONArray("articles");
                            List<NewsData> DataList = new ArrayList<>();

                            for(int i=0;i<articleArray.length();i++){
                                JSONObject jsonObject1= articleArray.getJSONObject(i);
                                NewsData newsData =new NewsData();
                                newsData.setTitle(jsonObject1.getString("title"));
                                if(!(jsonObject1.getString("description").isEmpty())){
                                    newsData.setDescription(jsonObject1.getString("description"));
                                }else{
                                    newsData.setDescription("-");
                                }
                                newsData.setUrlToImage(jsonObject1.getString("urlToImage"));

                                DataList.add(newsData);
                            }


                            mAdapter = new MyAdapter(DataList,NewsActivity.this,new View.OnClickListener(){
                                @Override
                                public void onClick(View v){
                                    if(v.getTag()!=null){
                                        int position=(int)v.getTag();
                                        //본문만 넘길까 //전체를 다 넘길까
                                          //2-1.하나씩 다넘기기
                                          //2-2.한번에 다넘기기 NewsData클래스 통채로 이떄 ,importSerializable 이 필요한것이다.
                                        Intent intent = new Intent(NewsActivity.this, NewsDetail.class);
                                        intent.putExtra("news", ((MyAdapter)mAdapter).getNews(position));
                                        startActivity(intent);

                                    }

                                }

                            });
                            recyclerView.setAdapter(mAdapter);

                        }catch(JSONException e){
                            e.printStackTrace();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error",error.toString());
                mDataset[1]="That didn't work!";
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);


    };
    //1. 화면이 로딩되면   뉴스정보를 받아 온다.

    //2. 정보 -> 어뎁터에 넘겨준다.
    //3. 어뎁터 셋팅.

}
