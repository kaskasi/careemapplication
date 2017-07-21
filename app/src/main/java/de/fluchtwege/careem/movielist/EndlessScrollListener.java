package de.fluchtwege.careem.movielist;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


public class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private boolean isLoading = false;
    private LinearLayoutManager layoutManager;
    private OnLoadNextListener reloadListener;

    public EndlessScrollListener(LinearLayoutManager layoutManager, OnLoadNextListener reloadListener) {
        this.layoutManager = layoutManager;
        this.reloadListener = reloadListener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if(dy > 0) {
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();



            if (!isLoading) {
                Log.e("123", "visible "+ visibleItemCount +" total "+totalItemCount +" pastVis "+ pastVisiblesItems);
                if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                    Log.e("2332", "RELOAD");
                    isLoading = true;
                    reloadListener.loadNext();
                }
            }
        }
    }

    public interface OnLoadNextListener {
        void loadNext();
    }

    public void reloadDone() {
        isLoading = false;
    }

}
