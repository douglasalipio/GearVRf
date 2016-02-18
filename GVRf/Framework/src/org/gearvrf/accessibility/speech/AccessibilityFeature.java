package org.gearvrf.accessibility.speech;

import java.util.ArrayList;

import org.gearvrf.GVRContext;
import org.gearvrf.R;
import org.gearvrf.accessibility.GVRAccessibilityManager;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;

class AccessibilityFeature {

    private String volumeUp;
    private String volumeDown;
    private String zoomIn;
    private String zoomOut;
    private String captions;
    private String invertedColors;
    private GVRAccessibilityManager managerFeatures;
    private AudioManager mAudioManager;
    private GVRContext mGvrContext;
    private static AccessibilityFeature instance;

    private AccessibilityFeature(GVRContext gvrContext) {

        if (instance == null) {
            mGvrContext = gvrContext;
            mAudioManager = (AudioManager) gvrContext.getActivity().getSystemService(Context.AUDIO_SERVICE);
            managerFeatures = new GVRAccessibilityManager(gvrContext);
        }

    }

    public static synchronized AccessibilityFeature getInstance(GVRContext gvrContext) {
        if (instance == null) {
            instance = new AccessibilityFeature(gvrContext);
        }

        return instance;
    }

    private void loadCadidateString() {
        volumeUp = mGvrContext.getActivity().getResources().getString(R.string.talk_back_less);
        volumeDown = mGvrContext.getActivity().getResources().getString(R.string.talk_back_more);
        zoomIn = mGvrContext.getActivity().getResources().getString(R.string.zoom_in);
        zoomOut = mGvrContext.getActivity().getResources().getString(R.string.zoom_out);
        captions = mGvrContext.getActivity().getResources().getString(R.string.captions);
        invertedColors = mGvrContext.getActivity().getResources().getString(R.string.inverted_colors);
    }

    public void findMatch(ArrayList<String> speechResult) {

        loadCadidateString();

        for (String matchCandidate : speechResult) {
            if (volumeUp.equals(matchCandidate)) {
                startVolumeUp();
                break;
            } else if (volumeDown.equals(matchCandidate)) {
                startVolumeDown();
                break;
            } else if (zoomIn.equals(matchCandidate)) {
                startZoomIn();
                break;
            } else if (zoomOut.equals(matchCandidate)) {
                startZoomOut();
                break;
            } else if (captions.equals(matchCandidate)) {
                startCaptions();
                break;
            } else if (invertedColors.equals(matchCandidate)) {
                startInvertedColors();
                break;
            }
        }
    }

    private void startVolumeUp() {
        mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);

    }

    private void startVolumeDown() {

        mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
    }

    private void startZoomIn() {

        managerFeatures.getZoom().zoomIn(mGvrContext.getMainScene());
    }

    private void startZoomOut() {
        managerFeatures.getZoom().zoomOut(mGvrContext.getMainScene());
    }

    private void startCaptions() {

    }

    private void startInvertedColors() {
        Log.e("test", "" + managerFeatures.getInvertedColors().isInverted());
        if (managerFeatures.getInvertedColors().isInverted()) {
            managerFeatures.getInvertedColors().turnOff(mGvrContext.getMainScene());
        } else {
            managerFeatures.getInvertedColors().turnOn(mGvrContext.getMainScene());
        }
    }

}
