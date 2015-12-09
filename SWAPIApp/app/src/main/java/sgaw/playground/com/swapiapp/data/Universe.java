package sgaw.playground.com.swapiapp.data;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.support.v4.util.CircularArray;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import sgaw.playground.com.swapiapp.converters.JSONToCharacterList;

/**
 * Created by shirleygaw on 09/12/15.
 */
public class Universe {
    private static final String ASSET = "CachedPeople.json";

    @VisibleForTesting
    static Universe sSelf = null;
    private CircularArray<FilmCharacter> mCharacters = null;

    public Universe(CircularArray<FilmCharacter> characters) {
        mCharacters = characters;
    }

    public static Universe get(Context context) {
        if (sSelf == null) {
            CircularArray<FilmCharacter> characters = new CircularArray<>();
            JSONToCharacterList converter = new JSONToCharacterList();
            converter.apply(readAsset(context), characters);
            sSelf = new Universe(characters);
        }
        return sSelf;
    }

    private static JSONObject readAsset(Context context) {
        StringWriter writer = new StringWriter();
        try {
            InputStream inputStream = context.getResources().getAssets()
                    .open(ASSET);
            while (inputStream.available() > 0) {
                writer.write(inputStream.read());
            }
            inputStream.close();
            writer.close();
            String jsonString = writer.toString();
            try {
                return new JSONObject(jsonString);
            } catch (JSONException je) {
                je.printStackTrace();
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    public CircularArray<FilmCharacter> getCharacters() {
        return mCharacters;
    }

    public FilmCharacter getCharacter(String uri) {
        // Inefficient but there's not that much data, I expect
        for (int i = 0; i < mCharacters.size(); i++) {
            if (mCharacters.get(i).getUri().equals(uri)) {
                return mCharacters.get(i);
            }
        }

        throw new IllegalStateException("Sending bad URI: " + uri);
    }

}
