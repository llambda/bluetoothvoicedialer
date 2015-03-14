package org.gman;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class BTPrefs extends PreferenceActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		PreferenceManager p = getPreferenceManager();
		p.setSharedPreferencesName(PREFS_NAME);
		addPreferencesFromResource(R.xml.preferences);
	}

}
