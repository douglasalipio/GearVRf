
package org.gearvrf.accessibility;

import org.gearvrf.GVRPerspectiveCamera;
import org.gearvrf.GVRScene;

public class GVRAccessibilityZoom {

    private static final float MAX_ZOOM = 45f;
    private static final float MIN_ZOOM = 90f;
    private static final float ZOOM_FACTOR = 5f;

    public void zoomIn(GVRScene... gvrScenes) {
        for (GVRScene gvrScene : gvrScenes) {
            GVRPerspectiveCamera leftCameraMainScene = ((GVRPerspectiveCamera) gvrScene.getMainCameraRig().getLeftCamera());
            GVRPerspectiveCamera rightCameraMainScene = ((GVRPerspectiveCamera) gvrScene.getMainCameraRig().getRightCamera());
            if (leftCameraMainScene.getFovY() > MAX_ZOOM) {
                leftCameraMainScene.setFovY(leftCameraMainScene.getFovY() - ZOOM_FACTOR);
                rightCameraMainScene.setFovY(rightCameraMainScene.getFovY() - ZOOM_FACTOR);
            }
        }
    }

    public void zoomOut(GVRScene... gvrScenes) {
        for (GVRScene gvrScene : gvrScenes) {
            GVRPerspectiveCamera leftCameraMainScene = ((GVRPerspectiveCamera) gvrScene.getMainCameraRig().getLeftCamera());
            GVRPerspectiveCamera rightCameraMainScene = ((GVRPerspectiveCamera) gvrScene.getMainCameraRig().getRightCamera());
            if (leftCameraMainScene.getFovY() < MIN_ZOOM) {
                leftCameraMainScene.setFovY(leftCameraMainScene.getFovY() + ZOOM_FACTOR);
                rightCameraMainScene.setFovY(rightCameraMainScene.getFovY() + ZOOM_FACTOR);
            }
        }
    }

}
