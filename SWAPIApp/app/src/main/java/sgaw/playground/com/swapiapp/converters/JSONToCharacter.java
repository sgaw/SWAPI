package sgaw.playground.com.swapiapp.converters;

import android.support.annotation.VisibleForTesting;

import org.json.JSONException;
import org.json.JSONObject;

import sgaw.playground.com.swapiapp.data.FilmCharacter;

/**
 * Convert between a /people/N JSON object and a POJO {@link FilmCharacter}.
 */
public class JSONToCharacter {

    public FilmCharacter apply(JSONObject json) throws JSONException {
        FilmCharacter.Builder builder = FilmCharacter.newBuilder()
                .setName(json.getString("name"))
                .setHeight(Integer.parseInt(json.getString("height")))
                .setMass(Integer.parseInt(json.getString("mass")))
                .setBirthYear(json.getString("birth_year"))
                .setGender(convertGender(json.getString("gender")))
                .setUri(json.getString("url"));

        String [] hairStrs = json.getString("hair_color").split(",");
        builder.addHairColor(convertHairColor(hairStrs[0]));
        if (hairStrs.length > 1) {
            builder.addHairColor(convertHairColor(hairStrs[1]));
        }

        builder.setEyeColor(convertEyeColor(json.getString("eye_color")));

        return builder.build();
    }



    @VisibleForTesting
    @FilmCharacter.HairColor int convertHairColor(String hair_color) {
        // Better as a SimpleArrayMap but then there's no interface that's mockable...
        if (hair_color.equals("black")) {
            return FilmCharacter.HAIR_BLACK;
        }

        if (hair_color.equals("blond")) {
            return FilmCharacter.HAIR_BLONDE;
        }

        if (hair_color.equals("auburn")) {
            return FilmCharacter.HAIR_RED;
        }

        if (hair_color.equals("white")) {
            return FilmCharacter.HAIR_WHITE;
        }

        if (hair_color.equals("brown")) {
            return FilmCharacter.HAIR_BROWN;
        }

        if (hair_color.equals("grey")) {
            return FilmCharacter.HAIR_GREY;
        }

        return FilmCharacter.HAIR_NONE;
    }

    @VisibleForTesting
    @FilmCharacter.EyeColor int convertEyeColor(String eye_color) {
        if (eye_color.equals("blue")) {
            return FilmCharacter.EYE_BLUE;
        }

        if (eye_color.equals("yellow")) {
            return FilmCharacter.EYE_YELLOW;
        }

        if (eye_color.equals("red")) {
            return FilmCharacter.EYE_RED;
        }

        if (eye_color.equals("brown")) {
            return FilmCharacter.EYE_BROWN;
        }

        if (eye_color.equals("blue-grey")) {
            return FilmCharacter.EYE_GREY;
        }

        return FilmCharacter.EYE_NONE;
    }

    private @FilmCharacter.Gender int convertGender(String gender) {
        if (gender.equals("male")) {
            return FilmCharacter.GENDER_MALE;
        } else if (gender.equals("female")) {
            return FilmCharacter.GENDER_FEMALE;
        }

        return FilmCharacter.GENDER_NONE;
    }

}
