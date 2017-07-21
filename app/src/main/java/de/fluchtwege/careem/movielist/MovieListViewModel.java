package de.fluchtwege.careem.movielist;

import android.databinding.BaseObservable;
import android.databinding.Observable;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.fluchtwege.careem.model.Movie;
import de.fluchtwege.careem.model.MoviePage;
import de.fluchtwege.careem.net.MovieController;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MovieListViewModel extends BaseObservable {


    private static String API_KEY = "02c5c02f833c6e54a07242842077ac60";
    private static String LANGUAGE = Locale.getDefault().toString();

    private int currentPage = 1;
    private MovieController movieController;
    private List<Movie> movies = new ArrayList<>();

    public MovieListViewModel(MovieController movieController) {
        this.movieController = movieController;
    }

    public Disposable loadMovies() {
        return movieController.getPopularMovies(API_KEY, LANGUAGE, currentPage).subscribe(new Consumer<MoviePage>() {
            @Override
            public void accept(@NonNull MoviePage moviePage) throws Exception {
                movies = moviePage.getMovies();
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        }, new Consumer<Subscription>() {
            @Override
            public void accept(@NonNull Subscription subscription) throws Exception {

            }
        });
    }


    public int getNumberOfMovies() {
        return movies.size();
    }

    public MovieItemViewModel createItemViewModel(int position) {
        return new MovieItemViewModel(movies.get(position));
    }

    public void nextPage() {
        currentPage++;
    }

    public Movie getMovie(int position) {
        return movies.get(position);
    }
}
