package sgaw.playground.com.swapiapp;

import android.view.View;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * Created by shirleygaw on 09/12/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class CharacterViewHolderTest {
    @Mock
    View mockView;
    @Mock
    TextView mockTextView;
    @Mock
    TextView mockTextView2;

    private CharacterViewHolder underTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new CharacterViewHolder(mockView, mockTextView, mockTextView2);
    }

    @Test
    public void testUpdate() throws Exception {
        View.OnClickListener stubListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // no-op
            }
        };
        underTest.update(FilmCharacter.newBuilder()
                .setName("foo")
                .setBirthYear("bar")
                .build(), stubListener);
        verify(mockTextView).setText("bar");
        verify(mockTextView2).setText("foo");
        verify(mockView).setOnClickListener(stubListener);
        verifyNoMoreInteractions(mockView, mockTextView, mockTextView2);
    }
}