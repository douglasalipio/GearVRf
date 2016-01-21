
package org.gearvrf.accessibility;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRPostEffect;

public class GVRAccessibilityInvertedColors {

    private GVRPostEffect postEffect;
    private GVRAccessibilityPostEffectShader shaderManager;
    private GVRContext mGvrContext;
    private boolean hasPostEffect;
    private GVRAccessibilityItem mAccessibilityItem;

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
                postEffect = new GVRPostEffect(gvrContext, shaderManager.getShaderId());
            }
        });
    }

    private void turnOn() {
        mGvrContext.getMainScene().getMainCameraRig().getLeftCamera().addPostEffect(postEffect);
        mGvrContext.getMainScene().getMainCameraRig().getRightCamera().addPostEffect(postEffect);
    }

    private void turnOff() {
        mGvrContext.getMainScene().getMainCameraRig().getLeftCamera().removePostEffect(postEffect);
        mGvrContext.getMainScene().getMainCameraRig().getRightCamera().removePostEffect(postEffect);
    }

    /**
     * Switch inverted colors between on/off state.
     */
    public void switchState() {
        if (mAccessibilityItem.isActive) {
            if (!hasPostEffect) {
                turnOn();
            } else {
                turnOff();
            }
            hasPostEffect = !hasPostEffect;
        }
    }

    public void setAccessibilityItem(GVRAccessibilityItem accessibilityItem) {
        mAccessibilityItem = accessibilityItem;
    }
}
