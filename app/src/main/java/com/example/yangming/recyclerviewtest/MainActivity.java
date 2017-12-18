package com.example.yangming.recyclerviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    ArrayList<Item> strings;

    AAdapter adapter;

    private void initView() {
        mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mRv.setItemAnimator(new ScaleItemAnimator());

        strings = new ArrayList<>();
        strings.add(new Item("aa", true));
        strings.add(new Item("bb", false));
        strings.add(new Item("cc", false));
        strings.add(new Item("dd", false));
        strings.add(new Item("ee", false));
        strings.add(new Item("ff", false));
        strings.add(new Item("gg", false));
        strings.add(new Item("hh", false));
        strings.add(new Item("ii", false));
        strings.add(new Item("jj", false));
        strings.add(new Item("kk", false));
        strings.add(new Item("ll", false));
        strings.add(new Item("mm", false));
        strings.add(new Item("nn", false));

        adapter = new AAdapter(getApplicationContext());
        adapter.setData(strings);

        mRv.setAdapter(adapter);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Item i1 = strings.get(1);
                i1.text = "xx";
                i1.open = !i1.open;
                adapter.notifyItemChanged(1);
//                adapter.notifyItemChanged(1, "xxx");
            }
        });
    }
}
