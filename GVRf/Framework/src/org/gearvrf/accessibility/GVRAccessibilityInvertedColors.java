package org.gearvrf.accessibility;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRPostEffect;
import org.gearvrf.GVRScene;

public class GVRAccessibilityInvertedColors {

    private GVRPostEffect postEffect;
    private GVRAccessibilityPostEffectShader shaderManager;
    private GVRContext mGvrContext;
    private boolean isInverted;

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

    public void turnOn(GVRScene scene) {
        scene.getMainCameraRig().getLeftCamera()
                .addPostEffect(postEffect);
        scene.getMainCameraRig().getRightCamera()
                .addPostEffect(postEffect);
        isInverted = true;
    }

    public void turnOff(GVRScene scene) {
        scene.getMainCameraRig().getLeftCamera()
                .removePostEffect(postEffect);
        scene.getMainCameraRig().getRightCamera()
                .removePostEffect(postEffect);
        isInverted = false;
    }

    public boolean isInverted() {
        return isInverted;
    }

}
