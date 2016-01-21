
package org.gearvrf.accessibility;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;

abstract class GVRAccessibilityInteractiveObject extends GVRSceneObject {

    public GVRAccessibilityInteractiveObject(GVRContext gvrContext, GVRMesh mesh, GVRTexture texture) {
        super(gvrContext, mesh, texture);
    }

    public abstract void interact();

    public abstract void doAction();
}
