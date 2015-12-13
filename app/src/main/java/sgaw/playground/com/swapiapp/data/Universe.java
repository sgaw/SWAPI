package sgaw.playground.com.swapiapp.data;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import sgaw.playground.com.swapiapp.util.CircularArrayWrapper;
import sgaw.playground.com.swapiapp.util.ICircularArray;

/**
 * Created by shirleygaw on 09/12/15.
 */
public class Universe {
    private static final String ASSET = "CachedPeople.json";
    private static final String TAG = "Universe";

    @VisibleForTesting
    static Universe sSelf = null;
    private ICircularArray<MovieCharacter> mCharacters = null;
    // Indicates which page to fetch if we want more data from the SWAPI service.
    private int nextPage = 2;

    public interface MovieCharactersCallback {
        public void onUpdated(List<MovieCharacter> characters);
    }

    public Universe(ICircularArray<MovieCharacter> characters) {
        mCharacters = characters;
    }

    /**
     * Fetches cached data to initialize a film universe.
     *
     * Assumes that the first page of data from SWAPI does not really change.
     * @param context
     * @return
     */
    public static Universe get(Context context) {
        if (sSelf == null) {
            Gson gson = new Gson();
            SwapiResponse response = gson.fromJson(assetToJsonReader(context), SwapiResponse.class);
            CircularArrayWrapper<MovieCharacter> characters = new CircularArrayWrapper<>(
                    response.results.length);
            for (MovieCharacter character : response.results) {
                characters.addLast(character);
            }
            sSelf = new Universe(characters);
        }
        return sSelf;
    }


    private static JsonReader assetToJsonReader(Context context) {
        JsonReader reader = null;
        try {
            reader = new JsonReader(new InputStreamReader(context.getResources().getAssets()
                    .open(ASSET)));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return reader;

    }

    public ICircularArray<MovieCharacter> getCharacters() {
        return mCharacters;
    }

    public MovieCharacter getCharacter(String uri) {
        // Inefficient but there's not that much data, I expect
        for (int i = 0; i < mCharacters.size(); i++) {
            if (mCharacters.get(i).getUrl().equals(uri)) {
                return mCharacters.get(i);
            }
        }

        throw new IllegalStateException("Sending bad URI: " + uri);
    }

    public void fetchMoreCharacters(final MovieCharactersCallback callback) {
        SWAPIApi.get().getMovieCharacters(nextPage, new SWAPIApi.MovieCharactersCallback() {
                @Override
                public void onResponse(List<MovieCharacter> characters) {
                    Universe.this.add(characters);
                    nextPage++;
                    callback.onUpdated(characters);
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e(TAG, t.getMessage());
                }
            }); // end class MovieCharactersCallback
    }

    private void add(List<MovieCharacter> characters) {
        for (MovieCharacter character: characters) {
            mCharacters.addLast(character);
        }
    }

    class SwapiResponse {
        private int count;
        private String next;
        private String previous;
        private MovieCharacter [] results;

        public MovieCharacter[] getMovieCharacters() {
            return results;
        }
    } // end class SwapiResponse

} // end class Universe
