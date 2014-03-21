package org.ninjalamp.lolquizz;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.preference.PreferenceManager;

public class MediaManager {

	public static boolean soundsActivated = true;

	public static void checkSoundsActivated(Activity context) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getBaseContext());
		soundsActivated = preferences.getBoolean(context.getString(R.string.sound_preference_key), true);
	}
	
	public static void playSound(Context context, int file) {
		if (soundsActivated) {
			MediaPlayer mp = MediaPlayer.create(context, file);
			if (mp != null) {
				mp.start();
				mp.setOnCompletionListener(new OnCompletionListener() {

					@Override
					public void onCompletion(MediaPlayer mp) {
						mp.release();
					}
				});
			}
		}
	}

}