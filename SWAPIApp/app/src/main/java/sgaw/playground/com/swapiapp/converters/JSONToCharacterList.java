package sgaw.playground.com.swapiapp.converters;

import android.support.v4.util.CircularArray;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sgaw.playground.com.swapiapp.data.FilmCharacter;

/**
 * Converter from asset file to list of Character POJOs.
 */
public class JSONToCharacterList {
    private static final String TAG = "JSONToCharacterList";
    JSONToCharacter converter = new JSONToCharacter();

    /**
     * Find the result array and convert the characters into POJO data objects.
     *
     * @param jsonObject to conver
     * @param characters output list
     * @return
     */
    public CircularArray<FilmCharacter> apply(JSONObject jsonObject,
                                              CircularArray<FilmCharacter> characters) {

        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("results");
        for (int i = 0; i < jsonArray.length(); i++) {
            jsonObject = jsonArray.getJSONObject(i);
            FilmCharacter filmCharacter = converter.apply(jsonObject);
            characters.addLast(filmCharacter);
        }
        } catch (JSONException e) {
            Log.e(TAG, "Parse exception", e);
        }
        return  characters;
    }
}
