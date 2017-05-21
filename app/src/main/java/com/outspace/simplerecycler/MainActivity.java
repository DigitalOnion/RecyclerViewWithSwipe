package com.outspace.simplerecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DataItem[] data = null;
    String[] initialStrings = new String[] {"Sofi", "Aldo", "Elvi", "Luis"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new DataItem[initialStrings.length];
        for(int i = 0; i < initialStrings.length; i++) {
            data[i] = new DataItem();
            data[i].name = initialStrings[i];
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new MyAdapter(data));
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
        DataItem[] data = null;

        public MyAdapter(DataItem[] data) {
            super();
            this.data = data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.txtName.setText(data[position].name);
        }

        @Override
        public int getItemCount() {
            return data.length;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txtName;
            public MyViewHolder(View itemView) {
                super(itemView);
                txtName = (TextView) itemView.findViewById(R.id.txtName);
            }
        }
    }
}
