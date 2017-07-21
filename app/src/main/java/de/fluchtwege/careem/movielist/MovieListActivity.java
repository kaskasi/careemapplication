package de.fluchtwege.careem.movielist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import de.fluchtwege.careem.R;


public class MovieListActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new MovieListFragment(), MovieListFragment.TAG)
                .commit();
    }
}
