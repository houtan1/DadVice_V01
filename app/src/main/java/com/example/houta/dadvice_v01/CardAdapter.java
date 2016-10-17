package com.example.houta.dadvice_v01;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by houta on 10/15/2016.
 */
// template: https://developer.android.com/training/material/lists-cards.html
// guidance: http://icetea09.com/blog/2014/12/19/android-cardview-and-recyclerview-in-material-design/
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private ArrayList<String> mDataset;

    // provide a reference to the views for each data item
    // complex data items may need more than one view per item
    // you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        // viewHolder constructor
        // ref: http://stackoverflow.com/questions/27450598/android-widget-textview-cannot-be-applied-to-android-view-view
        // ref: http://stackoverflow.com/questions/28407768/android-widget-linearlayout-cannot-be-cast-to-android-widget-textview-in-recycle
        public ViewHolder(View v){
            super(v);
            mTextView = (TextView) v.findViewById(R.id.textView);
        }
    }

    // provide a suitable constructor (depends on kind of dataset)
    public CardAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    // create new views (invoked by layout manager)
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view , parent, false);
        // set the view's size, margins, padding, and layout parameters here

        CardAdapter.ViewHolder vh = new CardAdapter.ViewHolder(v);
        return vh;
    }

    // replace the contents of a view (invoked by layout manager)
    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position) {
        // get element from your dataset at this position
        // replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position));
    }

    // return the size of your dataset (invoked by your laout manager)
    @Override
    public int getItemCount(){
        return mDataset.size();
    }
}
