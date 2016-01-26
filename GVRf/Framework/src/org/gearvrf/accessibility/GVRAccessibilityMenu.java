
package org.gearvrf.accessibility;

import java.util.ArrayList;
import java.util.List;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;
import org.gearvrf.R;
import org.gearvrf.scene_objects.GVRViewSceneObject;
import org.gearvrf.scene_objects.view.GVRTextView;

public class GVRAccessibilityMenu extends GVRSceneObject {

    private List<GVRAccessibilityMenuItem> menuItems;
    private GVRContext mGvrContext;
    private GVRViewSceneObject placeHolderBack;
    private GVRTextView backText;
    private GVRTexture mSpacerTexture;
    private GVRMesh mSlotMesh;
    private GVRTexture mSlotTexture;

    public GVRAccessibilityMenu(GVRContext gvrContext) {
        super(gvrContext);

        mGvrContext = gvrContext;
        mSlotMesh = mGvrContext.loadMesh(new GVRAndroidResource(mGvrContext, R.raw.circle_menu));
        mSlotTexture = mGvrContext.loadTexture(new GVRAndroidResource(mGvrContext, R.drawable.circle_normal));
        mSpacerTexture = mGvrContext.loadTexture(new GVRAndroidResource(mGvrContext, R.drawable.circle_normal_alpha));
        menuItems = new ArrayList<GVRAccessibilityMenuItem>();

        createAllButtons();
        createMenuItems();

    }

    private void createMenuItems() {
        for (int i = 0; i < 8; i++) {
            float degreeNormal = 360.0f * i / (8);
            menuItems.get(i).getTransform().setPosition(0, -1f, 0);
            menuItems.get(i).getTransform().rotateByAxis(degreeNormal, 0, 1, 0);
            addChildObject(menuItems.get(i));
        }
    }

    private void createAllButtons() {
        createBackButton();
        createCaptionsButton();
        createGazeButton();
        createInvertedColorsButton();
        createSpeechButton();
        createTalkBackButton();
        createZoomButton();
        createDefaultItem();
    }

    private void createBackIcon() {
        GVRSceneObject iconButton = new GVRSceneObject(mGvrContext, mGvrContext.createQuad(.2f, .3f),
                mGvrContext.loadTexture(new GVRAndroidResource(mGvrContext, R.drawable.back_ico)));
        iconButton.getTransform().setPosition(-.4f, -.99f, -.7f);
        iconButton.getTransform().rotateByAxis(-90, 1, 0, 0);
        iconButton.getTransform().rotateByAxis(35, 0, 1, 0);
        addChildObject(iconButton);
    }

    private void createBackButton() {
        GVRAccessibilityMenuItem backButton = new GVRAccessibilityMenuItem(mGvrContext, mSlotMesh, mSlotTexture);
        backButton.getRenderData().getMaterial().setMainTexture(mSpacerTexture);
        backButton.setOnAction(new GVRAccessibilityOnAction() {

            @Override
            public void setOnAction() {
                getGVRContext().setMainScene(GVRAccessibilityManager.mMainSceneApplication);
            }
        });

        menuItems.add(backButton);
    }

    private void createCaptionsButton() {
        GVRAccessibilityMenuItem captionsButton = new GVRAccessibilityMenuItem(mGvrContext, mSlotMesh, mSlotTexture);
        captionsButton.getRenderData().getMaterial().setMainTexture(mSpacerTexture);
        captionsButton.setOnAction(new GVRAccessibilityOnAction() {

            @Override
            public void setOnAction() {
            }
        });

        menuItems.add(captionsButton);
    }

    private void createGazeButton() {
        GVRAccessibilityMenuItem gazeButton = new GVRAccessibilityMenuItem(mGvrContext, mSlotMesh, mSlotTexture);
        gazeButton.getRenderData().getMaterial().setMainTexture(mSpacerTexture);
        gazeButton.setOnAction(new GVRAccessibilityOnAction() {

            @Override
            public void setOnAction() {
            }
        });

        menuItems.add(gazeButton);
    }

    private void createInvertedColorsButton() {
        GVRAccessibilityMenuItem InvertedColorsButton = new GVRAccessibilityMenuItem(mGvrContext, mSlotMesh, mSlotTexture);
        InvertedColorsButton.getRenderData().getMaterial().setMainTexture(mSpacerTexture);
        InvertedColorsButton.setOnAction(new GVRAccessibilityOnAction() {

            @Override
            public void setOnAction() {
            }
        });

        menuItems.add(InvertedColorsButton);
    }

    private void createZoomButton() {
        GVRAccessibilityMenuItem zoomButton = new GVRAccessibilityMenuItem(mGvrContext, mSlotMesh, mSlotTexture);
        zoomButton.getRenderData().getMaterial().setMainTexture(mSpacerTexture);
        zoomButton.setOnAction(new GVRAccessibilityOnAction() {

            @Override
            public void setOnAction() {
            }
        });

        menuItems.add(zoomButton);
    }

    private void createSpeechButton() {
        GVRAccessibilityMenuItem SpeechButton = new GVRAccessibilityMenuItem(mGvrContext, mSlotMesh, mSlotTexture);
        SpeechButton.getRenderData().getMaterial().setMainTexture(mSpacerTexture);
        SpeechButton.setOnAction(new GVRAccessibilityOnAction() {

            @Override
            public void setOnAction() {
            }
        });

        menuItems.add(SpeechButton);
    }

    private void createTalkBackButton() {
        GVRAccessibilityMenuItem talkBackButton = new GVRAccessibilityMenuItem(mGvrContext, mSlotMesh, mSlotTexture);
        talkBackButton.getRenderData().getMaterial().setMainTexture(mSpacerTexture);
        talkBackButton.setOnAction(new GVRAccessibilityOnAction() {

            @Override
            public void setOnAction() {
            }
        });

        menuItems.add(talkBackButton);
    }

    private void createDefaultItem() {
        GVRAccessibilityMenuItem defaultItem = new GVRAccessibilityMenuItem(mGvrContext, mSlotMesh, mSlotTexture);
        defaultItem.getRenderData().getMaterial().setMainTexture(mSpacerTexture);
        menuItems.add(defaultItem);
    }

}
