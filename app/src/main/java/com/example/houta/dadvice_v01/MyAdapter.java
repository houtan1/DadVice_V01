package com.example.houta.dadvice_v01;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by houta on 10/12/2016.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mDataset;

    // ref: https://developer.android.com/training/material/lists-cards.html
    // provide a reference to the views for each data item
    // complex data items may need more than one view per item
    // you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v){
            super(v);
            mTextView = v;
        }
    }

    // provide a suitable constructor (depends on kind of dataset)
    public MyAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    // create new views (invoked by layout manager
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main, parent, false);
        // set the view's size, margins, paddings, and layout parameters here

        //ERROR HERE
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // replace the contents of a view (invoked by layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // get element from your dataset at this position
        // replace the contents of the view with that element
        holder.mTextView.setText(mDataset[position]);
    }

    // return the size of your dataset (invoked by your laout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
