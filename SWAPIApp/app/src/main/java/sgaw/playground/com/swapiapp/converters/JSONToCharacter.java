package sgaw.playground.com.swapiapp.converters;

import android.support.annotation.VisibleForTesting;
import android.support.v4.util.CircularIntArray;
import android.support.v4.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import sgaw.playground.com.swapiapp.data.Character;

/**
 * Convert between a /people/N JSON object and a POJO {@link Character}.
 */
public class JSONToCharacter {

    public Character apply(JSONObject json) throws JSONException {
        Character.Builder builder = Character.newBuilder()
                .setHeight(Integer.parseInt(json.getString("height")))
                .setMass(Integer.parseInt(json.getString("mass")))
                .setBirthYear(json.getString("birth_year"))
                .setGender(convertGender(json.getString("gender")));

        String [] hairStrs = json.getString("hair_color").split(",");
        builder.addHairColor(convertHairColor(hairStrs[0]));
        if (hairStrs.length > 1) {
            builder.addHairColor(convertHairColor(hairStrs[1]));
        }

        builder.setEyeColor(convertEyeColor(json.getString("eye_color")));

        return builder.build();
    }



    @VisibleForTesting
    @Character.HairColor int convertHairColor(String hair_color) {
        // Better as a SimpleArrayMap but then there's no interface that's mockable...
        if (hair_color.equals("black")) {
            return Character.HAIR_BLACK;
        }

        if (hair_color.equals("blonde")) {
            return Character.HAIR_BLONDE;
        }

        if (hair_color.equals("auburn")) {
            return Character.HAIR_RED;
        }

        if (hair_color.equals("white")) {
            return Character.HAIR_WHITE;
        }

        if (hair_color.equals("brown")) {
            return Character.HAIR_BROWN;
        }

        if (hair_color.equals("grey")) {
            return Character.HAIR_GREY;
        }

        return Character.HAIR_NONE;
    }

    @VisibleForTesting
    @Character.EyeColor int convertEyeColor(String eye_color) {
        if (eye_color.equals("blue")) {
            return Character.EYE_BLUE;
        }

        if (eye_color.equals("yellow")) {
            return Character.EYE_YELLOW;
        }

        if (eye_color.equals("red")) {
            return Character.EYE_RED;
        }

        if (eye_color.equals("brown")) {
            return Character.EYE_BROWN;
        }

        if (eye_color.equals("blue-grey")) {
            return Character.EYE_GREY;
        }

        return Character.EYE_NONE;
    }

    private @Character.Gender int convertGender(String gender) {
        if (gender.equals("male")) {
            return Character.GENDER_MALE;
        } else if (gender.equals("female")) {
            return Character.GENDER_FEMALE;
        }

        return Character.GENDER_NONE;
    }

}
