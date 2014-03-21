package org.ninjalamp.lolquizz.activity;

import org.ninjalamp.lolquizz.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Window;

public class LolQuizzPreferenceActivity extends PreferenceActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.layout.preferences);
	}

	public static void startActivity(Activity caller) {
		Intent intent = new Intent(caller, LolQuizzPreferenceActivity.class);
		caller.startActivity(intent);
	}
}