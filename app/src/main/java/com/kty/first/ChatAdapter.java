package com.kty.first;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    private List<ChatData> mDataset;
    private String myNickName;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView TextView_message;
        public TextView TextView_nickname;
        public View rootView;
        public MyViewHolder(View v) {
            super(v);
            rootView= v;
            TextView_nickname=v.findViewById(R.id.TextView_nickname);
            TextView_message=v.findViewById(R.id.TextView_message);
            v.setClickable(true);
            v.setEnabled(true);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)//생성자
    public ChatAdapter(List<ChatData> myDataset, Context context,String myNick) {
        mDataset = myDataset;
        Fresco.initialize(context);
        myNickName= myNick;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ChatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_chat, parent, false);// 리스트 화면을 보여주는 xml  그리고, 그 한줄한줄당 내용이 들어가있는 xml 그것을 넣어주는것이다.
//지금여기 디자인이 없다.
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //값세팅이 된다.

        String message= mDataset.get(position).getMessage();
        holder.TextView_message.setText(message);
        String nickname= mDataset.get(position).getMessage();
        holder.TextView_nickname.setText(nickname);

        //위치를 잡아준다.
        if(mDataset.get(position).getNickname().equals(this.myNickName)) {
            holder.TextView_message.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            holder.TextView_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        }
        else {
            holder.TextView_message.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.TextView_nickname.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        //삼항 연산자
        return mDataset == null ? 0 :  mDataset.size();
    }

    public ChatData getChat(int position) {
        return mDataset != null ? mDataset.get(position) : null;
    }

    public void addChat(ChatData chat) {
        mDataset.add(chat);
        notifyItemInserted(mDataset.size()-1); //갱신
    }
}
