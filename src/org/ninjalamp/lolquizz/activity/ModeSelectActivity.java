package org.ninjalamp.lolquizz.activity;

import org.ninjalamp.lolquizz.MediaManager;
import org.ninjalamp.lolquizz.R;
import org.ninjalamp.lolquizz.ScoreManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class ModeSelectActivity extends LolQuizzActivity {

	public static void startActivity(Activity caller) {
		Intent intent = new Intent(caller, ModeSelectActivity.class);
		caller.startActivity(intent);
	}

	private TextView modeInfo;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_mode_select);
		iniElements();
		setListeners();
	}

	private void iniElements() {
		RadioGroup modeRadio = (RadioGroup) findViewById(R.id.mode_select);
		modeRadio.setOnCheckedChangeListener(modeClickListener);
		modeInfo = (TextView)findViewById(R.id.mode_info);
	}

	protected void setListeners() {
		((Button) findViewById(R.id.start)).setOnClickListener(startListener);
	}
	
	OnCheckedChangeListener modeClickListener = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch(checkedId) {
			case R.id.mode_time_attack:
				modeInfo.setText(getText(R.string.mode_time_attack_info));
				break;
			case R.id.mode_survival:
				modeInfo.setText(getText(R.string.mode_survival_info));
				break;
			case R.id.mode_arcade:
				modeInfo.setText(getText(R.string.mode_arcade_info));
				break;
			}
		}

		
	};

	OnClickListener startListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			ScoreManager.resetCurrentScore();
			MediaManager.checkSoundsActivated(ModeSelectActivity.this);
			QuestionActivity.startActivity(ModeSelectActivity.this);
			finish();
		}
	};


}