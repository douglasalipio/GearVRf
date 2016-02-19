package org.gearvrf.accessibility.speech;


public interface GVRAccessibilitySpeechListener {

    public void onBeginningOfSpeech();

    public void onFinish();

    public void onEndOfSpeech();

    public void onError(int arg0);

    public void onRmsChanged(float arg0);

}
