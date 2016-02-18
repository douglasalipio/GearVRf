package org.gearvrf.accessibility.speech;

import org.gearvrf.GVRContext;

public class GVRAccessibilitySpeech {

    private GVRAccessibilityTTS mTts;

    public GVRAccessibilitySpeech(GVRContext gvrContext) {

        mTts = new GVRAccessibilityTTS(gvrContext);
    }

    /**
     * Start speech recognizer.
     * 
     * @param speechListener
     */
    public void start(GVRAccessibilitySpeechListener speechListener) {
        mTts.setSpeechListener(speechListener);
        mTts.getSpeechRecognizer().startListening(mTts.getSpeechRecognizerIntent());
    }

    /**
     * Active talk back for accessibility features.
     */
    public void active() {
        mTts.setActive(true);
    }

    /**
     * Inactive talk back for accessibility features.
     */
    public void inactive() {
        mTts.setActive(false);
    }

    /**
     * Destroys the SpeechRecognizer object.
     */
    public void destroy() {
        mTts.getSpeechRecognizer().destroy();
    }

}
