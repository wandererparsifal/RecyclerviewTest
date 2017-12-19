package com.example.yangming.recyclerviewtest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by yangming on 17-12-19.
 */
public class ChatListCallback extends DiffUtil.Callback {

    private ArrayList<ChatItemBean> old_chats;

    private ArrayList<ChatItemBean> new_chats;

    public ChatListCallback(ArrayList<ChatItemBean> old_chats, ArrayList<ChatItemBean> new_chats) {
        this.old_chats = old_chats;
        this.new_chats = new_chats;
    }

    @Override
    public int getOldListSize() {
        return old_chats.size();
    }

    @Override
    public int getNewListSize() {
        return new_chats.size();
    }

    /**
     * 判断此id的用户消息是否已存在
     *
     * @param oldItemPosition
     * @param newItemPosition
     * @return
     */
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        String newId = old_chats.get(oldItemPosition).getUserId();
        String oldId = new_chats.get(newItemPosition).getUserId();
        if (oldId == null || newId == null) {
            Log.e("test", "areItemsTheSame false");
            return false;
        } else if (oldId.trim().equals("") || newId.trim().equals("") || (!oldId.equals(newId))) {
            Log.e("test", "areItemsTheSame false");
            return false;
        } else {
            Log.e("test", "areItemsTheSame true");
            return true;
        }
    }

    /**
     * 若此id的用户消息已存在，则判断内容是否一致
     *
     * @param oldItemPosition
     * @param newItemPosition
     * @return
     */
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        String newAvatarPath = new_chats.get(newItemPosition).getAvatarPath();
        String oldAvatarPath = old_chats.get(oldItemPosition).getAvatarPath();
        String oldNickname = old_chats.get(oldItemPosition).getNickname();
        String newNickname = new_chats.get(newItemPosition).getNickname();
        String newTime = new_chats.get(newItemPosition).getTime();
        String oldTime = old_chats.get(oldItemPosition).getTime();
        String newMsg = new_chats.get(newItemPosition).getMsg();
        String oldMsg = old_chats.get(oldItemPosition).getMsg();

        if ((newAvatarPath == null && oldAvatarPath != null) || (oldAvatarPath == null && newAvatarPath != null) || (oldAvatarPath != null && !oldAvatarPath.equals(newAvatarPath)) ||
                (newNickname == null && oldNickname != null) || (oldNickname == null && newNickname != null) || (oldNickname != null && !oldNickname.equals(newNickname)) ||
                (newTime == null && oldTime != null) || (oldTime == null && newTime != null) || (oldTime != null && !oldTime.equals(newTime)) ||
                (newMsg == null && oldMsg != null) || (oldMsg == null && newMsg != null) || (oldMsg != null && !oldMsg.equals(newMsg))) {
            Log.e("test", "areContentsTheSame false");
            return false;
        }
        Log.e("test", "areContentsTheSame true");
        return true;
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        String newAvatarPath = new_chats.get(newItemPosition).getAvatarPath();
        String oldAvatarPath = old_chats.get(oldItemPosition).getAvatarPath();
        String oldNickname = old_chats.get(oldItemPosition).getNickname();
        String newNickname = new_chats.get(newItemPosition).getNickname();
        String newTime = new_chats.get(newItemPosition).getTime();
        String oldTime = old_chats.get(oldItemPosition).getTime();
        String newMsg = new_chats.get(newItemPosition).getMsg();
        String oldMsg = old_chats.get(oldItemPosition).getMsg();

        Bundle bundle = new Bundle();

        Log.e("test", "oldMsg " + oldMsg);
        Log.e("test", "newMsg " + newMsg);

        if ((newAvatarPath == null && oldAvatarPath != null) || (newAvatarPath != null && oldAvatarPath == null) || (oldAvatarPath != null && !oldAvatarPath.equals(newAvatarPath)))
            bundle.putString("avatarPath", newAvatarPath);
        if ((newNickname == null && oldNickname != null) || (oldNickname == null && newNickname != null) || (oldNickname != null && !oldNickname.equals(newNickname)))
            bundle.putString("nickname", newNickname);
        if ((newTime == null && oldTime != null) || (oldTime == null && newTime != null) || (oldTime != null && !oldTime.equals(newTime)))
            bundle.putString("time", newTime);
        if ((newMsg == null && oldMsg != null) || (oldMsg == null && newMsg != null) || (oldMsg != null && !oldMsg.equals(newMsg)))
            bundle.putString("msg", newMsg);
        if (bundle.size() == 0) {
            return null;
        }

        return bundle;
    }
}