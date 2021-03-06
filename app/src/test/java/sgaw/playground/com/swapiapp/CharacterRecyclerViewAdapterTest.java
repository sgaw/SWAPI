package sgaw.playground.com.swapiapp;

import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import sgaw.playground.com.swapiapp.data.MovieCharacter;
import sgaw.playground.com.swapiapp.data.Universe;
import sgaw.playground.com.swapiapp.util.ICircularArray;

import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by shirleygaw on 09/12/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class CharacterRecyclerViewAdapterTest {
    MovieCharacter mCharacter = null;
    ICircularArray<MovieCharacter> mData = null;
    private CharacterRecyclerViewAdapter underTest;
    @Mock
    CharacterViewHolder mockViewHolder;
    @Mock
    CharacterListActivity.ICharacterDetailLauncher mockLauncher;
    @Captor
    ArgumentCaptor<View.OnClickListener> captor;

    @Before
    public void setupData() {
        mCharacter = new MovieCharacter();

        mData = new ICircularArray<MovieCharacter>() {
            @Override
            public MovieCharacter get(int index) {
                return mCharacter;
            }

            @Override
            public int size() {
                return 73;
            }

            @Override
            public void addLast(MovieCharacter filmCharacter) {
                throw new UnsupportedOperationException("not implemented");
            }

        };

        Universe universe = new Universe(mData);

        underTest = new CharacterRecyclerViewAdapter(mockLauncher, universe);
    }

    @Before
    public void setupMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnBindViewHolder() throws Exception {
        underTest.onBindViewHolder(mockViewHolder, 10);

        verify(mockViewHolder).update(eq(mCharacter), captor.capture());
        assertNotNull(captor.getValue());
        verifyNoMoreInteractions(mockViewHolder);

        captor.getValue().onClick(null);
        // BUGBUG(sgaw): Why doesn't the captor's value == mockLauncher?
        verify(underTest.mCharacterDetailLauncher).show(mCharacter);
    }

    @Test
    public void testGetItemCount() throws Exception {
        assertEquals(73, underTest.getItemCount());
    }
}