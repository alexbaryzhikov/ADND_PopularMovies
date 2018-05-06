package com.udacity.popularmovies.database;

import android.net.Uri;
import android.provider.BaseColumns;

public final class MovieContract {

  public static final String AUTHORITY = "com.udacity.popularmovies.provider";
  public static final String PATH_MOVIES = "movies";

  private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

  private MovieContract() {
    // Prevents instantiation
  }

  public static class MovieEntry implements BaseColumns {

    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

    public static final String TABLE_NAME = "movies";

    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_MOVIE_ID = "movie_id";
  }
}