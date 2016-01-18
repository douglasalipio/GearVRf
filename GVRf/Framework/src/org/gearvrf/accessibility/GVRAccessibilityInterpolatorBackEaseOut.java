
package org.gearvrf.accessibility;

import org.gearvrf.animation.GVRInterpolator;

public class GVRAccessibilityInterpolatorBackEaseOut implements GVRInterpolator {

    private static GVRAccessibilityInterpolatorBackEaseOut sInstance = null;

    public GVRAccessibilityInterpolatorBackEaseOut() {
    }

    /** Get the (lazy-created) singleton */
    public static synchronized GVRAccessibilityInterpolatorBackEaseOut getInstance() {
        if (sInstance == null) {
            sInstance = new GVRAccessibilityInterpolatorBackEaseOut();
        }
        return sInstance;
    }

    @Override
    public float mapRatio(float ratio) {
        float overshoot = 1.70158f;

        return ((ratio = ratio - 1) * ratio * ((overshoot + 1) * ratio + overshoot) + 1);
    }

}
