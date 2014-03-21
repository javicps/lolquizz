package org.ninjalamp.lolquizz.activity;

import org.ninjalamp.lolquizz.Constants;
import org.ninjalamp.lolquizz.MediaManager;
import org.ninjalamp.lolquizz.Question;
import org.ninjalamp.lolquizz.QuestionManager;
import org.ninjalamp.lolquizz.R;
import org.ninjalamp.lolquizz.ScoreManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class QuestionActivity extends LolQuizzActivity {
	private static final int TIME_PENALTY_QUESTION_WRONG = -2000;
	private static final int TIME_BONUS_QUESTION_OK = 5000;
	private static final int MILISECONDS_TIME_MODE = 60000;
	private static final int BUTTON_RELEASED = R.drawable.button_released;
	private static final int BUTTON_WRONG = R.drawable.button_wrong;
	private static final int BUTTON_RIGHT = R.drawable.button_right;
	private Button option1;
	private Button option2;
	private Button option3;
	private Button option4;
	private Question question;
	private Button selectedButton;
	private boolean buttonsCanBeClicked = true;
	private TextView time_counter;
	int miliseconds = MILISECONDS_TIME_MODE;
	int milisecondsThisQuestion = MILISECONDS_TIME_MODE;
	private CountDownTimer timer;
	private TextView score_meter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.screen_question);
		iniElements();
		setListeners();
	}

	private void iniElements() {
		option1 = (Button) findViewById(R.id.option1);
		option2 = (Button) findViewById(R.id.option2);
		option3 = (Button) findViewById(R.id.option3);
		option4 = (Button) findViewById(R.id.option4);
		time_counter = (TextView) findViewById(R.id.time_counter);
		score_meter = (TextView) findViewById(R.id.score_meter);
		setEnterAnimations();
		fillQuestion();
		createNewTimer(MILISECONDS_TIME_MODE);
	}

	private void createNewTimer(int secondsUntilFinished) {
		timer = new CountDownTimer(secondsUntilFinished, 1000) {
			public void onTick(long millisUntilFinished) {
				miliseconds = miliseconds - 1000;
				time_counter.setText("" + millisUntilFinished / 1000);
			}

			@Override
			public void onFinish() {
				if (miliseconds <= 1000) {
					finishGame();
				}
			}
		}.start();
	}

	private void setEnterAnimations() {
		option1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_enter1));
		option2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_enter2));
		option3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_enter3));
		option4.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_enter3));
	}

	private void setExitAnimations() {
		option1.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_exit1));
		option2.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_exit2));
		option3.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_exit3));
		option4.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_exit3));
	}

	protected void setListeners() {
		option1.setOnClickListener(optionClickListener);
		option2.setOnClickListener(optionClickListener);
		option3.setOnClickListener(optionClickListener);
		option4.setOnClickListener(optionClickListener);
	}

	OnClickListener optionClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			clickOnAnswer(v);
		}

	};

	private void addScore(int timeDelay) {
		int scoreToAdd = 300 - 30 * timeDelay/1000;
		if (scoreToAdd < 50) {
			scoreToAdd = 50;
		}
		ScoreManager.addScore(scoreToAdd);
		score_meter.setText(String.valueOf(ScoreManager.getCurrentScore()));
	}

	private void updateTimer(int modifier) {
		timer.cancel();
		miliseconds += modifier;
		createNewTimer(miliseconds);
	}

	private void setExitAnimationsWithDelay() {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				setExitAnimations();
			}
		}, 1000);
	}

	private void showQuestionResult(int buttonDrawable) {
		changeButtonBackground(buttonDrawable);
		changeButtonBackgroundWithDelay(BUTTON_RELEASED, 200);
		changeButtonBackgroundWithDelay(buttonDrawable, 400);
		changeButtonBackgroundWithDelay(BUTTON_RELEASED, 1500);
		setButtonsCanBeClickedWithDelay(1500);
	}

	private void changeButtonBackgroundWithDelay(final int buttonId, int delay) {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				changeButtonBackground(buttonId);
			}
		}, delay);
	}

	private void setButtonsCanBeClickedWithDelay(int delay) {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				buttonsCanBeClicked = true;
			}
		}, delay);
	}

	private void fillQuestionWithDelay(int delay) {
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				fillQuestion();
			}
		}, delay);
	}

	private void changeButtonBackground(int buttonId) {
		selectedButton.setBackgroundDrawable(getResources().getDrawable(buttonId));
	}

	private void fillQuestion() {
		question = QuestionManager.getRandomQuestion(this);
		int[] order = QuestionManager.getRandomOrder();
		((TextView) findViewById(R.id.title)).setText(question.getTitle());
		((Button) findViewById(R.id.option1)).setText(question.getOption(order[0]));
		((Button) findViewById(R.id.option2)).setText(question.getOption(order[1]));
		((Button) findViewById(R.id.option3)).setText(question.getOption(order[2]));
		((Button) findViewById(R.id.option4)).setText(question.getOption(order[3]));
	}

	private void finishGame() {
		timer.cancel();
		ScoreManager.updateHighScoreIfGreater(this);
		Intent intent = new Intent(this, GameOverActivity.class);
		intent.putExtra(Constants.CURRENT_SCORE, ScoreManager.getCurrentScore());
		startActivityForResult(intent, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 0) {
			QuestionActivity.this.finish();
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK :
			finishGame();
		}

		return super.onKeyDown(keyCode, event);
	}

	private void clickOnAnswer(View v) {
		if (buttonsCanBeClicked) {
			buttonsCanBeClicked = false;
			selectedButton = (Button) v;
			if (((Button) v).getText().toString().equals(question.getRightOption())) {
				showQuestionResult(BUTTON_RIGHT);
				addScore(milisecondsThisQuestion - miliseconds);
				updateTimer(TIME_BONUS_QUESTION_OK);
				MediaManager.playSound(this, R.raw.sound_ok);
				milisecondsThisQuestion = miliseconds;
			} else {
				showQuestionResult(BUTTON_WRONG);
				updateTimer(TIME_PENALTY_QUESTION_WRONG);
				MediaManager.playSound(this, R.raw.sound_error);
			}
			fillQuestionWithDelay(1500);
			setExitAnimationsWithDelay();
		}
	}

	public static void startActivity(Activity caller) {
		Intent intent = new Intent(caller, QuestionActivity.class);
		caller.startActivity(intent);
	}

}