package sgaw.playground.com.swapiapp.data;

import android.support.annotation.ColorRes;
import android.support.annotation.VisibleForTesting;

import sgaw.playground.com.swapiapp.R;

/**
 * Created by shirleygaw on 12/12/15.
 */
public class MovieCharacter {
    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;
    private String[] films;
    private String[] vehicles;
    private String[] starships;
    private String created;
    private String edited;
    private String url;

    public String getUrl() {
        return url;
    }

    public String getBirthYear() {
        return birth_year;
    }

    @VisibleForTesting
    public void setBirthYear(String birthYear) {
        this.birth_year = birthYear;
    }

    public String getName() {
        return name;
    }

    @VisibleForTesting
    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    @VisibleForTesting
    void setEyeColor(String eyeColor) {
        this.eye_color = eyeColor;
    }

    public @ColorRes int getEyeColor() {
        return eyeColorToColorRes(eye_color);
    }

    public @ColorRes int getHairColor() {
        return hairColorToColorRes(hair_color);
    }

    @VisibleForTesting
    void setHairColor(String hairColor) {
        this.hair_color = hairColor;
    }


    @VisibleForTesting
    @ColorRes int eyeColorToColorRes(String eye) {
        if (eye.equals("blue")) {
            return R.color.blue;
        }

        if (eye.equals("yellow")) {
            return R.color.yellow;
        }

        if (eye.equals("red")) {
            return R.color.red;
        }

        if (eye.equals("brown")) {
            return R.color.brown;
        }

        if (eye.equals("blue-grey")) {
            return R.color.grey;
        }

        return R.color.none;
    }

    @VisibleForTesting
    @ColorRes int hairColorToColorRes(String string) {
        String hair = string.split(",")[0]; // ignore other descriptors
        if (hair.equals("black")) {
            return R.color.black;
        }

        if (hair.equals("blond")) {
            return R.color.blond;
        }

        if (hair.equals("auburn")) {
            return R.color.auburn;
        }

        if (hair.equals("white")) {
            return R.color.white;
        }

        if (hair.equals("brown")) {
            return R.color.brown;
        }

        if (hair.equals("grey")) {
            return R.color.grey;
        }

        return R.color.none;
    }
}
