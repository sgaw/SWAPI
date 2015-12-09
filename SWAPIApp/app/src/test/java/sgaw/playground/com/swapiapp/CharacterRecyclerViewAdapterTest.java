package sgaw.playground.com.swapiapp;

import android.support.v4.util.CircularArray;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import sgaw.playground.com.swapiapp.data.FilmCharacter;
import sgaw.playground.com.swapiapp.data.ICircularArray;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by shirleygaw on 09/12/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class CharacterRecyclerViewAdapterTest {
    FilmCharacter mCharacter = null;
    ICircularArray<FilmCharacter> mData = null;
    private CharacterRecyclerViewAdapter underTest;
    @Mock
    CharacterViewHolder mockViewHolder;
    @Captor
    ArgumentCaptor<ShowCharacterDetailFragmentOnClickListener> captor;

    @Before
    public void setupData() {
        mCharacter = FilmCharacter.newBuilder()
                .setName("Fake name")
                .setUri("fakeuri")
                .setBirthYear("fakeyear")
                .build();

        mData = new ICircularArray<FilmCharacter>() {
            @Override
            public FilmCharacter get(int index) {
                return mCharacter;
            }

            @Override
            public int size() {
                return 73;
            }

            @Override
            public void addLast(FilmCharacter filmCharacter) {
                throw new UnsupportedOperationException("not implemented");
            }

        };

        underTest = new CharacterRecyclerViewAdapter(null, false,
                mData);
    }

    @Before
    public void setupMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnBindViewHolder() throws Exception {
        underTest.onBindViewHolder(mockViewHolder, 10);

        verify(mockViewHolder).update(eq(mCharacter), captor.capture());
        assertTrue(mCharacter.getUri().equals(captor.getValue().mCharacterUri));
        verifyNoMoreInteractions(mockViewHolder);
    }

    @Test
    public void testGetItemCount() throws Exception {
        assertEquals(73, underTest.getItemCount());
    }
}