package com.example.mladen.sql_recycleviewer;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {
private Context mContext;
private Cursor mCursor;
    public GroceryAdapter(Context context, Cursor cursor) {
mContext=context;
mCursor = cursor;
    }

    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.grocery_item,parent,false );
        return  new GroceryViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull GroceryViewHolder holder, int postition) {
        if (!mCursor.moveToPosition(postition)){
            return;


        }
        String name = mCursor.getString(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_NAME));
        int amount = mCursor.getInt(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_AMOUNT));
        long id = mCursor.getLong(mCursor.getColumnIndex(GroceryContract.GroceryEntry._ID));
        holder.nameText.setText(name);
        holder.countText.setText(String.valueOf(amount));
        holder.itemView.setTag(id);


    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public  void swapCursor (Cursor newCursor)
    {


        if (mCursor != null){mCursor.close();}

        mCursor=newCursor;


        if (newCursor != null)
        {
            notifyDataSetChanged();
        }



    }

    public  class GroceryViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nameText;
        public  TextView countText;


        public GroceryViewHolder(@NonNull View itemView) {


            super(itemView);



            nameText=itemView.findViewById(R.id.textview_name_item);
            countText=itemView.findViewById(R.id.textview_amount_item);





        }
    }
}
