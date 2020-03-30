package com.kty.first;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<NewsData> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView TextView_title;
        public TextView TextView_content;
        public SimpleDraweeView ImageView_title;
        public MyViewHolder(View v) {
            super(v);
            TextView_title=v.findViewById(R.id.TextView_title);
            TextView_content=v.findViewById(R.id.TextView_content);
            ImageView_title= v.findViewById(R.id.ImageView_title);
            }
    }

    // Provide a suitable constructor (depends on the kind of dataset)//생성자
    public MyAdapter(List<NewsData> myDataset, Context context) {
        mDataset = myDataset;
        Fresco.initialize(context);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_news, parent, false);// 리스트 화면을 보여주는 xml  그리고, 그 한줄한줄당 내용이 들어가있는 xml 그것을 넣어주는것이다.

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //값세팅이 된다.

        String text= mDataset.get(position).getTitle();
        holder.TextView_title.setText(text);
        String description =mDataset.get(position).getDescription();
        holder.TextView_content.setText(description);
        Uri uri = Uri.parse(mDataset.get(position).getUrlToImage());
        holder.ImageView_title.setImageURI(uri);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return mDataset == null ? 0 :  mDataset.size();
    }
    public NewsData getNews(int position) {
        return mDataset != null ? mDataset.get(position) : null;
    }

}
