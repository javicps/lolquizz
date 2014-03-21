package org.ninjalamp.lolquizz.widgets;

import org.ninjalamp.lolquizz.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class LolquizzButton extends Button {

	public LolquizzButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		iniElements();
	}

	public LolquizzButton(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		iniElements();
	}

	private void iniElements() {
		this.setOnTouchListener(buttonTouchListener);
	}

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