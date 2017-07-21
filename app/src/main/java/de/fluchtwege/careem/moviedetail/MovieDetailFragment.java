package de.fluchtwege.careem.moviedetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.Serializable;

import javax.inject.Inject;

import de.fluchtwege.careem.CareemApplication;
import de.fluchtwege.careem.databinding.FragmentMovieDetailBinding;
import de.fluchtwege.careem.model.Movie;

public class MovieDetailFragment extends Fragment {

    public static final String TAG = "detail";
    public static final String KEY_SELECTED_MOVIE = "selected_movie";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMovieDetailBinding binding = FragmentMovieDetailBinding.inflate(inflater);
        Movie selectedMovie = (Movie) getActivity().getIntent().getSerializableExtra(KEY_SELECTED_MOVIE);
        binding.setViewModel(new MovieDetailViewModel(selectedMovie));
        return binding.getRoot();
    }
}
