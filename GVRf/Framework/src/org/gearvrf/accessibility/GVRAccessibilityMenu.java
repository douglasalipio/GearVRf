
package org.gearvrf.accessibility;

import java.util.ArrayList;
import java.util.List;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;
import org.gearvrf.R;

public class GVRAccessibilityMenu extends GVRSceneObject {

    private List<GVRSceneObject> menuItems;
    private GVRContext mGvrContext;

    public GVRAccessibilityMenu(GVRContext gvrContext) {
        super(gvrContext);

        mGvrContext = gvrContext;
        createMenuItems();
        createBackButton();
    }

    private void createMenuItems() {
        GVRMesh slot1Mesh = mGvrContext.loadMesh(new GVRAndroidResource(mGvrContext, R.raw.circle_menu));
        GVRTexture slot1Texture = mGvrContext.loadTexture(new GVRAndroidResource(mGvrContext, R.drawable.circle_normal));
        GVRTexture backTexture = mGvrContext.loadTexture(new GVRAndroidResource(mGvrContext, R.drawable.circle_normal_alpha));
        menuItems = new ArrayList<GVRSceneObject>();
        for (int i = 0; i < 8; i++) {
            float degreeNormal = 360.0f * i / (8);
            GVRAccessibilityMenuItem menuItem = new GVRAccessibilityMenuItem(mGvrContext, slot1Mesh, slot1Texture);
            menuItems.add(menuItem);
            menuItem.getTransform().setPosition(0, -1f, 0);
            menuItem.getTransform().rotateByAxis(degreeNormal, 0, 1, 0);
            addChildObject(menuItem);

            if (i == 3) {
                menuItem.setOnAction(new GVRAccessibilityOnAction() {

                    @Override
                    public void setOnAction() {
                        getGVRContext().setMainScene(GVRAccessibilityScene.mainSceneApplication);
                    }
                });
                menuItem.getRenderData().getMaterial().setMainTexture(backTexture);
                menuItem.attachEyePointeeHolder();

            }
        }
    }

    private void createBackButton() {
        GVRSceneObject backButton = new GVRSceneObject(mGvrContext, mGvrContext.createQuad(.3f, .3f),
                mGvrContext.loadTexture(new GVRAndroidResource(mGvrContext, R.drawable.accessibility_back_btn)));
        backButton.getTransform().setPosition(-.2f, -.9f, -.7f);
        backButton.getTransform().rotateByAxis(-90, 1, 0, 0);
        backButton.getTransform().rotateByAxis(35, 0, 1, 0);
        addChildObject(backButton);
    }

}
