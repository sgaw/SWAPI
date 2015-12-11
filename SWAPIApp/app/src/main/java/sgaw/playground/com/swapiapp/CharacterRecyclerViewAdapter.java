package sgaw.playground.com.swapiapp;

import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.ref.WeakReference;

import sgaw.playground.com.swapiapp.data.FilmCharacter;
import sgaw.playground.com.swapiapp.util.ICircularArray;

/**
 * {@link android.support.v7.widget.RecyclerView.Adapter} for binding {@link FilmCharacter}
 * to their respective view holders.
 */
public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterViewHolder> {
    @VisibleForTesting
    final CharacterListActivity.ICharacterDetailLauncher mCharacterDetailLauncher;
    private final ICircularArray<FilmCharacter> mCharacters;

    public CharacterRecyclerViewAdapter(
            CharacterListActivity.ICharacterDetailLauncher characterDetailLauncher,
            ICircularArray<FilmCharacter> characters) {
        mCharacterDetailLauncher = characterDetailLauncher;
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
        final FilmCharacter character = mCharacters.get(position);
        holder.update(character, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCharacterDetailLauncher.show(character);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }
}
