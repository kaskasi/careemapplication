package de.fluchtwege.careem.movielist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import de.fluchtwege.careem.CareemApplication;
import de.fluchtwege.careem.databinding.FragmentMoviesBinding;
import de.fluchtwege.careem.net.MovieController;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MovieListFragment extends Fragment implements EndlessScrollListener.OnLoadNextListener {

    public static final String TAG = "movies";

    @Inject
    MovieController movieController;

    private MovieListViewModel viewModel;
    private MovieAdapter movieAdapter;
    private EndlessScrollListener endlessScrollListener;
    private CompositeDisposable disposables;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CareemApplication.getInjector().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentMoviesBinding binding = FragmentMoviesBinding.inflate(inflater);
        viewModel = new MovieListViewModel(movieController);
        binding.setViewModel(viewModel);
        setupRecyclerView(binding.movies);

        loadMovies();
        return binding.getRoot();
    }

    private void setupRecyclerView(RecyclerView movies) {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        movies.setLayoutManager(layoutManager);
        movies.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));
        movieAdapter = new MovieAdapter(viewModel);
        movies.setAdapter(movieAdapter);
        endlessScrollListener = new EndlessScrollListener(layoutManager, this);
        movies.setOnScrollListener(endlessScrollListener);
    }

    private void loadMovies() {
        disposables.add(viewModel.loadMovies());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposables.clear();
    }

    @Override
    public void loadNext() {
        viewModel.nextPage();
        loadMovies();
    }
}
