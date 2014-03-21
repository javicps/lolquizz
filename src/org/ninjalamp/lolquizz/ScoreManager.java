package org.ninjalamp.lolquizz;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class ScoreManager {

	public static int currentScore = 0;

	public static int getHighScore(Activity context) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getBaseContext());
		return preferences.getInt(Constants.HIGH_SCORE, 0);
	}

	public static void updateHighScoreIfGreater(Activity context) {
		if (currentScore > getHighScore(context)) {
			setHighScore(context, currentScore);
		}
	}
	
	public static void resetCurrentScore(){
		currentScore = 0;
	}

	private static void setHighScore(Activity context, int newScore) {
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getBaseContext());
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt(Constants.HIGH_SCORE, newScore);
		editor.commit();
	}

	public static int getCurrentScore() {
		return currentScore;
	}

	public static void addScore(int bonus) {
		currentScore += bonus;
	}

}