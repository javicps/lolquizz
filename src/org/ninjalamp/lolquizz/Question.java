package org.ninjalamp.lolquizz;

import java.util.Arrays;

public class Question  {

	private String title;
	private String[] options;
	private String rightOption;

	public Question(String[] questionArray) {
		this.title = questionArray[0];
		this.rightOption = questionArray[1];
//		this.options = Arrays.copyOfRange(questionArray, 1, 5);
		String[] args = new String[4];
		for (int i=1; i<5; i++ ) {
			args[i-1] = questionArray[i];
		}
		this.options = args;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getOptions() {
		return options;
	}
	
	public String getOption(int index) {
		return options[index];
	}

	public void setOptions(String[] options) {
		this.options = options;
	}

	public String getRightOption() {
		return rightOption;
	}

	public void setRightOption(String rightOption) {
		this.rightOption = rightOption;
	}

}