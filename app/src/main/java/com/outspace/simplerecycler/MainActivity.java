package com.outspace.simplerecycler;

import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public String[] initialStrings = new String[] {"zeroth",
            "first", "second", "third", "fourth", "fifth",
            "sixth", "seventh", "eighth", "ninth"
    };
    public ArrayList<DataItem> data = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<>();
        for(String name: initialStrings) {
            DataItem item = new DataItem();
            item.name = name;
            item.showActions = false;
            data.add(item);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        MyAdapter adapter = new MyAdapter(data);
        recycler.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback itemTochHelperSimpleCallback = createTouchCallback();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTochHelperSimpleCallback);
        itemTouchHelper.attachToRecyclerView(recycler);
    }

    ItemTouchHelper.SimpleCallback createTouchCallback() {
        int dragDirs = 0; // no drag directions. check possible values in documentation
        int swipeDirs = ItemTouchHelper.LEFT; // other values can be OR-ed in

        ItemTouchHelper.SimpleCallback callback =
                new ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                        MyAdapter.MyViewHolder myVH = (MyAdapter.MyViewHolder) viewHolder;
                        myVH.notifyListChange();
                    }

                    @Override
                    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                            float dX, float dY, int actionState, boolean isCurrentlyActive) {
                        super.onChildDraw(canvas, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

                        if( actionState == ItemTouchHelper.ACTION_STATE_SWIPE ) {
                            Paint paint = new Paint();
                            paint.setColor(0xFFE040FB);
                            View itemView = viewHolder.itemView;
                            RectF bkgrn = new RectF((float) itemView.getRight() + dX,
                                    (float) itemView.getTop(), (float) itemView.getRight(),
                                    (float) itemView.getBottom());
                            canvas.drawRect( bkgrn, paint);
                            // we could insert text or an image in here.
                        }
                    }
                };

        return callback;
    }
}
