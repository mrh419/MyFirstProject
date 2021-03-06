package com.lineargs.watchnext.adapters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lineargs.watchnext.R;
import com.lineargs.watchnext.data.DataContract;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by goranminov on 16/11/2017.
 * <p>
 * Abstract RecyclerView class
 * We are extending from it in most of the Movies / Series adapters
 */

public abstract class BaseTabbedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    OnItemClickListener callback;
    private Context context;

    /* Empty constructor*/
    BaseTabbedAdapter() {
    }

    /**
     * Constructor for passing the Context and ClickListener from the child Adapter
     * Creates a BaseTabbedAdapter
     *
     * @param context  The Context to be passed
     * @param listener The ClickListener to be passed
     */
    BaseTabbedAdapter(@NonNull Context context, OnItemClickListener listener) {
        this.context = context;
        this.callback = listener;
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param parent   The ViewGroup that these ViewHolders are contained within.
     * @param viewType If the RecyclerView has more than one type of item.
     * @return A new TabbedViewHolder that holds the View for each item
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.item_layout_tabbed, parent, false);
        return new TabbedViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position.
     * We make a call to our abstract method bindViews for easier implementation
     *
     * @param holder   The ViewHolder which should be updated to represent the
     *                 contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TabbedViewHolder tabbedViewHolder = (TabbedViewHolder) holder;
        bindViews(tabbedViewHolder, context, position);
    }

    /* Abstract method so we can implement in the child Adapter*/
    protected abstract void bindViews(TabbedViewHolder holder, Context context, int position);

    /* Abstract method so we can implement in the child Adapter*/
    protected abstract void onViewClick(View view, int position);

    /*
     * Used in the child Adapters so we can check if the data contains
     * in the favorites table before we display it
     */
    protected boolean isFavorite(Context context, long id) {
        Uri uri = DataContract.Favorites.buildFavoritesUriWithId(id);
        Cursor cursor = context.getContentResolver().query(uri,
                null,
                null,
                null,
                null);
        if (cursor == null) {
            return false;
        }
        boolean favorite = cursor.getCount() > 0;
        cursor.close();
        return favorite;
    }

    /**
     * Simple method that creates the drawable resource
     *
     * @return The drawable to be returned
     */
    VectorDrawableCompat starImage() {
        return VectorDrawableCompat.create
                (context.getResources(), R.drawable.icon_star_black, context.getTheme());
    }

    /**
     * Simple method that creates the drawable resource
     *
     * @return The drawable to be returned
     */
    VectorDrawableCompat starImageBorder() {
        return VectorDrawableCompat.create
                (context.getResources(), R.drawable.icon_star_border_black, context.getTheme());
    }

    /* Our OnClickListener*/
    public interface OnItemClickListener {
        void onItemSelected(Uri uri);
    }

    /**
     * Cache of the children views
     */
    class TabbedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.poster)
        ImageView poster;
        @BindView(R.id.star_image)
        ImageView star;
        @BindView(R.id.title)
        AppCompatTextView title;

        TabbedViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            if (callback != null) view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onViewClick(v, getAdapterPosition());
        }
    }
}
