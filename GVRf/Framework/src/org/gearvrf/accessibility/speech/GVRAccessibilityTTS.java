package org.gearvrf.accessibility.speech;

import java.util.ArrayList;

import org.gearvrf.GVRContext;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

/**
 * This class initializes Speech Recognizer, capture user voice, convert it to text and treats it as a command by comparing to a list of predefined text commands.
 */
final class GVRAccessibilityTTS implements RecognitionListener {
    private SpeechRecognizer mSpeechRecognizer;
    private Intent mSpeechRecognizerIntent;
    private boolean mActive;
    private GVRContext mContext;
    private GVRAccessibilitySpeechListener mSpeechListener;

    public GVRAccessibilityTTS(GVRContext context) {

        mContext = context;
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(mContext.getActivity());
        mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
                mContext.getActivity().getPackageName());
        mSpeechRecognizer.setRecognitionListener(this);
    }

    @Override
    public void onBeginningOfSpeech() {
        if (mSpeechListener != null)
            mSpeechListener.onBeginningOfSpeech();
    }

    @Override
    public void onBufferReceived(byte[] arg0) {
    }

    @Override
    public void onEndOfSpeech() {
    }

    @Override
    public void onError(int arg0) {
        Log.e("test", "erro de network");
    }

    @Override
    public void onEvent(int arg0, Bundle arg1) {
    }

    @Override
    public void onPartialResults(Bundle arg0) {
    }

    @Override
    public void onReadyForSpeech(Bundle arg0) {
    }

    @Override
    public void onResults(Bundle results) {

        ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        AccessibilityFeature.getInstance(mContext).findMatch(matches);
        if (mSpeechListener != null)
            mSpeechListener.onFinish();

    }

    @Override
    public void onRmsChanged(float arg0) {

    }

    private void readFeatureName() {

    }

    public boolean isActive() {
        return mActive;
    }

    public void setActive(boolean mActive) {
        this.mActive = mActive;
    }

    public void setSpeechRecognizer(SpeechRecognizer mSpeechRecognizer) {
        this.mSpeechRecognizer = mSpeechRecognizer;
    }

    public GVRAccessibilitySpeechListener getSpeechListener() {
        return mSpeechListener;
    }

    public void setSpeechListener(GVRAccessibilitySpeechListener mSpeechListener) {
        this.mSpeechListener = mSpeechListener;
    }

    public Intent getSpeechRecognizerIntent() {
        return mSpeechRecognizerIntent;
    }

    public SpeechRecognizer getSpeechRecognizer() {
        return mSpeechRecognizer;
    }

}
