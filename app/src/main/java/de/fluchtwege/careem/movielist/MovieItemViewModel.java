package de.fluchtwege.careem.movielist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import de.fluchtwege.careem.model.Movie;

public class MovieItemViewModel extends BaseObservable {

    private Movie movie;

    public MovieItemViewModel(Movie movie) {
        this.movie = movie;
    }

    @Bindable
    public String getTitle() {
        return movie.getTitle();
    }

    @Bindable
    public String getOverview() {
        return movie.getOverview();
    }

    public String getPosterUrl() {
        return movie.getPosterPath();
    }
}
