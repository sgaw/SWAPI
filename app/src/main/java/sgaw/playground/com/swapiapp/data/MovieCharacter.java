package sgaw.playground.com.swapiapp.data;

import android.support.annotation.ColorRes;

import sgaw.playground.com.swapiapp.R;

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
        return eyeColorToColorRes(eye_color);
    }

    public @ColorRes int getHairColor() {
        return hairColorToColorRes(hair_color);
    }

    private @ColorRes int eyeColorToColorRes(String eye) {
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

        return R.color.clear;
    }

    private @ColorRes int hairColorToColorRes(String string) {
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

        return R.color.clear;
    }
}
