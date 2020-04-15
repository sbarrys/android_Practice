package com.kty.first;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class BoardActivity extends AppCompatActivity {
    private ArrayList<BoardData> arrayList;
    private BoardAdapter boardAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Button button_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        //나중에는 데이터베이스에 있는 것을 가지고 오면된다.
        arrayList= new ArrayList<>();


        recyclerView =(RecyclerView)findViewById(R.id.RecyclerView_board);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        boardAdapter= new BoardAdapter(arrayList);
        recyclerView.setAdapter(boardAdapter);

        button_add= (Button)findViewById(R.id.button_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //보드데이터 추가해주기
                BoardData boardData= new BoardData(R.drawable.ic_launcher_background,"제목1","리사이클러뷰");
                //데이터리스트에 추가
                arrayList.add(boardData);
                boardAdapter.notifyDataSetChanged();

            }
        });
    }
}
