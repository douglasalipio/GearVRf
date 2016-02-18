package org.gearvrf.accessibility;

import java.util.concurrent.Future;

import org.gearvrf.GVRAndroidResource;
import org.gearvrf.GVRContext;
import org.gearvrf.GVRMaterialShaderId;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;

public class GVRAccessiblityObject extends GVRSceneObject {

    private GVRAccessibilityTalkBack mTalkBack;
    private GVRAccessibilityCaptions mCaptions;

    public GVRAccessiblityObject(GVRContext gvrContext, float width, float height, GVRTexture texture, GVRMaterialShaderId shaderId) {
        super(gvrContext, width, height, texture, shaderId);

    }

    public GVRAccessiblityObject(GVRContext gvrContext, float width, float height, GVRTexture texture) {
        super(gvrContext, width, height, texture);

    }

    public GVRAccessiblityObject(GVRContext gvrContext, float width, float height) {
        super(gvrContext, width, height);

    }

    public GVRAccessiblityObject(GVRContext gvrContext, Future<GVRMesh> futureMesh, Future<GVRTexture> futureTexture) {
        super(gvrContext, futureMesh, futureTexture);

    }

    public GVRAccessiblityObject(GVRContext gvrContext, GVRAndroidResource mesh, GVRAndroidResource texture) {
        super(gvrContext, mesh, texture);

    }

    public GVRAccessiblityObject(GVRContext gvrContext, GVRMesh mesh, GVRTexture texture, GVRMaterialShaderId shaderId) {
        super(gvrContext, mesh, texture, shaderId);

    }

    public GVRAccessiblityObject(GVRContext gvrContext, GVRMesh mesh, GVRTexture texture) {
        super(gvrContext, mesh, texture);

    }

    public GVRAccessiblityObject(GVRContext gvrContext, GVRMesh mesh) {
        super(gvrContext, mesh);

    }

    public GVRAccessiblityObject(GVRContext gvrContext) {
        super(gvrContext);

    }

    public GVRAccessibilityTalkBack getTalkBack() {
        return mTalkBack;
    }

    public void setTalkBack(GVRAccessibilityTalkBack mTalkBack) {
        this.mTalkBack = mTalkBack;
    }

    public GVRAccessibilityCaptions getCaptions() {
        return mCaptions;
    }

    public void setCaptions(GVRAccessibilityCaptions captions) {
        this.mCaptions = captions;
    }

}
