package de.fluchtwege.careem.moviedetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import de.fluchtwege.careem.model.Movie;

public class MovieDetailViewModel extends BaseObservable {

    private Movie movie;

    public MovieDetailViewModel(Movie movie) {
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

    //this should be formatted threeTen LocalDate
    @Bindable
    public String getReleaseDate() {
        return movie.getReleaseDate();
    }


}
