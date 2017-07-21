package de.fluchtwege.careem.di;

import dagger.Component;
import de.fluchtwege.careem.moviedetail.MovieDetailFragment;
import de.fluchtwege.careem.movielist.MovieListFragment;

@Component(modules = CareemModule.class)
public interface CareemComponent {

    void inject(MovieListFragment movieListFragment);

    void inject(MovieDetailFragment movieDetailFragment);
}
