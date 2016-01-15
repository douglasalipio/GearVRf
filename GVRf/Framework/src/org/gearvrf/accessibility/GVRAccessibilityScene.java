
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
 * It enables a menu with the following features each one having it's own
 * button: <br/>
 * <li>
 * {@link GVRAccessibilityTextSpeech} <li>
 * {@link GVRAccessibilityInvertedColors} <li>
 * {@link GVRAccessibilitySettings}
 * <li>
 * {@link GVRAccessibilityTalkBack} <li>
 * {@link GVRAccessibilityCaptions},
 * <li>
 * {@link GVRAccessibilityZoom}<br/>
 * </br> {@link GVRAccessibilityScene} instantiates a sky box and menu both
 * disabled and a button to add and remove them from the {@link GVRSceneObject}.
 * <br/>
 * <br/>
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
        GVRMesh mesh = getGVRContext().createQuad(3, 4.873f);
        GVRAccessibilityItem invertedColors = new GVRAccessibilityItem(getGVRContext(), mesh, getGVRContext()
                .loadTexture(new GVRAndroidResource(getGVRContext(), R.drawable.inverted_colors_off)));
        invertedColors.getTransform().setPositionZ(-10f);
        this.addSceneObject(invertedColors);

        GVRAccessibilityItem zoom = new GVRAccessibilityItem(getGVRContext(), mesh, getGVRContext()
                .loadTexture(new GVRAndroidResource(getGVRContext(), R.drawable.zoom_off)));
        zoom.getTransform().setPositionZ(-10f);
        this.addSceneObject(zoom);

        GVRAccessibilityItem talkBack = new GVRAccessibilityItem(getGVRContext(), mesh, getGVRContext()
                .loadTexture(new GVRAndroidResource(getGVRContext(), R.drawable.talk_back_off)));
        talkBack.getTransform().setPositionZ(-10f);
        this.addSceneObject(talkBack);

        GVRAccessibilityItem speech = new GVRAccessibilityItem(getGVRContext(), mesh, getGVRContext()
                .loadTexture(new GVRAndroidResource(getGVRContext(), R.drawable.speech_off)));
        speech.getTransform().setPositionZ(-10f);
        this.addSceneObject(speech);

        GVRAccessibilityItem captions = new GVRAccessibilityItem(getGVRContext(), mesh, getGVRContext()
                .loadTexture(new GVRAndroidResource(getGVRContext(), R.drawable.captions_off)));
        captions.getTransform().setPositionZ(-10f);
        this.addSceneObject(captions);

        GVRAccessibilityItem settings = new GVRAccessibilityItem(getGVRContext(), mesh, getGVRContext()
                .loadTexture(new GVRAndroidResource(getGVRContext(), R.drawable.settings_off)));
        settings.getTransform().setPositionZ(-10f);
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
        mGvrContext.setMainScene(mGvrContext.getMainScene());
    }

    private void createDefaultMenuButton() {

    }

    private void addMenuButtonToMainScene() {

    }
}
