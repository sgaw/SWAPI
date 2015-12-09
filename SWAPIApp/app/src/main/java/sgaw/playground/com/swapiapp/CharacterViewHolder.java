package sgaw.playground.com.swapiapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * {@link RecyclerView.ViewHolder} for {@link sgaw.playground.com.swapiapp.data.FilmCharacter}.
 */
public class CharacterViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.id)
    public TextView mIdView;

    @Bind(R.id.content)
    public TextView mContentView;
    public View mView;

    public CharacterViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        ButterKnife.bind(this, itemView);
    }


    @Override
    public String toString() {
        return super.toString() + " '" + mContentView.getText() + "'";
    }
}
