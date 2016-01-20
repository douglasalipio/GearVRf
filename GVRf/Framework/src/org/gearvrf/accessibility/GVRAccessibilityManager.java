
package org.gearvrf.accessibility;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.R;

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
    private GVRAccessibilityScene mAccessiblityScene;

    public GVRAccessibilityManager(GVRContext gvrContext) {
        this.mGvrContext = gvrContext;
        mTalkBack = new GVRAccessibilityTalkBack(gvrContext.getActivity());
        mSpeechRecognition = new GVRAccessibilitySpeechRecognition();
        mInvertedColors = new GVRAccessibilityInvertedColors(gvrContext);
        mCaptions = GVRAccessibilityCaptions.getInstance(gvrContext);
    }

    public GVRAccessibilityTalkBack getTalkBack() {
        return mTalkBack;
    }

    public GVRAccessibilitySpeechRecognition getSpeechRecognition() {
        return mSpeechRecognition;
    }

    public GVRAccessibilityInvertedColors getINvertedColors() {
        return mInvertedColors;
    }

    public GVRAccessibilityCaptions getCaptions() {
        return mCaptions;
    }

    public GVRAccessibilityScene getAccessiblityScene() {
        return mAccessiblityScene;
    }

    /**
     * Create a default button in position x=0, y=0 and z=0 to start
     * accessibility mode
     * 
     * @param gvrContext
     * @return
     */
    public GVRAccessibilityObject createDefaultButton() {
        GVRAccessibilityObject accessibilityobject = new GVRAccessibilityObject(mGvrContext, mGvrContext.createQuad(1f, 1f),
                mGvrContext.loadTexture(new GVRAndroidResource(mGvrContext, R.drawable.skybox_accessibility)));
        accessibilityobject.getTransform().setPosition(0.0f, 1.5f, -5.0f);
        return accessibilityobject;
    }
}
