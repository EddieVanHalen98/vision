package com.evh98.vision.remote;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import java.util.ArrayList;

import ai.api.AIListener;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIDataService;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;

import com.google.gson.JsonElement;
import java.util.Map;

public class AndroidLauncher extends AndroidApplication implements AIListener {

	public static final int REQUEST_SPEECH = 1;

	ActionResolverAndroid actionResolver;

	Remote remote;

	private AIDataService aiDataService;

	final AIConfiguration config = new AIConfiguration("ea44c2de068344ceaf38f4b8bbde48b5",
			ai.api.AIConfiguration.SupportedLanguages.English, AIConfiguration.RecognitionEngine.System);

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
		cfg.useImmersiveMode = true;
		cfg.useAccelerometer = false;
		cfg.useCompass = false;

		actionResolver = new ActionResolverAndroid(this);
		remote = new Remote(actionResolver);

		initialize(remote, cfg);

		aiDataService = new AIDataService(this, config);
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQUEST_SPEECH && resultCode == RESULT_OK) {
			String input = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);

			final AIRequest request = new AIRequest();
			request.setQuery(input);

			new Thread() {
				@Override
				public void run() {
					try {
						final AIResponse response = aiDataService.request(request);

						if (response != null) {
							onResult(response);
						}
					} catch (AIServiceException e) {
						e.printStackTrace();
					}
				}
			}.start();
		}
	}

	@Override
	public void onResult(AIResponse response) {
		Result result = response.getResult();

		// Get parameters
		String parameterString = "";
		if (result.getParameters() != null && !result.getParameters().isEmpty()) {
			for (final Map.Entry<String, JsonElement> entry : result.getParameters().entrySet()) {
				parameterString += "(" + entry.getKey() + ", " + entry.getValue() + ") ";
			}
		}

		Gdx.app.log("Query:", result.getResolvedQuery());
		Gdx.app.log("Action:", result.getAction());
		Gdx.app.log("Parameters:", parameterString);

		remote.client.sendTCP("assistant " + result.getAction() + " " + parameterString);
	}

	@Override
	public void onError(final AIError error) {
		Gdx.app.log("API.AI", "error");
	}

	@Override
	public void onListeningStarted() {}

	@Override
	public void onListeningCanceled() {}

	@Override
	public void onListeningFinished() {}

	@Override
	public void onAudioLevel(final float level) {}
}
