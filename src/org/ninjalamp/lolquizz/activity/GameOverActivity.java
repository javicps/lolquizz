package org.ninjalamp.lolquizz.activity;

import org.ninjalamp.lolquizz.R;
import org.ninjalamp.lolquizz.ScoreManager;
import org.ninjalamp.lolquizz.widgets.LolquizzButton;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class GameOverActivity extends LolQuizzActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_game_over);
		setListeners();
	}

	@Override
	protected void onStart() {
		super.onStart();
		((TextView) findViewById(R.id.score_meter)).setText(String.valueOf(ScoreManager.getCurrentScore()));
	}

	protected void setListeners() {
		((LolquizzButton) findViewById(R.id.ok_button)).setOnClickListener(okButtonListener);
	}

	OnClickListener okButtonListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			GameOverActivity.this.finish();
		}
	};

}