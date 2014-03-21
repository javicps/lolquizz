package org.ninjalamp.lolquizz.activity;

import org.ninjalamp.lolquizz.MediaManager;
import org.ninjalamp.lolquizz.R;
import org.ninjalamp.lolquizz.ScoreManager;

import com.hipmob.android.HipmobCore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends LolQuizzActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_main);
		setListeners();
	}

	@Override
	protected void onStart() {
		super.onStart();
		((TextView)findViewById(R.id.score_meter)).setText(String.valueOf(ScoreManager.getHighScore(this)));

	}

	protected void setListeners() {
		((Button) findViewById(R.id.take_quizz)).setOnClickListener(takeQuizzListener);
		((Button) findViewById(R.id.options)).setOnClickListener(settingsListener);
		((Button) findViewById(R.id.take_quizz)).setOnTouchListener(buttonTouchListener);
		((Button) findViewById(R.id.options)).setOnTouchListener(buttonTouchListener);
	}

	OnClickListener takeQuizzListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			ScoreManager.resetCurrentScore();
			MediaManager.checkSoundsActivated(MainActivity.this);
			QuestionActivity.startActivity(MainActivity.this);
			ModeSelectActivity.startActivity(MainActivity.this);
		}
	};

	OnClickListener settingsListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			LolQuizzPreferenceActivity.startActivity(MainActivity.this);
		}
	};
	
	
	OnTouchListener buttonTouchListener = new OnTouchListener() {
		public boolean onTouch(View view, MotionEvent event) {
			Context context = view.getContext();
			alphaAnimation(context, event, view);
			return false;
		}
	};
	
	public static void alphaAnimation(Context context, MotionEvent event, View view) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha));
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha_inverse));
		} else if (event.getAction() == MotionEvent.ACTION_CANCEL) {
			view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.alpha_inverse));
		}
	}

}