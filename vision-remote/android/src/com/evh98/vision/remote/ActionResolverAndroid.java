package com.evh98.vision.remote;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;

/**
 * Created by jts on 08/01/2017.
 */

public class ActionResolverAndroid extends Activity implements ActionResolver {

    public static final int REQUEST_OK = 1;

    Handler uiThread;
    Context appContext;

    public ActionResolverAndroid(Context appContext) {
        uiThread = new Handler();
        this.appContext = appContext;
    }

    @Override
    public void showToast(final CharSequence toastMessage, final int toastDuration) {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(appContext, toastMessage, toastDuration).show();
            }
        });
    }

    @Override
    public void showSpeechPopup() {
        uiThread.post(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                try {
                    // We open the Speech dialog here using a request code
                    // and retrieve the spoken text in AndroidLauncher's onActivityResult().

                    ((Activity)appContext).startActivityForResult(i, REQUEST_OK);
                } catch (Exception e) {

                    showToast(e.toString(), 5000);

                    Gdx.app.log(ActionResolverAndroid.class.getName(), "error initializing speech engine" + e);
                }
            }
        });
    }

}
