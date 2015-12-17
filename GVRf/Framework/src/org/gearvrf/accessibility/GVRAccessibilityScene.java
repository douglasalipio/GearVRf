
package org.gearvrf.accessibility;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRScene;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;
import org.gearvrf.R;

public class GVRAccessibilityScene extends GVRScene {

    public GVRAccessibilityScene(GVRContext gvrContext) {
        super(gvrContext);

        createSkyBox();
        createAccessibilityMenu();
    }

    private void createAccessibilityMenu() {
        GVRAccessibilityMenu menuPanel = new GVRAccessibilityMenu(getGVRContext());
        addSceneObject(menuPanel);
    }

    private void createSkyBox() {

        GVRMesh mesh = getGVRContext().loadMesh(new GVRAndroidResource(getGVRContext(), R.raw.skybox_esphere_acessibility));
        GVRTexture texture = getGVRContext().loadTexture(new
                GVRAndroidResource(getGVRContext(), R.drawable.skybox_accessibility));
        GVRSceneObject skybox = new GVRSceneObject(getGVRContext(), mesh, texture);
        skybox.getTransform().setScale(1, 1, 1);
        skybox.getRenderData().setRenderingOrder(0);
        addSceneObject(skybox);
    }
}
