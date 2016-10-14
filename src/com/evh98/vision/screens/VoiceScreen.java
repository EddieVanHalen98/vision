package com.evh98.vision.screens;

import java.io.IOException;

import com.evh98.vision.Vision;
import com.evh98.vision.util.Graphics;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class VoiceScreen extends Screen {

	String input = "";
	
	public VoiceScreen(GraphicsContext gc, Group root, Scene scene) {
		super(gc, root, scene);
	}
	
	@Override
	public void start() {
		Configuration configuration = new Configuration();

        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        
        
        LiveSpeechRecognizer recognizer = null;
		try {
			recognizer = new LiveSpeechRecognizer(configuration);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		/*
		recognizer.startRecognition(true);
		SpeechResult result = recognizer.getResult();
		recognizer.stopRecognition();
		
		input = result.getHypothesis();
		*/
		
	}

	@Override
	public void render() {
		gc.setFont(Font.font("Roboto Thin", 100 * Vision.SCALE));
		Graphics.text(gc, input, 0, 0);
	}
	
}