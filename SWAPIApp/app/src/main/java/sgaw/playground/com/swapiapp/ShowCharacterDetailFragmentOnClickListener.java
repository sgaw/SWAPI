package sgaw.playground.com.swapiapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentManager;
import android.view.View;

import java.lang.ref.WeakReference;

/**
 * When clicked, show the details of the specified character in a detail fragment.
 *
 * The detail may replace the current activity or may be shown alongside (aka Two-Pane).
 */
class ShowCharacterDetailFragmentOnClickListener implements View.OnClickListener {
    private final WeakReference<FragmentManager> mSupportFragmentManagerRef;
    private final boolean mTwoPane;
    @VisibleForTesting
    public final String mCharacterUri;

    public ShowCharacterDetailFragmentOnClickListener(FragmentManager supportFragmentManager,
                                                      boolean twoPane,
                                                      String characterUri) {
        mSupportFragmentManagerRef = new WeakReference<>(supportFragmentManager);
        mTwoPane = twoPane;
        mCharacterUri = characterUri;
    }

    @Override
    public void onClick(View v) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(CharacterDetailFragment.ARG_CHARACTER_ID,
                    mCharacterUri);
            CharacterDetailFragment fragment = new CharacterDetailFragment();
            fragment.setArguments(arguments);
            mSupportFragmentManagerRef.get().beginTransaction()
                    .replace(R.id.character_detail_container, fragment)
                    .commit();
        } else {
            Context context = v.getContext();
            Intent intent = new Intent(context, CharacterDetailActivity.class);
            intent.putExtra(CharacterDetailFragment.ARG_CHARACTER_ID,
                    mCharacterUri);

            context.startActivity(intent);
        }
    }
}
