package sgaw.playground.com.swapiapp.converters;

import org.junit.Before;
import org.junit.Test;

import org.json.JSONObject;
import static org.junit.Assert.*;

import sgaw.playground.com.swapiapp.data.FilmCharacter;
import com.google.common.collect.ImmutableMap;

/**
 * Created by shirleygaw on 08/12/15.
 */
public class JSONToCharacterTest {
    ImmutableMap<String, Integer> HAIR_MAP = ImmutableMap.<String, Integer>builder()
            .put("blonde", 0xFFEB3B)
            .put("black", 0x000000)
            .put("auburn", 0xF44336)
            .put("white", 0xFFFFFF)
            .put("grey", 0x9E9E9E)
            .put("brown", 0x795548)
            .put("n/a", 0xE91E63)
            .put("none", 0xE91E63)
            .build();
    ImmutableMap<String, Integer> EYE_MAP = ImmutableMap.<String, Integer>builder()
            .put("blue", 0x2196F3)
            .put("yellow", 0xFFEB3B)
            .put("red", 0xF44336)
            .put("brown", 0x795548)
            .put("blue-grey", 0x607D8B)
            .put("n/a", 0xE91E63)
            .build();

    private JSONObject jsonCharacter;
    private JSONToCharacter underTest;


    @Before
    public void setUp() throws Exception {
        underTest = new JSONToCharacter();

        jsonCharacter = new JSONObject("{\"name\": \"Stub name\", " +
                "\"height\": \"230\", " +
                "\"mass\": \"30\", " +
                "\"hair_color\": \"black\", " +
                "\"eye_color\": \"blue-grey\", " +
                "\"birth_year\": \"myyear\", " +
                "\"gender\": \"n/a\", " +
                "\"url\": \"http://fakesite.com/id/5\" }");
    }

    @Test
    public void testConvertHairColor() throws Exception {
        for (String hair : HAIR_MAP.keySet()) {
            assertEquals("Hair string: " + hair, HAIR_MAP.get(hair).intValue(),
                    underTest.convertHairColor(hair));
        }
    }

    @Test
    public void testConvertEyeColor() throws Exception {
        for (String eye : EYE_MAP.keySet()) {
            assertEquals("Eye string: " + eye, EYE_MAP.get(eye).intValue(),
                    underTest.convertEyeColor(eye));
        }
    }

    @Test
    public void testConvert() throws Exception {
        FilmCharacter expected = FilmCharacter.newBuilder()
                .setHeight(230)
                .setMass(30)
                .addHairColor(FilmCharacter.HAIR_BLACK)
                .setEyeColor(FilmCharacter.EYE_GREY)
                .setBirthYear("myyear")
                .setUri("http://fakesite.com/id/5")
                .build();
        assertTrue(expected.equals(underTest.apply(jsonCharacter)));
    }
}