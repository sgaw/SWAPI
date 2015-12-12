package sgaw.playground.com.swapiapp.data;

import android.content.Context;
import android.support.annotation.VisibleForTesting;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;

import sgaw.playground.com.swapiapp.util.CircularArrayWrapper;
import sgaw.playground.com.swapiapp.util.ICircularArray;

/**
 * Created by shirleygaw on 09/12/15.
 */
public class Universe {
    private static final String ASSET = "CachedPeople.json";

    @VisibleForTesting
    static Universe sSelf = null;
    private ICircularArray<MovieCharacter> mCharacters = null;

    public Universe(ICircularArray<MovieCharacter> characters) {
        mCharacters = characters;
    }

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

    class SwapiResponse {
        private int count;
        private String next;
        private String previous;
        private MovieCharacter [] results;
    }

}
