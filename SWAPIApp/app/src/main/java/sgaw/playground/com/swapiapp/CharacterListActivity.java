package sgaw.playground.com.swapiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.lang.ref.WeakReference;

import butterknife.Bind;
import butterknife.ButterKnife;
import sgaw.playground.com.swapiapp.data.FilmCharacter;
import sgaw.playground.com.swapiapp.data.Universe;

/**
 * An activity representing a list of Characters. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link CharacterDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class CharacterListActivity extends AppCompatActivity {

    /**
     * When the user clicks on a character in a list, show the character's details.
     *
     * Implementation is dependent on screen size, say tablet versus phone.
     */
    public interface ICharacterDetailLauncher {
        void show(FilmCharacter character);
    }

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Bind(R.id.character_list)
    RecyclerView mRecyclerView;

    // Only available on large screens
    @Nullable @Bind(R.id.character_detail_container)
    View mDetailsPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);
        ButterKnife.bind(this);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getTitle());

        setupRecyclerView(mRecyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        ICharacterDetailLauncher launcher;
        if (mDetailsPane != null) {
            launcher = new FragmentCharacterDetailLauncher(
                    getSupportFragmentManager());
        } else {
            launcher = new ActivityCharacterDetailLauncher(this);
        }

        recyclerView.setAdapter(new CharacterRecyclerViewAdapter(launcher,
                Universe.get(this).getCharacters()));
    }

    /**
     * Wrapper to show the specified character's information in a fragment for large screens
     * (master-detail view).
     */
    private class FragmentCharacterDetailLauncher implements ICharacterDetailLauncher {
        private final WeakReference<FragmentManager> mSupportFragmentManagerRef;

        public FragmentCharacterDetailLauncher(FragmentManager supportFragmentManager) {
            mSupportFragmentManagerRef = new WeakReference<>(supportFragmentManager);
        }

        @Override
        public void show(FilmCharacter character) {
            Bundle arguments = new Bundle();
            arguments.putString(CharacterDetailFragment.ARG_CHARACTER_ID,
                    character.getUri());
            CharacterDetailFragment fragment = new CharacterDetailFragment();
            fragment.setArguments(arguments);
            mSupportFragmentManagerRef.get().beginTransaction()
                    .replace(R.id.character_detail_container, fragment)
                    .commit();
        }
    }

    /**
     * Wrapper to show the specified character's information in another screen (activity).
     */
    private class ActivityCharacterDetailLauncher implements ICharacterDetailLauncher {
        private final WeakReference<Context> mContextRef;

        private ActivityCharacterDetailLauncher(Context context) {
            mContextRef = new WeakReference<>(context);
        }

        @Override
        public void show(FilmCharacter character) {
            Context context = mContextRef.get();
            Intent intent = new Intent(context, CharacterDetailActivity.class);
            intent.putExtra(CharacterDetailFragment.ARG_CHARACTER_ID,
                    character.getUri());
            context.startActivity(intent);
        }
    }
}
