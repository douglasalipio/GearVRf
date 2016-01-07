
package org.gearvrf.accessibility;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;
import org.gearvrf.R;
import org.gearvrf.scene_objects.GVRViewSceneObject;
import org.gearvrf.scene_objects.view.GVRTextView;

final class GVRAccessibilityButton extends GVRSceneObject {

    private GVRSceneObject mIconButton;
    private GVRViewSceneObject mPlaceHoldText;
    private GVRTextView mTextView;

    public GVRAccessibilityButton(final GVRContext gvrContext, GVRMesh mesh, GVRTexture texture) {
        super(gvrContext, mesh, texture);

        GVRTexture textureBackground = gvrContext.loadTexture(new GVRAndroidResource(gvrContext, R.drawable.ic_speech_off));
        mIconButton = new GVRSceneObject(gvrContext, gvrContext.createQuad(.8f, .8f), textureBackground);
        mIconButton.getRenderData().setRenderingOrder(1);
        addChildObject(mIconButton);

        gvrContext.getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {

                mTextView = new GVRTextView(gvrContext.getActivity());
                gvrContext.runOnGlThread(new Runnable() {
                    @Override
                    public void run() {
                        mPlaceHoldText = new GVRViewSceneObject(gvrContext, mTextView, gvrContext.createQuad(1.5f, 1f));
                        mPlaceHoldText.getRenderData().setRenderingOrder(2);
                        mPlaceHoldText.getTransform().setPositionY(-1);
                        addChildObject(mPlaceHoldText);
                    }
                });
            }
        });

    }

    public GVRTextView getTextView() {
        return this.mTextView;
    }

    public GVRSceneObject getIcon() {
        return this.mIconButton;
    }
}
