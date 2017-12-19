package com.example.yangming.recyclerviewtest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final int MSG_WHAT_CHAT_UPDATE = 12;

    private RecyclerView mRv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    ArrayList<ChatItemBean> chats;

    ChatRecyclerAdapter adapter;

    private void initView() {
        mRv = findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

//        mRv.setItemAnimator(new FadeItemAnimator());

        chats = new ArrayList<>();
        chats.add(new ChatItemBean("aa", "11", "22", "33").setUserId(UUID.randomUUID().toString()));
        chats.add(new ChatItemBean("bb", "11", "22", "33").setUserId(UUID.randomUUID().toString()));
        chats.add(new ChatItemBean("cc", "11", "22", "33").setUserId(UUID.randomUUID().toString()));
        chats.add(new ChatItemBean("dd", "11", "22", "33").setUserId(UUID.randomUUID().toString()));
        chats.add(new ChatItemBean("ee", "11", "22", "33").setUserId(UUID.randomUUID().toString()));
        chats.add(new ChatItemBean("ff", "11", "22", "33").setUserId(UUID.randomUUID().toString()));
        chats.add(new ChatItemBean("gg", "11", "22", "33").setUserId(UUID.randomUUID().toString()));
        chats.add(new ChatItemBean("hh", "11", "22", "33").setUserId(UUID.randomUUID().toString()));
        chats.add(new ChatItemBean("ii", "11", "22", "33").setUserId(UUID.randomUUID().toString()));
        chats.add(new ChatItemBean("jj", "11", "22", "33").setUserId(UUID.randomUUID().toString()));
        chats.add(new ChatItemBean("kk", "11", "22", "33").setUserId(UUID.randomUUID().toString()));
        chats.add(new ChatItemBean("ll", "11", "22", "33").setUserId(UUID.randomUUID().toString()));
        chats.add(new ChatItemBean("mm", "11", "22", "33").setUserId(UUID.randomUUID().toString()));
        chats.add(new ChatItemBean("nn", "11", "22", "33").setUserId(UUID.randomUUID().toString()));

        adapter = new ChatRecyclerAdapter(chats, getApplicationContext());

        mRv.setAdapter(adapter);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatItemBean chatItemBean = chats.get(1);
                chatItemBean.setMsg(UUID.randomUUID().toString());
                chatItemBean.setOpen(!chatItemBean.isOpen());
                Log.e("test", "chatItemBean " + chatItemBean);
                updateChats();
            }
        });
    }

    private void updateChats() {
        //为了防止数据量过大，比对算法耗时，将算法放入新线程执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<ChatItemBean> oldChats = adapter.getChats();
                DiffUtil.DiffResult result = DiffUtil.calculateDiff(new ChatListCallback(oldChats, chats));
                Message message = updateChatsHandler.obtainMessage(MSG_WHAT_CHAT_UPDATE);
                message.obj = result;
                message.sendToTarget();
            }
        }).start();
    }

    private Handler updateChatsHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_WHAT_CHAT_UPDATE:
                    DiffUtil.DiffResult result = (DiffUtil.DiffResult) msg.obj;
                    //界面更新
                    result.dispatchUpdatesTo(adapter);
                    adapter.setChats(chats);
            }
        }
    };

}
