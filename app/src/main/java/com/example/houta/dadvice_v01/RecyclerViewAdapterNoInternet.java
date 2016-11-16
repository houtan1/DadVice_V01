package com.example.houta.dadvice_v01;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.NativeExpressAdView;

import java.util.List;

/**
 * The {@link RecyclerViewAdapterNoInternet} class.
 * <p>The adapter provides access to the items in the {@link DadviceItemViewHolder}
 * or the {@link NativeExpressAdViewHolder}.</p>
 */
class RecyclerViewAdapterNoInternet extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // A menu dadvice view type.
    private static final int DADVICE_VIEW_TYPE = 0;

    // An Activity's Context.
    private final Context mContext;

    // The list of Native Express ads and dadvice items.
    private final List<Object> mRecyclerViewItems;

    /**
     * For this example app, the recyclerViewItems list contains only
     * {@link //Dadvice} and {@link //NativeExpressAdView} types.
     */
    public RecyclerViewAdapterNoInternet(Context context, List<Object> recyclerViewItems) {
        this.mContext = context;
        this.mRecyclerViewItems = recyclerViewItems;
    }

    /**
     * The {@link DadviceItemViewHolder} class.
     * Provides a reference to each view in the menu item view.
     */
    public class DadviceItemViewHolder extends RecyclerView.ViewHolder {
        private TextView dadvice_text;

        DadviceItemViewHolder(View view) {
            super(view);
            dadvice_text = (TextView) view.findViewById(R.id.dadvice_textview);//TODO check this matches xml properly in card_view.xml
        }
    }

    /**
     * The {@link NativeExpressAdViewHolder} class.
     */
    public class NativeExpressAdViewHolder extends RecyclerView.ViewHolder {

        NativeExpressAdViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public int getItemCount() {
        return mRecyclerViewItems.size();
    }

    /**
     * Determines the view type for the given position.
     */
    @Override
    public int getItemViewType(int position) {
        int return_value;
            return_value = DADVICE_VIEW_TYPE;
        return return_value;
    }

    /**
     * Creates a new view for a menu item view or a Native Express ad view
     * based on the viewType. This method is invoked by the layout manager.
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View DadviceItemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.card_view, viewGroup, false);
        return new DadviceItemViewHolder(DadviceItemLayoutView);
    }

    /**
     *  Replaces the content in the views that make up the menu item view and the
     *  Native Express ad view. This method is invoked by the layout manager.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        DadviceItemViewHolder DadviceItemHolder = (DadviceItemViewHolder) holder;
        String dadvice_item = mRecyclerViewItems.get(position).toString();

        DadviceItemHolder.dadvice_text.setText(dadvice_item);
    }

}
