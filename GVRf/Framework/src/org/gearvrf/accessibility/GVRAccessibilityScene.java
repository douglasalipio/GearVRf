
package org.gearvrf.accessibility;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVREyePointeeHolder;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRPicker;
import org.gearvrf.GVRRenderData.GVRRenderMaskBit;
import org.gearvrf.GVRScene;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;
import org.gearvrf.R;

/**
 * {@link GVRAccessibilityScene} is responsible for encapsulating all
 * accessibility features interactions.<br/>
 * &nbsp; &nbsp;&nbsp; Add to scene in your project:
 * 
 * <pre>
 * GVRAccessibilityScene scene = new GVRAccessibilityScene(gvrContext);
 * gvrContextYouApplication.setMainScene(scene);
 * </pre>
 */
public class GVRAccessibilityScene extends GVRScene {

    private GVRAccessibilityButton mMenuButton;
    private GVRSceneObject mRightEye;
    private GVRSceneObject mLeftEye;
    private GVRSceneObject mBothEyesSkyBox;
    private GVRContext mGvrContext;

    /**
     * This constructor creates default scene</p>
     * 
     * <pre>
     * GVRAccessibilityScene scene = new GVRAccessibilityScene(gvrContext);
     * gvrContextYouApplication.setMainScene(scene);
     * </pre>
     * 
     * @param gvrContext
     */
    public GVRAccessibilityScene(GVRContext gvrContext) {
        super(gvrContext);
        mGvrContext = gvrContext;
        createDefaultSkyBox();
        createItems();
        backToMainScene();
    }

    /**
     * This constructor is possible customize a {@code GVRAccessibilityButton}
     * inside scene. </p>
     * 
     * <pre>
     * GVRAccessibilityButton openAccessibilityMenu = new GVRAccessibilityButton(gvrContext);
     * GVRAccessibilityScene scene = new GVRAccessibilityScene(gvrContext, openAccessibilityMenu);
     * gvrContextYourApplication.setMainScene(scene);
     * </pre>
     * 
     * @param gvrContext
     * @param menuButton for enable accessibility menu.
     */
    public GVRAccessibilityScene(GVRContext gvrContext, GVRAccessibilityButton menuButton) {
        super(gvrContext);
        mGvrContext = gvrContext;
        mMenuButton = menuButton;
        createDefaultSkyBox();
        createItems();
        backToMainScene();
    }

    /**
     * With this constructor it is possible to customize sky box thought
     * {@link GVRSceneObject}.</p>
     * 
     * <pre>
     *  GVRSceneObject skyBox = new GVRSceneObject(gvrContext);
     *  GVRAccessibilityButton button = new GVRAccessibilityButton(gvrContext);}
     *  GVRAccessibilityScene scene = new GVRAccessibilityScene(gvrContext, skyBox, button);
     *  gvrContextYourApplication.setMainScene(scene)
     * </pre>
     * 
     * @param gvrContext
     * @param menuButton
     */
    public GVRAccessibilityScene(GVRContext gvrContext, GVRSceneObject bothEyesSkyBox, GVRAccessibilityButton menuButton) {
        super(gvrContext);
        mGvrContext = gvrContext;
        mMenuButton = menuButton;
        mBothEyesSkyBox = bothEyesSkyBox;
        createSkyBoxForBothEyes();
        createItems();
        backToMainScene();
    }

    /**
     * With this constructor it is possible to customize sky box thought
     * {@link GVRSceneObject}.</p>
     * 
     * <pre>
     * GVRSceneObject skyBox = new GVRSceneObject(gvrContext);
     * GVRAccessibilityScene scene = new GVRAccessibilityScene(gvrContext,skyBox);
     * gvrContextYourApplication.setMainScene(scene)
     * </pre>
     * 
     * @param gvrContext
     * @param bothEyeSkyBox
     */
    public GVRAccessibilityScene(GVRContext gvrContext, GVRSceneObject bothEyeSkyBox) {
        super(gvrContext);
        mGvrContext = gvrContext;
        mBothEyesSkyBox = bothEyeSkyBox;
        createSkyBoxForBothEyes();
        createItems();
        backToMainScene();
    }

    /**
     * With this constructor it is possible to customize sky box thought
     * {@link GVRSceneObject}.</p>
     * 
     * <pre>
     * GVRSceneObject leftScreen = new GVRSceneObject(gvrContext);
     * GVRSceneObject rightScreen = new GVRSceneObject(gvrContext);
     * GVRAccessibilityButton initMenu = new GVRAccessibilityButton(gvrContext);
     * 
     * GVRAccessibilityScene scene = new GVRAccessibilityScene(gvrContext,rightScreen, leftScreen, initMenu);
     * gvrContextYourApplication.setMainScene(scene)
     * </pre>
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
        createItems();
        backToMainScene();
    }

    /**
     * With this constructor it is possible to customize sky box thought
     * {@link GVRSceneObject}.</br></br>
     * 
     * <pre>
     * GVRSceneObject leftScreen = new GVRSceneObject(gvrContext);
     * GVRSceneObject rightScreen = new GVRSceneObject(gvrContext);
     * 
     * GVRAccessibilityScene scene = new GVRAccessibilityScene(gvrContext,rightScreen, leftScreen);
     * gvrContextYourApplication.setMainScene(scene)
     * </pre>
     * 
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
        createItems();
        backToMainScene();
    }

    /**
     * Create sky box using one {@code GVRSceneObject} for each eye
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
     * Create sky box using one {@code GVRSceneObject} for both eyes
     * 
     * @return
     */
    private GVRAccessibilityScene createSkyBoxForBothEyes() {
        mBothEyesSkyBox.getRenderData().setRenderingOrder(0);
        applyShaderOnSkyBox(mBothEyesSkyBox);
        addSceneObject(mBothEyesSkyBox);
        return this;
    }

    /**
     * Create default sky box for accessibility scene.
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

    private void createItems() {
        float positionX = 0f;
        float positionY = -2f;
        float positionZ = -10f;
        float scale = 0.03f;
        GVRMesh mesh = getGVRContext().loadMesh(new GVRAndroidResource(getGVRContext(), R.raw.accessibility_item));
        GVRAccessibilityItem invertedColors = new GVRAccessibilityItem(getGVRContext(), mesh, getGVRContext()
                .loadTexture(new GVRAndroidResource(getGVRContext(), R.drawable.inverted_colors)));
        invertedColors.getTransform().setPosition(positionX, positionY, positionZ);
        invertedColors.getTransform().setScale(scale, scale, scale);
        invertedColors.attachEyePointeeHolder();
        this.addSceneObject(invertedColors);

        GVRAccessibilityItem zoom = new GVRAccessibilityItem(getGVRContext(), mesh, getGVRContext()
                .loadTexture(new GVRAndroidResource(getGVRContext(), R.drawable.zoom)));
        zoom.getTransform().setPosition(positionX, positionY, positionZ);
        zoom.getTransform().setScale(scale, scale, scale);
        zoom.attachEyePointeeHolder();
        this.addSceneObject(zoom);

        GVRAccessibilityItem talkBack = new GVRAccessibilityItem(getGVRContext(), mesh, getGVRContext()
                .loadTexture(new GVRAndroidResource(getGVRContext(), R.drawable.talk_back)));
        talkBack.getTransform().setPosition(positionX, positionY, positionZ);
        talkBack.getTransform().setScale(scale, scale, scale);
        talkBack.attachEyePointeeHolder();
        this.addSceneObject(talkBack);

        GVRAccessibilityItem speech = new GVRAccessibilityItem(getGVRContext(), mesh, getGVRContext()
                .loadTexture(new GVRAndroidResource(getGVRContext(), R.drawable.speech)));
        speech.getTransform().setPosition(positionX, positionY, positionZ);
        speech.getTransform().setScale(scale, scale, scale);
        speech.attachEyePointeeHolder();
        this.addSceneObject(speech);

        GVRAccessibilityItem captions = new GVRAccessibilityItem(getGVRContext(), mesh, getGVRContext()
                .loadTexture(new GVRAndroidResource(getGVRContext(), R.drawable.captions)));
        captions.getTransform().setPosition(positionX, positionY, positionZ);
        captions.getTransform().setScale(scale, scale, scale);
        captions.attachEyePointeeHolder();
        this.addSceneObject(captions);

        GVRAccessibilityItem settings = new GVRAccessibilityItem(getGVRContext(), mesh, getGVRContext()
                .loadTexture(new GVRAndroidResource(getGVRContext(), R.drawable.settings)));
        settings.getTransform().setPosition(positionX, positionY, positionZ);
        settings.getTransform().setScale(scale, scale, scale);
        settings.attachEyePointeeHolder();
        this.addSceneObject(settings);

        float angle = -20;

        invertedColors.getTransform().rotateByAxisWithPivot(-3 * angle, 0, 1, 0, 0, 0, 0);
        zoom.getTransform().rotateByAxisWithPivot(-2 * angle, 0, 1, 0, 0, 0, 0);
        talkBack.getTransform().rotateByAxisWithPivot(-1 * angle, 0, 1, 0, 0, 0, 0);
        speech.getTransform().rotateByAxisWithPivot(0 * angle, 0, 1, 0, 0, 0, 0);
        captions.getTransform().rotateByAxisWithPivot(1 * angle, 0, 1, 0, 0, 0, 0);
        settings.getTransform().rotateByAxisWithPivot(2 * angle, 0, 1, 0, 0, 0, 0);
    }

    private void createButtonBackMainScene() {

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

    private void backToMainScene() {
        GVRAccessibilityMenu menu = new GVRAccessibilityMenu(mGvrContext);
        this.addSceneObject(menu);
    }

    private void createDefaultMenuButton() {
    }

    /**
     * Select and unselect a certain menu item. This serves to activate or
     * deactivate accessibility functions. It is advisable that this method is
     * called after a click on an item, but it can also be called in
     * {@link org.gearvrf.GVRScript#onStep()} </br> It will only work if this
     * scene is the main scene.</br>
     * 
     * <pre>
     * public void onSingleTap(MotionEvent e) {
     *     if (mGVRContext.getMainScene().equals(accessibilityScene)) {
     *         gvrAccessibilityScene.interact();
     *     }
     * </pre>
     */
    public void interact() {
        GVREyePointeeHolder[] eyePointeeHolders = GVRPicker.pickScene(this);
        for (GVREyePointeeHolder gvrEyePointeeHolder : eyePointeeHolders) {
            ((GVRAccessibilityItem) gvrEyePointeeHolder.getOwnerObject()).click();
        }
    }

}
