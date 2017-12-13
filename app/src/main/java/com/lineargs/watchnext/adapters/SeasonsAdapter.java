package com.lineargs.watchnext.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lineargs.watchnext.R;
import com.lineargs.watchnext.data.SeasonsQuery;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by goranminov on 27/11/2017.
 * <p>
 * See {@link MainAdapter}
 */

public class SeasonsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnClickListener callback;
    private Context context;
    private Cursor cursor;

    public SeasonsAdapter(@NonNull Context context, OnClickListener listener) {
        this.context = context;
        callback = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.item_seasons, parent, false);
        return new SeasonsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SeasonsViewHolder viewHolder = (SeasonsViewHolder) holder;
        viewHolder.bindViews(context, position);
    }

    @Override
    public int getItemCount() {
        if (cursor == null) {
            return 0;
        } else {
            return cursor.getCount();
        }
    }

    public void swapCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    public interface OnClickListener {
        void OnClick(String seasonId, String seasonNumber, String serieId, String seasonTitle, String episodes);
    }

    class SeasonsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.title)
        AppCompatTextView title;
        @BindView(R.id.seasons_date)
        AppCompatTextView date;
        @BindView(R.id.seasons_episodes)
        AppCompatTextView episodes;

        SeasonsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            if (callback != null) view.setOnClickListener(this);
        }

        void bindViews(Context context, int position) {
            Resources resources = context.getResources();
            cursor.moveToPosition(position);
            String seasonTitle = resources.getString(R.string.season, cursor.getInt(SeasonsQuery.SEASON_NUMBER));
            title.setText(seasonTitle);
            date.setText(cursor.getString(SeasonsQuery.RELEASE_DATE));
            String episodesCount = resources.getQuantityString(R.plurals.numberOfEpisodes, cursor.getInt(SeasonsQuery.EPISODE_COUNT), cursor.getInt(SeasonsQuery.EPISODE_COUNT));
            episodes.setText(episodesCount);
        }

        @Override
        public void onClick(View view) {
            Resources resources = context.getResources();
            cursor.moveToPosition(getAdapterPosition());
            String seasonId = cursor.getString(SeasonsQuery.SEASON_ID);
            String seasonTitle = resources.getString(R.string.season, cursor.getInt(SeasonsQuery.SEASON_NUMBER));
            String episodes = resources.getQuantityString(R.plurals.numberOfEpisodes, cursor.getInt(SeasonsQuery.EPISODE_COUNT), cursor.getInt(SeasonsQuery.EPISODE_COUNT));
            String serieId = cursor.getString(SeasonsQuery.SERIE_ID);
            String number = cursor.getString(SeasonsQuery.SEASON_NUMBER);
            callback.OnClick(seasonId, number, serieId, seasonTitle, episodes);
        }
    }
}

