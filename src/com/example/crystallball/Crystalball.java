package com.example.crystallball;

import java.util.Random;

public class Crystalball {
	public String [] mAnswers={"It is certain",
			"It is decidedly so",
			"All signs say YES",
			"The stars are not alligned",
			"My reply is NO",
			"It is doubtfull",
			"Better not tell you now",
			"Concentrate and ask again",
			"Unable to answer now",
			"It is hard to say now"
	};
    public String getAnswer(){
    	
		String answer = "";
		//Randomly select one of three answers:Yes,No or Maybe
		Random randomGenerator = new Random();//construct a new random number generator
		int randomNumber = randomGenerator.nextInt(mAnswers.length);
		answer = mAnswers[randomNumber];
		return answer;
    }
}
