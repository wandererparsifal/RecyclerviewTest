package com.example.yangming.recyclerviewtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangming on 17-12-18.
 */
public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatRecyclerAdapter.ChatItemViewHolder> {

    private ArrayList<ChatItemBean> chats;
    private Context context;

    public ChatRecyclerAdapter(ArrayList<ChatItemBean> chats, Context context) {
        this.chats = new ArrayList<>();
        cloneChats(chats);
        this.context = context;
    }

    public ArrayList<ChatItemBean> getChats() {
        return chats;
    }

    public void setChats(ArrayList<ChatItemBean> chats) {
        cloneChats(chats);
    }

    public static class ChatItemViewHolder extends RecyclerView.ViewHolder {

        public Context context;

        public LinearLayout mLayoutButtons;

        public ImageView mRivAvatar;

        public TextView mTvNickname;

        public TextView mTvMsg;

        public TextView mTvTime;


        public ChatItemViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            this.mRivAvatar = itemView.findViewById(R.id.icon);
            this.mLayoutButtons = itemView.findViewById(R.id.layout_buttons);
            this.mTvNickname = itemView.findViewById(R.id.textView2);
            this.mTvTime = itemView.findViewById(R.id.textView3);
            this.mTvMsg = itemView.findViewById(R.id.textView);
        }
    }

    @Override
    public ChatItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ChatItemViewHolder(v, context);
    }

    @Override
    public void onBindViewHolder(ChatItemViewHolder holder, int position) {
        ChatItemBean chatItemBean = chats.get(position);
        holder.mTvMsg.setText(chatItemBean.getMsg());
        holder.mTvTime.setText(chatItemBean.getTime());
        holder.mTvNickname.setText(chatItemBean.getNickname());
        holder.mLayoutButtons.setVisibility(chatItemBean.isOpen() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onBindViewHolder(ChatItemViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle bundle = (Bundle) payloads.get(0);
            for (String key : bundle.keySet()) {
                Log.e("test", "key " + key);
                switch (key) {
                    case "avatarPath":
                        break;
                    case "nickname":
                        holder.mTvNickname.setText((CharSequence) bundle.get(key));
                        break;
                    case "time":
                        holder.mTvTime.setText((CharSequence) bundle.get(key));
                        break;
                    case "msg":
                        holder.mTvMsg.setText((CharSequence) bundle.get(key));
                        break;
                    case "open":
                        holder.mLayoutButtons.setVisibility((boolean) bundle.get(key) ? View.VISIBLE : View.GONE);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        if (chats == null)
            return 0;
        return chats.size();
    }

    private void cloneChats(ArrayList<ChatItemBean> newChats) {
        chats.clear();
        if (!newChats.isEmpty()) {
            for (int i = 0; i < newChats.size(); i++) {
                ChatItemBean bean = new ChatItemBean();
                bean.setUserId(newChats.get(i).getUserId());
                bean.setNickname(newChats.get(i).getNickname());
                bean.setAvatarPath(newChats.get(i).getAvatarPath());
                bean.setMsg(newChats.get(i).getMsg());
                bean.setTime(newChats.get(i).getTime());
                bean.setOpen(newChats.get(i).isOpen());
                chats.add(bean);
            }
        }
    }
}