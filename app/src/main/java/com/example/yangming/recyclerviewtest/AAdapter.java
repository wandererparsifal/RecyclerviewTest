package com.example.yangming.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangming on 17-12-18.
 */
public class AAdapter extends RecyclerView.Adapter<AAdapter.ListHolder> {

    private static final int TYPE_OPENED = 1;

    private static final int TYPE_CLOSED = 2;

    private Context mContext = null;

    private ArrayList<Item> mList = null;

    public AAdapter(Context context) {
        this.mContext = context;
        this.mList = new ArrayList<>();
    }

    public void setData(ArrayList<Item> list) {
        this.mList = list;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        holder.name.setText(mList.get(position).text);
        switch (getItemViewType(position)) {
            case TYPE_OPENED:
                holder.layout.setVisibility(View.VISIBLE);
                break;
            case TYPE_CLOSED:
                holder.layout.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

//    @Override
//    public void onBindViewHolder(ListHolder holder, int position, List<Object> payloads) {
//        Log.e("onBindViewHolder","onBindViewHolder " + payloads);
//        if (payloads.isEmpty()) {
//            onBindViewHolder(holder, position);
//        } else {
//            Log.e("onBindViewHolder","xxxxxxx");
//            switch (getItemViewType(position)) {
//                case TYPE_OPENED:
//                    holder.layout.setVisibility(View.VISIBLE);
//                    break;
//                case TYPE_CLOSED:
//                    holder.layout.setVisibility(View.GONE);
//                    break;
//                default:
//                    break;
//            }
//        }
//    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).open ? TYPE_OPENED : TYPE_CLOSED;
    }

    class ListHolder extends RecyclerView.ViewHolder {

        public TextView name;

        public LinearLayout layout;

        ListHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
            layout = itemView.findViewById(R.id.layout_buttons);
        }
    }
}