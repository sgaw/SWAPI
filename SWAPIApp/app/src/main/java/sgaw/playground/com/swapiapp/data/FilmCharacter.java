package sgaw.playground.com.swapiapp.data;

import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import sgaw.playground.com.swapiapp.BuildConfig;

/**
 * POJO to represent a film character.
 */
public class FilmCharacter {

    // Replacement for enums in Android
    @IntDef({GENDER_MALE,
            GENDER_FEMALE,
            GENDER_NONE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Gender {}

    public static final int GENDER_NONE = 0;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;

    @IntDef({HAIR_NONE,
            HAIR_BLACK,
            HAIR_BLONDE,
            HAIR_RED,
            HAIR_WHITE,
            HAIR_BROWN,
            HAIR_GREY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface HairColor {}
    public static final @ColorInt int HAIR_NONE = 0xE91E63; // Pink
    public static final @ColorInt int HAIR_BLACK = 0x000000;
    public static final @ColorInt int HAIR_BLONDE = 0xFFEB3B;
    public static final @ColorInt int HAIR_RED = 0xF44336;
    public static final @ColorInt int HAIR_WHITE = 0xFFFFFF;
    public static final @ColorInt int HAIR_BROWN = 0x795548;
    public static final @ColorInt int HAIR_GREY = 0x9E9E9E;


    @IntDef({EYE_NONE,
            EYE_BLUE,
            EYE_YELLOW,
            EYE_RED,
            EYE_BROWN,
            EYE_GREY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EyeColor {}
    public static final int EYE_NONE = 0xE91E63;  // Pink
    public static final @ColorInt int EYE_BLUE = 0x2196F3;
    public static final @ColorInt int EYE_YELLOW = 0xFFEB3B;
    public static final @ColorInt int EYE_RED = 0xF44336;
    public static final @ColorInt int EYE_BROWN = 0x795548;
    public static final @ColorInt int EYE_GREY = 0x607D8B;

    private final String mName;


    private final int mHeight;
    private final int mMass;
    private @HairColor int [] mHairColor;
    private @EyeColor int mEyeColor ;
    private final String mBirthYear;
    private final @Gender int mGender;
    private final String mUri;

    public FilmCharacter(String name, int height, int mass, @HairColor int[] hairColor,
                         @EyeColor int eyeColor, String birthYear, int gender, String uri) {
        this.mName = name;
        this.mHeight = height;
        this.mMass = mass;
        this.mHairColor = hairColor;
        this.mEyeColor = eyeColor;
        this.mBirthYear = birthYear;
        this.mGender = gender;
        this.mUri = uri;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || !(other instanceof FilmCharacter)) {
            return false;
        }

        FilmCharacter that = (FilmCharacter) other;
        return (this.mHeight == that.mHeight)
                && (this.mMass == that.mMass)
                && (this.mHairColor[0] == that.mHairColor[0])
                && (this.mHairColor[1] == that.mHairColor[1])
                && (this.mEyeColor == that.mEyeColor)
                && (this.mBirthYear.equals(that.mBirthYear))
                && (this.mGender == that.mGender);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return mName;
    }

    public @Gender int getGender() {
        return mGender;
    }

    public int getHeight() {
        return mHeight;
    }

    public int getMass() {
        return mMass;
    }

    public @HairColor int[] getHairColor() {
        return mHairColor;
    }

    public @EyeColor int getEyeColor() {
        return mEyeColor;
    }

    public String getBirthYear() {
        return mBirthYear;
    }


    public String getUri() {
        return mUri;
    }


    /**
     * Builder for a Character POJO.
     */
    public static class Builder {
        private int mHeight;
        private int mMass;
        // Only support max two hair colors, two eye colors
        private @HairColor int [] mHairColor = new int[]{-1, -1};
        private @EyeColor int mEyeColor;
        private String mBirthYear;
        private int mGender;
        private String mUri;
        private String mName;

        public Builder setHeight(int height) {
            mHeight = height;
            return this;
        }

        public Builder setMass(int mass) {
            mMass = mass;
            return this;
        }

        public Builder addHairColor(@HairColor int hairColor) {
            if (mHairColor[0] == -1) {
                mHairColor[0] = hairColor;
            } else {
                mHairColor[1] = hairColor;
            }
            return this;
        }

        public Builder setEyeColor(@EyeColor int eyeColor) {
            mEyeColor = eyeColor;
            return this;
        }

        public Builder setBirthYear(String birthYear) {
            mBirthYear = birthYear;
            return this;
        }

        public Builder setGender(@Gender int gender) {
            mGender = gender;
            return this;
        }

        @SuppressWarnings("WrongConstant")
        public FilmCharacter build() {
            if (BuildConfig.DEBUG) {
                if (mBirthYear == null || mBirthYear.isEmpty()
                        || (mUri == null) || mUri.isEmpty()) {
                    throw new IllegalStateException("Expected properties were not set");
                }
            }

            return new FilmCharacter(mName, mHeight, mMass, mHairColor, mEyeColor, mBirthYear,
                    mGender, mUri);
        }

        public Builder setName(String name) {
            mName = name;
            return this;
        }

        public Builder setUri(String uri) {
            mUri = uri;
            return this;
        }
    }
}
