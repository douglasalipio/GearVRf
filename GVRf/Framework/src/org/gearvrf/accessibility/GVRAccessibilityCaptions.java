
package org.gearvrf.accessibility;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;
import org.gearvrf.scene_objects.view.GVRTextView;

/**
 * Object that will receive subtitle information. It will be created in a fixed
 * position, will have camera rig as its parent and will appear for a fixed
 * amount of time when requested by {@link GVRSceneObject}.
 */
public class GVRAccessibilityCaptions extends GVRSceneObject {

    private GVRTextView subtitle;
    private float captions_x;
    private float captions_y;
    private float captions_z;
    private float showTime;

    public GVRAccessibilityCaptions(GVRContext gvrContext, float width, float height, GVRTexture texture) {
        super(gvrContext, width, height, texture);
    }

    private void createCaptions() {

    }

    public void setSubtitle(String text) {
        this.subtitle.setText(text);
    }

    private void turnOn() {

    }

    private void turnOff() {

    }

}
