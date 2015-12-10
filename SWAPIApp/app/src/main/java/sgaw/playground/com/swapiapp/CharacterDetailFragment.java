package sgaw.playground.com.swapiapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
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

    @Bind(R.id.text_view_height)
    TextView mTextViewHeight;

    @Bind(R.id.text_view_weight)
    TextView mTextViewWeight;

    @Bind(R.id.text_view_birth_year)
    TextView mTextViewBirthYear;

    @Bind(R.id.text_view_eye)
    TextView mImageViewEye;

    @Bind(R.id.text_view_hair)
    TextView mTextViewHair;

    public interface ICharacterDetailPresenter {
        void setBirthYear(String birthYear, TextView textView);
        void setHeight(int height, TextView textView);
        void setWeight(int weight, TextView textView);
        void setEyeColor(@FilmCharacter.EyeColor int eyeColor, TextView textView);
        void setHairColor(@FilmCharacter.HairColor int[] hairColor, TextView textView);
    }

    private FilmCharacter mCharacter = null;
    private ICharacterDetailPresenter mPresenter = new CharacterDetailPresenter();

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

        updateViews();

        Activity activity = this.getActivity();
        if (activity instanceof  CharacterDetailActivity) {
            CollapsingToolbarLayout appBarLayout =
                    ((CharacterDetailActivity) activity).getCollapsingToolbarLayout();
            appBarLayout.setTitle(mCharacter.getName());
        }

        return rootView;
    }

    private void updateViews() {
        mPresenter.setBirthYear(mCharacter.getBirthYear(), mTextViewBirthYear);
        mPresenter.setHeight(mCharacter.getHeight(), mTextViewHeight);
        mPresenter.setWeight(mCharacter.getMass(), mTextViewWeight);
        mPresenter.setEyeColor(mCharacter.getEyeColor(), mImageViewEye);
        mPresenter.setHairColor(mCharacter.getHairColor(), mTextViewHair);
    }

    @VisibleForTesting
    class CharacterDetailPresenter implements ICharacterDetailPresenter {

        @Override
        public void setBirthYear(String birthYear, TextView textView) {
            textView.setText(birthYear);
        }

        @Override
        public void setHeight(int height, TextView textView) {
            textView.setText(String.valueOf(height));
        }

        @Override
        public void setWeight(int weight, TextView textView) {
            textView.setText(String.valueOf(weight));
        }

        @Override
        public void setEyeColor(@FilmCharacter.EyeColor int eyeColor, TextView textView) {
            textView.getCompoundDrawables()[3].setTint(eyeColor);
        }

        @Override
        public void setHairColor(@FilmCharacter.HairColor int[] hairColor, TextView textView) {
            if (hairColor[0] == FilmCharacter.HAIR_NONE) {
                textView.setCompoundDrawables(null, null, null, null);
            } else {
                textView.getCompoundDrawables()[3].setTint(hairColor[0]);
            }
        }
    }
}
