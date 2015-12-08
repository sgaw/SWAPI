package sgaw.playground.com.swapiapp.converters;

import android.content.Context;
import android.support.v4.util.CircularArray;

import sgaw.playground.com.swapiapp.data.FilmCharacter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * Converter from asset file to list of Character POJOs.
 */
public class JSONToCharacterList {
    private static final String ASSET = "CachedPeople.json";
    JSONToCharacter converter = new JSONToCharacter();

    public CircularArray<FilmCharacter> apply(JSONObject jsonObject) throws JSONException {
        CircularArray<FilmCharacter> result = new CircularArray<>(10);
        JSONArray jsonArray = jsonObject.getJSONArray("results");
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            FilmCharacter filmCharacter = converter.apply(jsonObject);
            result.addLast(filmCharacter);
        }
        return  result;
    }

    public static JSONObject readAsset(Context context) {
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
}
