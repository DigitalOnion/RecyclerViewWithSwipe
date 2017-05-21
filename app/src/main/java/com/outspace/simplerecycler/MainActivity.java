package com.outspace.simplerecycler;

import android.content.ClipData;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
        MyAdapter adapter = new MyAdapter(data);
        recycler.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemTochHelperSimpleCallback = getItemTochHelperSimpleCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTochHelperSimpleCallback);
        itemTouchHelper.attachToRecyclerView(recycler);
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

    ItemTouchHelper.SimpleCallback getItemTochHelperSimpleCallback(MyAdapter adapter) {

        // check on the swipeDirections, they could be ored - up like: ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
        ItemTouchHelper.SimpleCallback callback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();

                        if (direction == ItemTouchHelper.LEFT){
                            Toast.makeText(getApplicationContext(), "Left", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Right", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                    }
                };

        return callback;
    }


}
