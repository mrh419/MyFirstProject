package com.lineargs.watchnext.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lineargs.watchnext.R;
import com.lineargs.watchnext.utils.Constants;

/**
 * Created by goranminov on 27/11/2017.
 */

public class VideosTvActivity extends BaseTopActivity {

    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_tv);
        if (getIntent().hasExtra(Constants.TITLE)) {
            title = getIntent().getStringExtra(Constants.TITLE);
        }
        setupActionBar();
        setupNavDrawer();
        if (savedInstanceState == null) {
            VideosTvFragment fragment = new VideosTvFragment();
            fragment.setmUri(getIntent().getData());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.videos_tv_fragment, fragment)
                    .commit();
        }
    }

    @Override
    protected void setupActionBar() {
        super.setupActionBar();
        setTitle(title);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setSubtitle(getString(R.string.subtitle_activity_videos));
        }
    }

    @Override
    public void setDrawerIndicatorEnabled() {
        super.setDrawerIndicatorEnabled();
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_arrow_back_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
