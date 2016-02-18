package org.gearvrf.accessibility;

import java.util.ArrayList;

import org.gearvrf.GVRContext;
import org.gearvrf.accessibility.invertedcolors.GVRAccessibilityInvertedColors;
import org.gearvrf.accessibility.speech.GVRAccessibilitySpeech;

public class GVRAccessibilityManager {

    private ArrayList<GVRAccessibilityTalkBack> mTalkBacks;
    private GVRAccessibilityCaptions mCaptions;
    private GVRAccessibilityZoom mZoom;
    private GVRAccessibilitySpeech mSpeech;
    private GVRAccessibilityInvertedColors mInvertedColors;

    public GVRAccessibilityManager(GVRContext gvrContext) {

        mTalkBacks = new ArrayList<GVRAccessibilityTalkBack>();
        mInvertedColors = new GVRAccessibilityInvertedColors(gvrContext);
        mZoom = new GVRAccessibilityZoom();
        mSpeech = new GVRAccessibilitySpeech(gvrContext);
    }

    public ArrayList<GVRAccessibilityTalkBack> getTalkBacks() {
        return mTalkBacks;
    }

    public GVRAccessibilityCaptions getCaptions() {
        return mCaptions;
    }

    public GVRAccessibilityZoom getZoom() {
        return mZoom;
    }

    public GVRAccessibilitySpeech getSpeech() {
        return mSpeech;
    }

    public GVRAccessibilityInvertedColors getInvertedColors() {
        return mInvertedColors;
    }

}
