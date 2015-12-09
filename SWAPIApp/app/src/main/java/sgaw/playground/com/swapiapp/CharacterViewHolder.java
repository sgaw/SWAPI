package sgaw.playground.com.swapiapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import sgaw.playground.com.swapiapp.data.FilmCharacter;

/**
 * {@link RecyclerView.ViewHolder} for {@link sgaw.playground.com.swapiapp.data.FilmCharacter}.
 */
public class CharacterViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.id)
    TextView mIdView;

    @Bind(R.id.content)
    TextView mContentView;
    View mView;

    public CharacterViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        ButterKnife.bind(this, itemView);
    }

    public void update(FilmCharacter character,
                       ShowCharacterDetailFragmentOnClickListener listener) {
        mIdView.setText(character.getBirthYear());
        mContentView.setText(character.getName());
        mView.setOnClickListener(listener);
    }
    @Override
    public String toString() {
        return super.toString() + " '" + mContentView.getText() + "'";
    }
}
