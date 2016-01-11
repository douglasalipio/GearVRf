
package org.gearvrf.accessibility;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRScene;

/**
 * {@link GVRAccessibilityScene} is responsible for encapsulating all
 * accessibility features interactions.<br/>
 * It enables a menu with the following features each one having it's own button <br/>
 * {@link GVRAccessibilityTextSpeech} <br/>
 * {@link GVRAccessibilityInvertedColors} <br/>
 * {@link GVRAccessibilitySettings} <br/>
 * {@link GVRAccessibilityTalkBack} <br/>
 * {@link GVRAccessibilityCaptions} <br/>
 * {@link GVRAccessibilityZoom} <br/>
 * <br/>
 * {@link GVRAccessibilityScene} instantiates a skybox and menu both disabled
 * and a button to add and remove them from the scene.
 */
public class GVRAccessibilityScene extends GVRScene {

    private GVRAccessibilityButton mMenuButton;

    /**
     * Constructor to use default {@link GVRAccessibilityMenuButton}.
     * 
     * @param gvrContext
     */
    public GVRAccessibilityScene(GVRContext gvrContext) {
        super(gvrContext);

        createDefaultMenuButton();
        createScene();
    }

    /**
     * Constructor to use your own {@link GVRAccessibilityMenuButton}.
     * 
     * @param gvrContext
     * @param menuButton
     */
    public GVRAccessibilityScene(GVRContext gvrContext, GVRAccessibilityButton menuButton) {
        super(gvrContext);

        mMenuButton = menuButton;
        createScene();
    }

    private void createScene() {

        createSkyBox();
        createAccessibilityMenu();
        addMenuButtonToMainScene();
    }

    private void createAccessibilityMenu() {
        // GVRAccessibilityMenu menuPanel = new
        // GVRAccessibilityMenu(getGVRContext());
        // addSceneObject(menuPanel);
    }

    private void createSkyBox() {
        // GVRMesh mesh = getGVRContext().loadMesh(new
        // GVRAndroidResource(getGVRContext(),
        // R.raw.skybox_esphere_acessibility));
        // GVRTexture texture = getGVRContext().loadTexture(new
        // GVRAndroidResource(getGVRContext(),
        // R.drawable.skybox_accessibility));
        // GVRSceneObject skybox = new GVRSceneObject(getGVRContext(), mesh,
        // texture);
        // skybox.getTransform().setScale(1, 1, 1);
        // skybox.getRenderData().setRenderingOrder(0);
        // addSceneObject(skybox);
    }

    private void createDefaultMenuButton() {

    }

    private void addMenuButtonToMainScene() {

    }
}
