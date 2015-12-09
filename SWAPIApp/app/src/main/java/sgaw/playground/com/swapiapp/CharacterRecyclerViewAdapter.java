package sgaw.playground.com.swapiapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.util.CircularArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import sgaw.playground.com.swapiapp.data.FilmCharacter;
import sgaw.playground.com.swapiapp.data.Universe;

/**
 * Created by shirleygaw on 09/12/15.
 */
public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterViewHolder> {
    private final WeakReference<FragmentManager> mSupportFragmentManagerRef;
    private final boolean mTwoPane;
    private final CircularArray<FilmCharacter> mCharacters;

    public CharacterRecyclerViewAdapter(FragmentManager supportFragmentManager,
                                        boolean twoPane, CircularArray<FilmCharacter> characters) {
        mSupportFragmentManagerRef = new WeakReference<>(supportFragmentManager);
        mTwoPane = twoPane;
        mCharacters = characters;
    }


    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_list_content, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        FilmCharacter character = mCharacters.get(position);
        holder.mIdView.setText(character.getBirthYear());
        holder.mContentView.setText(character.getName());
        holder.mView.setOnClickListener(new ShowCharacterDetailFragmentOnClickListener(
                mSupportFragmentManagerRef.get(), mTwoPane, character.getUri()));
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }
}
