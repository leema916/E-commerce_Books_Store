package ma.projet.my_app.my_fragments;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import ma.projet.my_app.R;

public class SettingFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}