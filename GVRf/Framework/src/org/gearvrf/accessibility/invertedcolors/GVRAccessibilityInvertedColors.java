package org.gearvrf.accessibility.invertedcolors;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRPostEffect;
import org.gearvrf.GVRScene;

import android.util.Log;

public class GVRAccessibilityInvertedColors {

    private GVRPostEffect postEffect;
    private GVRAccessibilityPostEffectShader shaderManager;
    private boolean mInverted;
    private GVRContext mGvrContext;

    /**
     * Initialize {@link GVRPostEffect}
     * 
     * @param gvrContext
     */
    public GVRAccessibilityInvertedColors(final GVRContext gvrContext) {
        mGvrContext = gvrContext;
        gvrContext.runOnGlThread(new Runnable() {

            @Override
            public void run() {
                shaderManager = new GVRAccessibilityPostEffectShader(gvrContext);
                postEffect = new GVRPostEffect(gvrContext, shaderManager
                        .getShaderId());

            }
        });
    }

    public void turnOn(final GVRScene... scene) {
        mInverted = true;
        mGvrContext.runOnGlThread(new Runnable() {

            @Override
            public void run() {
                for (GVRScene gvrScene : scene) {
                    gvrScene.getMainCameraRig().getLeftCamera()
                            .addPostEffect(postEffect);
                    gvrScene.getMainCameraRig().getRightCamera()
                            .addPostEffect(postEffect);
                }
            }

        });
        Log.e("test", "turnOn " + mInverted);

    }

    public void turnOff(final GVRScene... scene) {
        mInverted = false;
        mGvrContext.runOnGlThread(new Runnable() {

            @Override
            public void run() {
                for (GVRScene gvrScene : scene) {
                    gvrScene.getMainCameraRig().getLeftCamera()
                            .removePostEffect(postEffect);
                    gvrScene.getMainCameraRig().getRightCamera()
                            .removePostEffect(postEffect);
                }
            }

        });

        Log.e("test", "turnOff " + mInverted);
    }

    public boolean isInverted() {
        return mInverted;
    }

}
