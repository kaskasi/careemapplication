package de.fluchtwege.careem.net;

import java.util.List;

import de.fluchtwege.careem.model.Movie;
import de.fluchtwege.careem.model.MoviePage;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MovieController {


    private final MovieAPI movieAPI;
    private final Scheduler scheduler;

    public MovieController(MovieAPI movieAPI, Scheduler scheduler) {
        this.movieAPI = movieAPI;
        this.scheduler = scheduler;
    }

    public Flowable<MoviePage> getPopularMovies(String apiKey, String language, int page) {
        return movieAPI.getMovies(apiKey, language, page).subscribeOn(scheduler);
    }

    public interface MovieAPI {

        @GET("movie/popular")
        Flowable<MoviePage> getMovies(
                @Query("api_key") String apiKey,
                @Query("language") String language,
                @Query("page") int page);

    }

}
