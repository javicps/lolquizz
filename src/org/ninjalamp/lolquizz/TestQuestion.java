package org.ninjalamp.lolquizz;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestQuestion {

	@Test
	public void test() {
		String[] questionArray = {"What color is Veigar?", "Blue", "Red", "White", "Orange"};
		Question question = new Question(questionArray);
		assertEquals("What color is Veigar?", question.getTitle());
		assertEquals("Blue", question.getRightOption());
		assertEquals("Blue", question.getOptions()[0]);
		assertEquals("Red", question.getOptions()[1]);
		assertEquals("White", question.getOptions()[2]);
		assertEquals("Orange", question.getOptions()[3]);
	}

}
