package sgaw.playground.com.swapiapp.data;

import android.support.annotation.ColorRes;

/**
 * Created by shirleygaw on 12/12/15.
 */
public class MovieCharacter {
    private String name;
    private int height;
    private int mass;
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
        return gender;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getMass() {
        return mass;
    }

    public @ColorRes int getEyeColor() {
        return android.R.color.holo_purple;
    }

    public @ColorRes int getHairColor() {
        return android.R.color.holo_green_light;
    }
}
