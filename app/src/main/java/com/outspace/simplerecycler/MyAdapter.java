package com.outspace.simplerecycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 5/21/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<DataItem> data = null;

    public MyAdapter(ArrayList<DataItem> data) {
        super();
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int resources[] =
                {R.layout.list_item, R.layout.list_item_alternative};
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(resources[viewType], parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAdapter.this.notifyListChange();
            }
        });

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txtName.setText(data.get(position).name);
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).showActions ? 1 : 0;
    }

    public void notifyListChange(int position) {
        for(DataItem item: data)
            item.showActions = false;
        data.get(position).showActions = true;
        notifyDataSetChanged();
    }

    public void notifyListChange() {
        for(DataItem item: data)
            item.showActions = false;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public int position;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
        }

        public void notifyListChange() {
            MyAdapter.this.notifyListChange(position);
        }
    }

}
