
package org.gearvrf.accessibility;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRScene;
import org.gearvrf.GVRSceneObject;

/**
 * {@literal  It enables a menu with the following features each one having it's own
 *      button: } <li>
 * {@link GVRAccessibilityTextSpeech} <li>
 * {@link GVRAccessibilityInvertedColors} <li>
 *      {@link GVRAccessibilitySettings}
 * <li>
 * {@link GVRAccessibilityTalkBack} <li>
 * {@link GVRAccessibilityCaptions},
 * <li>
 * {@link GVRAccessibilityZoom}<br/>
 * </br> {@literal GVRAccessibilityScene instantiates a sky box and menu both
 * disabled and a button to add and remove them from the {@link GVRSceneObject}.
 * <br/>
 * <br/>
 */
public class GVRAccessibilityManager {

    private GVRContext mGvrContext;
    private GVRAccessibilityTalkBack mTalkBack;
    private GVRAccessibilitySpeechRecognition mSpeechRecognition;
    private GVRAccessibilityInvertedColors mInvertedColors;
    private GVRAccessibilityCaptions mCaptions;
    protected static GVRScene mMainSceneApplication;

    public GVRAccessibilityManager(GVRContext gvrContext, GVRScene mainScene) {
        mGvrContext = gvrContext;
        mTalkBack = new GVRAccessibilityTalkBack(gvrContext.getActivity());
        mSpeechRecognition = new GVRAccessibilitySpeechRecognition();
        mInvertedColors = new GVRAccessibilityInvertedColors(gvrContext);
        mCaptions = GVRAccessibilityCaptions.getInstance(gvrContext);
        mMainSceneApplication = mainScene;
    }

    public GVRAccessibilityTalkBack getTalkBack() {
        return mTalkBack;
    }

    public GVRAccessibilitySpeechRecognition getSpeechRecognition() {
        return mSpeechRecognition;
    }

    public GVRAccessibilityInvertedColors getInvertedColors() {
        return mInvertedColors;
    }

    public GVRAccessibilityCaptions getCaptions() {
        return mCaptions;
    }

   
}
