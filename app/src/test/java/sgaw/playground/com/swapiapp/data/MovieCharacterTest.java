package sgaw.playground.com.swapiapp.data;

import com.google.common.collect.ImmutableMap;

import org.junit.Test;

import sgaw.playground.com.swapiapp.R;

import static org.junit.Assert.*;

/**
 * Created by shirleygaw on 12/12/15.
 */
public class MovieCharacterTest {
    private static final ImmutableMap<String, Integer> HAIR_MAP =
            ImmutableMap.<String, Integer>builder()
                    .put("blond", R.color.blond)
                    .put("black", R.color.black)
                    .put("auburn", R.color.auburn)
                    .put("white", R.color.white)
                    .put("grey", R.color.grey)
                    .put("brown", R.color.brown)
                    .put("n/a", R.color.none)
                    .put("none", R.color.none)
                    .build();
    private static final ImmutableMap<String, Integer> EYE_MAP =
            ImmutableMap.<String, Integer>builder()
                    .put("blue", R.color.blue)
                    .put("yellow", R.color.yellow)
                    .put("red", R.color.red)
                    .put("brown", R.color.brown)
                    .put("blue-grey", R.color.grey)
                    .put("n/a", R.color.none)
                    .build();

    MovieCharacter underTest = new MovieCharacter();

    @Test
    public void testGetEyeColor() throws Exception {
        underTest.setEyeColor("blue-grey");
        assertEquals(R.color.grey, underTest.getEyeColor());
    }

    @Test
    public void testGetHairColor() throws Exception {
        underTest.setHairColor("auburn, black");
        assertEquals(R.color.auburn, underTest.getHairColor());
    }

    @Test
    public void testEyeColorToColorRes() throws Exception {
        for (String colorStr: EYE_MAP.keySet()) {
            assertEquals(EYE_MAP.get(colorStr).intValue(), underTest.eyeColorToColorRes(colorStr));
        }
    }

    @Test
    public void testHairColorToColorRes() throws Exception {
        for (String colorStr: HAIR_MAP.keySet()) {
            assertEquals(HAIR_MAP.get(colorStr).intValue(), underTest.hairColorToColorRes(colorStr));
        }
    }
}