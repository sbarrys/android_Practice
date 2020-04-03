package com.kty.first;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.CharacterData;

import java.util.ArrayList;
import java.util.List;

public class chatActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView; //리사이클러뷰
    public  RecyclerView.Adapter mAdapter;//어뎁터
    private RecyclerView.LayoutManager mLayoutManager;//레이아웃매니져
    private List<ChatData> chatDataList;
    private String nick ="nick2";

    private EditText EditText_chat;
    private Button Button_send;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        EditText_chat=findViewById(R.id.EditText_chat);
        Button_send = findViewById(R.id.Button_send);
        ///////////////버튼 클릭시 DB에 데이터전송
        Button_send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String msg= EditText_chat.getText().toString();
                if(msg!=null) {
                    ChatData chat = new ChatData();
                    chat.setMessage(msg);
                    chat.setNickname(nick);
                    myRef.push().setValue(chat);
                }
            }
        });



//////////리사이클러뷰 셋팅///////////////
        mRecyclerView=findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager= new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        chatDataList= new ArrayList<>();
        mAdapter= new ChatAdapter(chatDataList,chatActivity.this,nick);
        mRecyclerView.setAdapter(mAdapter);

////////////////데이터베이스에 있는 레퍼런스가 수정되면, onClickListner타기

        FirebaseDatabase database= FirebaseDatabase.getInstance();
        myRef=database.getReference("message");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("CHATCHAT", dataSnapshot.getValue().toString());
                //스탭샷추가시 여기타고 자동으로 VO에 탑재되어서들어온다.
                ChatData chat = dataSnapshot.getValue(ChatData.class);
                ((ChatAdapter) mAdapter).addChat(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
