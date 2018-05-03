package com.udacity.popularmovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.R;
import com.udacity.popularmovies.api.HttpUtils;
import com.udacity.popularmovies.model.discover.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

  private List<Result> moviesData;
  private MoviesOnClickHandler clickHandler;
  private ViewGroup.LayoutParams itemLayoutParams;

  /**
   * MoviesAdapter constructor
   *
   * @param clickHandler The on-click handler for this adapter.
   * @param itemHeight   The height of the items inflated by the adapter.
   */
  public MoviesAdapter(MoviesOnClickHandler clickHandler, int itemHeight) {
    this.clickHandler = clickHandler;
    itemLayoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight);
  }

  @NonNull
  @Override
  public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(context);
    View view = inflater.inflate(R.layout.movie_grid_item, parent, false);
    view.findViewById(R.id.iv_poster).setLayoutParams(itemLayoutParams);  // set row height
    return new MovieViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
    ImageView posterView = holder.getPosterView();
    String posterPath = moviesData.get(position).getPosterPath();
    String posterUrl = HttpUtils.posterUrl(posterPath);
    Picasso.get().load(posterUrl).into(posterView);
  }

  @Override
  public int getItemCount() {
    if (moviesData == null) {
      return 0;
    }
    return moviesData.size();
  }

  public void submitList(List<Result> moviesData) {
    this.moviesData = moviesData;
    notifyDataSetChanged();
  }

  /**
   * The interface that receives onClick messages
   */
  public interface MoviesOnClickHandler {
    void onClick(Result movie);
  }

  /**
   * Cache of the movie grid items
   */
  class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.iv_poster)
    ImageView posterView;

    MovieViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      clickHandler.onClick(moviesData.get(getAdapterPosition()));
    }

    ImageView getPosterView() {
      return posterView;
    }
  }
}
