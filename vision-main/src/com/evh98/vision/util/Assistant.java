package com.evh98.vision.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.evh98.vision.Vision;

public class Assistant {
	
	Vision vision;
	
	Robot robot;
	
	public Assistant(Vision vision) {
		this.vision = vision;
		
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}
	
	public void execute(String text) {
		String command = text.substring(0, text.indexOf(' '));
		String param = text.substring(text.indexOf('\"') + 1, text.length() - 3).toLowerCase();
		
		if (command.equals("input.unknown")) {
			inputUnknown();
		}
		else if (command.equals("smalltalk.person")) {
			smalltalkPerson(param);
		}
		else if (command.equals("app.open")) {
			appOpen(param);
		}
		else if (command.equals("movie.random")) {
			movieRandom();
		}
		else if (command.equals("movie.play")) {
			moviePlay(param);
		}
		else if (command.equals("playback.pause")) {
			playbackPause();
		}
	}
	
	private void inputUnknown() {
		generateResponse("input_unknown", 3);
	}
	
	private void smalltalkPerson(String question) {
		switch (question) {
		case "who are you": generateResponse("who_are_you", 3);
			break;
		case "your age": generateResponse("who_are_you", 3);
			break;
		case "your birth date": generateResponse("who_are_you", 3);
			break;
		case "where are you from": generateResponse("who_are_you", 3);
			break;
		case "where do you work": generateResponse("who_are_you", 3);
			break;
		case "are you married": generateResponse("who_are_you", 3);
			break;
		case "do you love me": generateResponse("who_are_you", 3);
			break;
		case "are you okay": generateResponse("who_are_you", 3);
		}
	}
	
	private void appOpen(String app) {
		generateResponse("confirmation", 3);
		
		switch (app) {
		case "home": vision.setScreen(vision.home_screen);
			break;
		case "movies": vision.setScreen(vision.movies_screen);
			break;
		case "youtube": vision.setScreen(vision.youtube_screen);
			break;
		case "netflix": vision.setScreen(vision.media_screen);
		}
	}
	
	private void movieRandom() {
		Random r = new Random();
		int i = r.nextInt(Vision.movies.size());
		Vision.movies.get(i).open();
	}
	
	private void moviePlay(String title) {
		generateResponse("confirmation", 3);
		
		Vision.search.feelingLuckyMovies(title);
	}
	
	private void playbackPause() {
		robot.keyPress(KeyEvent.VK_SPACE);
		robot.keyRelease(KeyEvent.VK_SPACE);
	}
	
	private void generateResponse(String query, int amount) {
		Random r = new Random();
		int i = r.nextInt(amount - 1) + 1;
		Gdx.audio.newSound(Gdx.files.internal("assistant/" + query + i + ".mp3")).play();
	}
}