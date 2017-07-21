package de.fluchtwege.careem.movielist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fluchtwege.careem.databinding.ItemMovieBinding;
import de.fluchtwege.careem.moviedetail.MovieDetailActivity;

import static de.fluchtwege.careem.moviedetail.MovieDetailFragment.KEY_SELECTED_MOVIE;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private MovieListViewModel movieListViewModel;
    private Context context;

    public MovieAdapter(MovieListViewModel movieListViewModel) {
        this.movieListViewModel = movieListViewModel;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemMovieBinding binding = ItemMovieBinding.inflate(inflater);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        MovieItemViewModel itemViewModel = movieListViewModel.createItemViewModel(position);
        holder.getBinding().setViewModel(itemViewModel);
        holder.getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDetail(position);
            }
        });
    }

    private void openDetail(int position) {
        Intent openDetail = new Intent(context, MovieDetailActivity.class);
        openDetail.putExtra(KEY_SELECTED_MOVIE, movieListViewModel.getMovie(position));
        context.startActivity(openDetail);
    }

    @Override
    public int getItemCount() {
        return movieListViewModel.getNumberOfMovies();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemMovieBinding binding;

        public ViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ItemMovieBinding getBinding() {
            return binding;
        }
    }
}
