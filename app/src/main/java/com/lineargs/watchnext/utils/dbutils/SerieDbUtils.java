package com.lineargs.watchnext.utils.dbutils;

import android.content.ContentValues;
import android.util.Log;

import com.lineargs.watchnext.data.DataContract;
import com.lineargs.watchnext.utils.MovieUtils;
import com.lineargs.watchnext.utils.retrofit.series.SeriesResult;
import com.lineargs.watchnext.utils.retrofit.series.seasondetails.Episode;
import com.lineargs.watchnext.utils.retrofit.series.seriesdetails.Season;
import com.lineargs.watchnext.utils.retrofit.series.seriesdetails.SeriesDetails;

import java.text.ParseException;
import java.util.List;

/**
 * Created by goranminov on 11/11/2017.
 * <p>
 * Utilities Helper class used for the Movies API response
 */

public class SerieDbUtils {

    /* Static variable for our poster and backdrop path*/
    private static final String IMAGE_SMALL_BASE = "http://image.tmdb.org/t/p/w370/";
    private static final String IMAGE_MEDIUM_BASE = "http://image.tmdb.org/t/p/w500/";
    private static final String IMAGE_ORIGINAL_BASE = "http://image.tmdb.org/t/p/original/";

    /**
     * Builds ContentValues[] used for our ContentResolver
     *
     * @param seriesResults List used to get the values from our API response
     * @return The {@link ContentValues}
     */
    public static ContentValues[] getPopularContentValues(List<SeriesResult> seriesResults) {
        int i = 0;
        ContentValues[] values = new ContentValues[seriesResults.size()];
        for (SeriesResult seriesResult : seriesResults) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(DataContract.PopularSerieEntry.COLUMN_SERIE_ID, seriesResult.getId());
            contentValues.put(DataContract.PopularSerieEntry.COLUMN_NAME, seriesResult.getName());
            contentValues.put(DataContract.PopularSerieEntry.COLUMN_OVERVIEW, seriesResult.getOverview());
            try {
                contentValues.put(DataContract.PopularSerieEntry.COLUMN_RELEASE_DATE, MovieUtils.getNormalizedReleaseDate(seriesResult.getFirstAirDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            contentValues.put(DataContract.PopularSerieEntry.COLUMN_VOTE_AVERAGE, MovieUtils.getNormalizedVoteAverage(String.valueOf(seriesResult.getVoteAverage())));
            contentValues.put(DataContract.PopularSerieEntry.COLUMN_POSTER_PATH, IMAGE_SMALL_BASE + seriesResult.getPosterPath());
            contentValues.put(DataContract.PopularSerieEntry.COLUMN_BACKDROP_PATH, IMAGE_SMALL_BASE + seriesResult.getBackdropPath());
            contentValues.put(DataContract.PopularSerieEntry.COLUMN_ORIGINAL_LANGUAGE, seriesResult.getOriginalLanguage());
            contentValues.put(DataContract.PopularSerieEntry.COLUMN_ORIGINAL_NAME, seriesResult.getOriginalName());

            values[i] = contentValues;
            i++;
        }

        return values;
    }

    /**
     * Builds ContentValues[] used for our ContentResolver
     *
     * @param seriesResults List used to get the values from our API response
     * @return The {@link ContentValues}
     */
    public static ContentValues[] getTopContentValues(List<SeriesResult> seriesResults) {
        int i = 0;
        ContentValues[] values = new ContentValues[seriesResults.size()];
        for (SeriesResult seriesResult : seriesResults) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(DataContract.TopRatedSerieEntry.COLUMN_SERIE_ID, seriesResult.getId());
            contentValues.put(DataContract.TopRatedSerieEntry.COLUMN_NAME, seriesResult.getName());
            contentValues.put(DataContract.TopRatedSerieEntry.COLUMN_OVERVIEW, seriesResult.getOverview());
            try {
                contentValues.put(DataContract.TopRatedSerieEntry.COLUMN_RELEASE_DATE, MovieUtils.getNormalizedReleaseDate(seriesResult.getFirstAirDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            contentValues.put(DataContract.TopRatedSerieEntry.COLUMN_VOTE_AVERAGE, MovieUtils.getNormalizedVoteAverage(String.valueOf(seriesResult.getVoteAverage())));
            contentValues.put(DataContract.TopRatedSerieEntry.COLUMN_POSTER_PATH, IMAGE_SMALL_BASE + seriesResult.getPosterPath());
            contentValues.put(DataContract.TopRatedSerieEntry.COLUMN_BACKDROP_PATH, IMAGE_SMALL_BASE + seriesResult.getBackdropPath());
            contentValues.put(DataContract.TopRatedSerieEntry.COLUMN_ORIGINAL_LANGUAGE, seriesResult.getOriginalLanguage());
            contentValues.put(DataContract.TopRatedSerieEntry.COLUMN_ORIGINAL_NAME, seriesResult.getOriginalName());

            values[i] = contentValues;
            i++;
        }

        return values;
    }

    /**
     * Builds ContentValues[] used for our ContentResolver
     *
     * @param seriesResults List used to get the values from our API response
     * @return The {@link ContentValues}
     */
    public static ContentValues[] getOnTheAirContentValues(List<SeriesResult> seriesResults) {
        int i = 0;
        ContentValues[] values = new ContentValues[seriesResults.size()];
        for (SeriesResult seriesResult : seriesResults) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(DataContract.OnTheAirSerieEntry.COLUMN_SERIE_ID, seriesResult.getId());
            contentValues.put(DataContract.OnTheAirSerieEntry.COLUMN_NAME, seriesResult.getName());
            contentValues.put(DataContract.OnTheAirSerieEntry.COLUMN_OVERVIEW, seriesResult.getOverview());
            try {
                contentValues.put(DataContract.OnTheAirSerieEntry.COLUMN_RELEASE_DATE, MovieUtils.getNormalizedReleaseDate(seriesResult.getFirstAirDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            contentValues.put(DataContract.OnTheAirSerieEntry.COLUMN_VOTE_AVERAGE, MovieUtils.getNormalizedVoteAverage(String.valueOf(seriesResult.getVoteAverage())));
            contentValues.put(DataContract.OnTheAirSerieEntry.COLUMN_POSTER_PATH, IMAGE_SMALL_BASE + seriesResult.getPosterPath());
            contentValues.put(DataContract.OnTheAirSerieEntry.COLUMN_BACKDROP_PATH, IMAGE_SMALL_BASE + seriesResult.getBackdropPath());
            contentValues.put(DataContract.OnTheAirSerieEntry.COLUMN_ORIGINAL_LANGUAGE, seriesResult.getOriginalLanguage());
            contentValues.put(DataContract.OnTheAirSerieEntry.COLUMN_ORIGINAL_NAME, seriesResult.getOriginalName());

            values[i] = contentValues;
            i++;
        }

        return values;
    }

    /**
     * Builds ContentValues[] used for our ContentResolver
     *
     * @param result Object used to get the values from our API response
     * @return The {@link ContentValues}
     */
    public static ContentValues[] getSyncTV(SeriesResult result) {

        ContentValues[] values = new ContentValues[1];
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataContract.Favorites.COLUMN_FAV_ID, result.getId());
        contentValues.put(DataContract.Favorites.COLUMN_TYPE, 1);
        contentValues.put(DataContract.Favorites.COLUMN_TITLE, result.getName());
        contentValues.put(DataContract.Favorites.COLUMN_OVERVIEW, result.getOverview());
        try {
            contentValues.put(DataContract.Favorites.COLUMN_RELEASE_DATE, MovieUtils.getNormalizedReleaseDate(result.getFirstAirDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        contentValues.put(DataContract.Favorites.COLUMN_VOTE_AVERAGE, MovieUtils.getNormalizedVoteAverage(String.valueOf(result.getVoteAverage())));
        contentValues.put(DataContract.Favorites.COLUMN_POSTER_PATH, IMAGE_SMALL_BASE + result.getPosterPath());
        contentValues.put(DataContract.Favorites.COLUMN_BACKDROP_PATH, IMAGE_SMALL_BASE + result.getBackdropPath());
        contentValues.put(DataContract.Favorites.COLUMN_ORIGINAL_LANGUAGE, result.getOriginalLanguage());
        contentValues.put(DataContract.Favorites.COLUMN_ORIGINAL_TITLE, result.getOriginalName());

        values[0] = contentValues;

        return values;
    }

    /**
     * Builds ContentValues[] used for our ContentResolver
     *
     * @param details Object used to get the values from our API response
     * @return The {@link ContentValues}
     */
    public static ContentValues[] getSeasons(SeriesDetails details, String serieId) {
        int i = 0;
        String name = details.getName();
        List<Season> seasons = details.getSeasons();
        ContentValues[] values = new ContentValues[seasons.size()];
        for (Season season : seasons) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(DataContract.Seasons.COLUMN_SHOW_NAME, name);
            contentValues.put(DataContract.Seasons.COLUMN_SERIE_ID, serieId);
            contentValues.put(DataContract.Seasons.COLUMN_SEASON_ID, season.getId());
            contentValues.put(DataContract.Seasons.COLUMN_EPISODE_COUNT, season.getEpisodeCount());
            if (season.getAirDate() != null) {
                try {
                    contentValues.put(DataContract.Seasons.COLUMN_RELEASE_DATE, MovieUtils.getNormalizedReleaseDate(season.getAirDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            contentValues.put(DataContract.Seasons.COLUMN_POSTER_PATH, IMAGE_SMALL_BASE + season.getPosterPath());
            contentValues.put(DataContract.Seasons.COLUMN_SEASON_NUMBER, season.getSeasonNumber());
            values[i] = contentValues;
            i++;
        }

        return values;
    }

    /**
     * Builds ContentValues[] used for our ContentResolver
     *
     * @param episodes List used to get the values from our API response
     * @return The {@link ContentValues}
     */
    public static ContentValues[] getEpisodes(List<Episode> episodes, String seasonId) {
        int i = 0;
        ContentValues[] values = new ContentValues[episodes.size()];
        for (Episode episode : episodes) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(DataContract.Episodes.COLUMN_EPISODE_ID, episode.getId());
            contentValues.put(DataContract.Episodes.COLUMN_SEASON_ID, seasonId);
            contentValues.put(DataContract.Episodes.COLUMN_SEASON_NUMBER, episode.getSeasonNumber());
            if (episode.getAirDate() != null) {
                try {
                    contentValues.put(DataContract.Episodes.COLUMN_RELEASE_DATE, MovieUtils.getNormalizedReleaseDate(episode.getAirDate()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            contentValues.put(DataContract.Episodes.COLUMN_STILL_PATH, IMAGE_SMALL_BASE + episode.getStillPath());
            contentValues.put(DataContract.Episodes.COLUMN_EPISODE_NUMBER, episode.getEpisodeNumber());
            contentValues.put(DataContract.Episodes.COLUMN_NAME, episode.getName());
            contentValues.put(DataContract.Episodes.COLUMN_OVERVIEW, episode.getOverview());
            contentValues.put(DataContract.Episodes.COLUMN_VOTE_AVERAGE, MovieUtils.getNormalizedVoteAverage(String.valueOf(episode.getVoteAverage())));
            contentValues.put(DataContract.Episodes.COLUMN_VOTE_COUNT, episode.getVoteCount());
            values[i] = contentValues;
            i++;
        }

        return values;
    }
}
