package com.kty.first;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.CustomViewHolder> {

    private ArrayList<BoardData> arrayList;

    public BoardAdapter(ArrayList<BoardData> arrayList) {
        this.arrayList = arrayList;
    }

    //1.Inflater 로  미리 정의된 xml 을 메모리에 올린뒤,  그 View를 이용해 화면을 셋팅하는 과정
    public class CustomViewHolder extends RecyclerView.ViewHolder {

        protected ImageView ImageView_profile;
        protected TextView TextView_tv_name;
        protected TextView TextView_content_name;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ImageView_profile= (ImageView)itemView.findViewById(R.id.ImageView_profile);
            this.TextView_content_name=(TextView)itemView.findViewById(R.id.TextView_content_name);
            this.TextView_tv_name=(TextView)itemView.findViewById(R.id.TextView_tv_name);

        }
    }

    //2.리사이클러뷰 처음 생성될때의 생성주기
    @NonNull
    @Override
    public BoardAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //LayoutInflater는 xml에서 미리 정의해둔 틀을 실제 메모리에 올려주는 역할을 한다.
        //즉, xml에 정의된 Resource를 View객체로 반환해준다.
        //R.layout.item_list의 화면을 view로 반환해준다.
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);

        CustomViewHolder holder= new CustomViewHolder(view);

        return holder;
    }
    //3.리사이클러가 생성후 실제로 정보가 추가될때에 대한 생명주기
    //  arrayList 에 있는 정보들로 각 셋트 하나씩 만들어가는것이다. 그러므로 position이 주어지낟.
    @Override
    public void onBindViewHolder(@NonNull final BoardAdapter.CustomViewHolder holder, int position) {
        holder.ImageView_profile.setImageResource(arrayList.get(position).getImageView_profile());
        holder.TextView_content_name.setText(arrayList.get(position).getTextView_content_name());
        holder.TextView_tv_name.setText(arrayList.get(position).getTextView_tv_name());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String currentName=holder.TextView_tv_name.getText().toString();
                Toast.makeText(view.getContext(),currentName,Toast.LENGTH_SHORT).show();
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                removeThisPosition(holder.getAdapterPosition()); //tip! holder는 RecyclerView.viewholder를 상속 받았으므로 , getAdapterPosition()이라는 함수가잇다.
                //onClick이벤트 처리기 내에서 클릭된 Item의 위치를 가져올수 있는 방법을 ViewHolder가 제공(ViewHolder.getAdapterPosition)하고 있다

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        //listView의 갯수
        return (null!=arrayList? arrayList.size():0);
    }
    public void removeThisPosition(int position){
        try{
                arrayList.remove(position);  //정보가 들어있는 arrayList 에서 지워라.
                notifyItemRemoved(position); //새로고침
        }        catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }

    }

}
