package sgaw.playground.com.swapiapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import sgaw.playground.com.swapiapp.data.FilmCharacter;
import sgaw.playground.com.swapiapp.data.Universe;

/**
 * A fragment representing a single Character detail screen.
 * This fragment is either contained in a {@link CharacterListActivity}
 * in two-pane mode (on tablets) or a {@link CharacterDetailActivity}
 * on handsets.
 */
public class CharacterDetailFragment extends Fragment {
    public static final String ARG_CHARACTER_ID = "character_uri";

    private static final FilmCharacter DEFAULT_CHARACTER =
            FilmCharacter.newBuilder()
                    .setName("FIXME default character name")
                    .setUri("http://fixme.com")
                    .setBirthYear("badyear")
                    .build();
    private static final String BUNDLE_CHARACTER_URI = "character_uri";

    @Bind(R.id.character_detail)
    TextView mTextView;

    private FilmCharacter mCharacter = null;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CharacterDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_CHARACTER_ID)) {
            mCharacter = Universe.get(getContext()).getCharacter(
                    getArguments().getString(ARG_CHARACTER_ID));
        } else {
            // Shouldn't get here but give the visual clue that something's wrong
            mCharacter = DEFAULT_CHARACTER;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.character_detail, container, false);

        ButterKnife.bind(this, rootView);
        mTextView.setText(mCharacter.getBirthYear());


        Activity activity = this.getActivity();
        if (activity instanceof  CharacterDetailActivity) {
            CollapsingToolbarLayout appBarLayout =
                    ((CharacterDetailActivity) activity).getCollapsingToolbarLayout();
            appBarLayout.setTitle(mCharacter.getName());
        }

        return rootView;
    }
}
