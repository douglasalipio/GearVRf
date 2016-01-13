
package org.gearvrf.accessibility;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRRenderData.GVRRenderMaskBit;
import org.gearvrf.GVRScene;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;
import org.gearvrf.R;

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
 * {@link GVRAccessibilityScene} instantiates a sky box and menu both disabled
 * and a button to add and remove them from the {@link GVRSceneObject}.
 */
public class GVRAccessibilityScene extends GVRScene {

    private GVRAccessibilityButton mMenuButton;
    private GVRSceneObject mRightEye;
    private GVRSceneObject mLeftEye;
    private GVRSceneObject mBothSkyBoxEye;
    private GVRContext mGvrContext;

    /**
     * This constructor creates default scene<br/>
     * <br/>
     * {@code
     *  GVRAccessibilityScene scene = new GVRAccessibilityScene(gvrContext);
     *  gvrContextYouApplication.setMainScene(scene);
     *  
     * }
     * 
     * @param gvrContext
     */
    public GVRAccessibilityScene(GVRContext gvrContext) {
        super(gvrContext);
        mGvrContext = gvrContext;
        createDefaultSkyBox();
    }

    /**
     * This constructor is possible customize a {@code GVRAccessibilityButton}
     * inside scene. </br></br>
     * {@code  
     *  GVRAccessibilityButton openAccessibilityMenu = new GVRAccessibilityButton(gvrContext);
     *  GVRAccessibilityScene scene = new GVRAccessibilityScene(gvrContext, openAccessibilityMenu);
     *  gvrContextYourApplication.setMainScene(scene);}
     * 
     * @param gvrContext
     * @param menuButton for enable accessibility menu.
     */
    public GVRAccessibilityScene(GVRContext gvrContext, GVRAccessibilityButton menuButton) {
        super(gvrContext);
        mGvrContext = gvrContext;
        mMenuButton = menuButton;
        createDefaultSkyBox();
    }

    /**
     * This constructor is possible customize sky box thought
     * {@link GVRSceneObject}.</br></br>
     * {@code
     *  GVRSceneObject skyBox = new GVRSceneObject(gvrContext);
     *  GVRAccessibilityButton button = new GVRAccessibilityButton(gvrContext);}
     * </br>
     * {@code
     *  GVRAccessibilityScene scene = new GVRAccessibilityScene(gvrContext, skyBox, button);
     *  gvrContextYourApplication.setMainScene(scene)
     *  }
     * 
     * @param gvrContext
     * @param menuButton
     */
    public GVRAccessibilityScene(GVRContext gvrContext, GVRSceneObject bothSkyBoxEye, GVRAccessibilityButton menuButton) {
        super(gvrContext);
        mGvrContext = gvrContext;
        mMenuButton = menuButton;
        mBothSkyBoxEye = bothSkyBoxEye;
        createSkyBoxWhithBothEye();
    }

    /**
     * This constructor is possible customize sky box thought
     * {@link GVRSceneObject}.</br></br> GVRSceneObject(gvrContext);
     * {@code 
     * GVRSceneObject skyBox = new GVRSceneObject(gvrContext);
     * GVRAccessibilityScene scene = new GVRAccessibilityScene(gvrContext,skyBox);
     * gvrContextYourApplication.setMainScene(scene) }
     * 
     * @param gvrContext
     * @param bothSkyBoxEye
     */
    public GVRAccessibilityScene(GVRContext gvrContext, GVRSceneObject bothSkyBoxEye) {
        super(gvrContext);
        mGvrContext = gvrContext;
        mBothSkyBoxEye = bothSkyBoxEye;
        createSkyBoxWhithBothEye();
    }

    /**
     * This constructor is possible customize sky box thought
     * {@link GVRSceneObject}.</br></br>
     * {@code
     * 
     * GVRSceneObject leftScreen = new GVRSceneObject(gvrContext);
     * GVRSceneObject rightScreen = new GVRSceneObject(gvrContext);
     * GVRAccessibilityButton initMenu = new GVRAccessibilityButton(gvrContext);
     * 
     * GVRAccessibilityScene scene = new GVRAccessibilityScene(gvrContext,rightScreen, leftScreen, initMenu);
     * gvrContextYourApplication.setMainScene(scene) 
     * }
     * 
     * @param gvrContext
     * @param mesh
     * @param texture
     * @param menuButton
     */
    public GVRAccessibilityScene(GVRContext gvrContext, GVRSceneObject rightEye, GVRSceneObject leftEye, GVRAccessibilityButton menuButton) {
        super(gvrContext);
        mGvrContext = gvrContext;
        mMenuButton = menuButton;
        mRightEye = rightEye;
        mLeftEye = leftEye;
        createSkyBoxLeftAndRightEye();
    }

    /**
     * This constructor is possible customize sky box thought
     * {@link GVRSceneObject}.</br></br>
     * {@code
     * GVRSceneObject leftScreen = new GVRSceneObject(gvrContext);
     * GVRSceneObject rightScreen = new GVRSceneObject(gvrContext);
     * 
     * GVRAccessibilityScene scene = new GVRAccessibilityScene(gvrContext,rightScreen, leftScreen);
     * gvrContextYourApplication.setMainScene(scene) 
     * }
     * 
     * @param gvrContext
     * @param rightEye
     * @param leftEye
     */
    public GVRAccessibilityScene(GVRContext gvrContext, GVRSceneObject rightEye, GVRSceneObject leftEye) {
        super(gvrContext);
        mGvrContext = gvrContext;
        mRightEye = rightEye;
        mLeftEye = leftEye;
        createSkyBoxLeftAndRightEye();
    }

    /**
     * Create sky box using two {@code GVRSceneObject} for each eye
     * 
     * @return
     */
    private GVRAccessibilityScene createSkyBoxLeftAndRightEye() {
        mRightEye.getRenderData().setRenderingOrder(0);
        mRightEye.getRenderData().setRenderMask(GVRRenderMaskBit.Right);

        mLeftEye.getRenderData().setRenderingOrder(0);
        mLeftEye.getRenderData().setRenderMask(GVRRenderMaskBit.Left);

        applyShaderOnSkyBox(mRightEye);
        applyShaderOnSkyBox(mLeftEye);

        addSceneObject(mRightEye);
        addSceneObject(mLeftEye);

        return this;
    }

    /**
     * Create sky box using one {@code GVRSceneObject} for eyes
     * 
     * @return
     */
    private GVRAccessibilityScene createSkyBoxWhithBothEye() {
        mBothSkyBoxEye.getRenderData().setRenderingOrder(0);
        applyShaderOnSkyBox(mBothSkyBoxEye);
        addSceneObject(mBothSkyBoxEye);
        return this;
    }

    /**
     * Create sky box default for accessibility scene.
     * 
     * @return
     */
    private GVRAccessibilityScene createDefaultSkyBox() {
        GVRMesh defaultMesh = getGVRContext().loadMesh(new GVRAndroidResource(getGVRContext(), R.raw.skybox_esphere_acessibility));
        GVRTexture defaultTexture = getGVRContext().loadTexture(new GVRAndroidResource(getGVRContext(), R.drawable.skybox_accessibility));
        GVRSceneObject skyBox = new GVRSceneObject(getGVRContext(), defaultMesh, defaultTexture);
        skyBox.getTransform().setScale(1, 1, 1);
        skyBox.getRenderData().setRenderingOrder(0);
        addSceneObject(skyBox);
        return this;
    }

    /**
     * Apply blur effect on SkyBox
     * 
     * @param skyBox
     */
    private void applyShaderOnSkyBox(GVRSceneObject skyBox) {
        GVRAccessibilitySceneShader shader = new GVRAccessibilitySceneShader(mGvrContext);
        skyBox.getRenderData().getMaterial().setShaderType(shader.getShaderId());
        skyBox.getRenderData().getMaterial().setTexture(GVRAccessibilitySceneShader.TEXTURE_KEY,
                skyBox.getRenderData().getMaterial().getMainTexture());
        skyBox.getRenderData().getMaterial().setFloat(GVRAccessibilitySceneShader.BLUR_INTENSITY, 1);
    }

    private void createDefaultMenuButton() {

    }

    private void addMenuButtonToMainScene() {

    }
}
