package com.lineargs.watchnext.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lineargs.watchnext.R;
import com.lineargs.watchnext.data.CreditsQuery;
import com.lineargs.watchnext.utils.ServiceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by goranminov on 21/12/2017.
 */

public class CreditsCrewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnClick callBack;
    private Context context;
    private Cursor cursor;

    public CreditsCrewAdapter(@NonNull Context context, @NonNull OnClick listener) {
        this.context = context;
        this.callBack = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.item_credits_crew, parent, false);
        return new CreditsCrewAdapter.CreditsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CreditsViewHolder viewHolder = (CreditsViewHolder) holder;
        viewHolder.bindViews(position);
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

    public interface OnClick {
        void onPersonClick(String id, String name);
    }

    class CreditsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.crew_name)
        AppCompatTextView name;
        @BindView(R.id.crew_job)
        AppCompatTextView job;
        @BindView(R.id.profile_photo)
        ImageView photo;

        CreditsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        void bindViews(int position) {
            cursor.moveToPosition(position);
            name.setText(cursor.getString(CreditsQuery.NAME));
            job.setText(cursor.getString(CreditsQuery.JOB));
            ServiceUtils.loadPicasso(photo.getContext(), cursor.getString(CreditsQuery.PROFILE_PATH))
                    .resizeDimen(R.dimen.movie_poster_width_default, R.dimen.movie_poster_height_default)
                    .centerCrop()
                    .error(R.drawable.icon_person_grey)
                    .into(photo);
        }

        @Override
        public void onClick(View view) {
            cursor.moveToPosition(getAdapterPosition());
            String id = cursor.getString(CreditsQuery.PERSON_ID);
            String name = cursor.getString(CreditsQuery.NAME);
            callBack.onPersonClick(id, name);
        }
    }
}

