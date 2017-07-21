package de.fluchtwege.careem.di;

import com.google.gson.GsonBuilder;

import java.util.concurrent.Executors;

import dagger.Module;
import dagger.Provides;
import de.fluchtwege.careem.net.MovieController;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class CareemModule {

    private Retrofit retrofit;
    private Scheduler scheduler = Schedulers.io();

    @Provides
    Retrofit provideRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    .callbackExecutor(Executors.newCachedThreadPool())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(new OkHttpClient.Builder().build())
                    .baseUrl("https://api.themoviedb.org/3/")
                    .build();
        }
        return retrofit;
    }

    @Provides
    MovieController provideMovieController(Retrofit retrofit) {
        MovieController.MovieAPI movieAPI = retrofit.create(MovieController.MovieAPI.class);
        return new MovieController(movieAPI, scheduler);
    }
}
