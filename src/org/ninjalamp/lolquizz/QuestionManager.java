package org.ninjalamp.lolquizz;

import java.lang.reflect.Field;
import java.util.Random;

import android.content.Context;

public class QuestionManager {
	
	public static Question getRandomQuestion(Context context) {
	    Field[] fields = R.array.class.getFields();
	    int resourceId = 0;
		try {
			resourceId = fields[getRandomIndexForQuestion()].getInt(null);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] questionArray = context.getResources().getStringArray(resourceId);
		Question question = new Question(questionArray);
		return question;
	}

	public static int[] getRandomOrder() {
		Random random = new Random();
		int[] array = {0, 1, 2, 3};
		for (int i = 0; i < array.length; i++) {
			int randomPosition = random.nextInt(array.length);
			int temp = array[i];
			array[i] = array[randomPosition];
			array[randomPosition] = temp;
		}
		return array;
	}
	
	public static int getRandomIndexForQuestion() {
	    Field[] fields = R.array.class.getFields();
		Random random = new Random();
		return random.nextInt(fields.length);
	}

}