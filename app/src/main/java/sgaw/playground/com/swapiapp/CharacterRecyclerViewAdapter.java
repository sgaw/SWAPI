package sgaw.playground.com.swapiapp;

import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import sgaw.playground.com.swapiapp.data.MovieCharacter;
import sgaw.playground.com.swapiapp.data.Universe;
import sgaw.playground.com.swapiapp.util.ICircularArray;

/**
 * {@link android.support.v7.widget.RecyclerView.Adapter} for binding {@link MovieCharacter}
 * to their respective view holders.
 */
public class CharacterRecyclerViewAdapter extends RecyclerView.Adapter<CharacterViewHolder> {
    @VisibleForTesting
    final CharacterListActivity.ICharacterDetailLauncher mCharacterDetailLauncher;
    private ICircularArray<MovieCharacter> mCharacters;
    private Universe mUniverse;

    public CharacterRecyclerViewAdapter(
            CharacterListActivity.ICharacterDetailLauncher characterDetailLauncher,
            Universe universe) {
        mCharacterDetailLauncher = characterDetailLauncher;
        mUniverse = universe;
        mCharacters = universe.getCharacters();
    }


    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_list_content, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        final MovieCharacter character = mCharacters.get(position);
        holder.update(character, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCharacterDetailLauncher.show(character);
            }
        });

        // BUGBUG(sgaw): This is probably not the best place for this.  Also we should
        // indicate to the user that we're fetching more data.
        if (position == mCharacters.size() - 1) {
            loadMore();
        }
    }

    @Override
    public int getItemCount() {
        return mCharacters.size();
    }

    private void appendCharacters(List<MovieCharacter> newCharacters) {
        final int positionStart = mCharacters.size();
        for (MovieCharacter character: newCharacters) {
            mCharacters.addLast(character);
        }
        int itemCount = newCharacters.size();
        notifyItemRangeInserted(positionStart, itemCount);
    }


    private void loadMore() {
        // Fetch more data
        mUniverse.fetchMoreCharacters(new Universe.MovieCharactersCallback() {
            @Override
            public void onUpdated(List<MovieCharacter> characters) {
                appendCharacters(characters);
            }
        });
    }

}
