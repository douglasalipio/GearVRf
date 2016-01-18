
package org.gearvrf.accessibility;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRMesh;
import org.gearvrf.GVRSceneObject;
import org.gearvrf.GVRTexture;
import org.gearvrf.animation.GVRAnimation;
import org.gearvrf.animation.GVROnFinish;
import org.gearvrf.animation.GVRRelativeMotionAnimation;
import org.gearvrf.animation.GVRRotationByAxisAnimation;

public class GVRAccessibilityItem extends GVRSceneObject {
    private boolean isActive = false;
    private boolean isAnimating = false;
    private static final float duration = 0.35f;

    public GVRAccessibilityItem(GVRContext gvrContext, GVRMesh mesh, GVRTexture texture) {
        super(gvrContext, mesh, texture);
    }

    public void click() {
        if (!isAnimating) {
            isAnimating = true;
            if (isActive) {
                new GVRRelativeMotionAnimation(this, duration, 0, 0, -2).start(getGVRContext().getAnimationEngine()).setOnFinish(new GVROnFinish() {

                    @Override
                    public void finished(GVRAnimation animation) {
                        new GVRRotationByAxisAnimation(GVRAccessibilityItem.this, duration, 180, 0, 1, 0).start(getGVRContext().getAnimationEngine())
                                .setOnFinish(new GVROnFinish() {

                                    @Override
                                    public void finished(GVRAnimation animation) {
                                        new GVRRelativeMotionAnimation(GVRAccessibilityItem.this, duration, 0, 0, 2).start(
                                                getGVRContext().getAnimationEngine()).setOnFinish(new GVROnFinish() {

                                            @Override
                                            public void finished(GVRAnimation animation) {
                                                isAnimating = false;
                                            }
                                        });
                                    }
                                });
                    }
                });
            } else {
                new GVRRelativeMotionAnimation(this, duration, 0, 0, -2).start(getGVRContext().getAnimationEngine()).setOnFinish(new GVROnFinish() {

                    @Override
                    public void finished(GVRAnimation animation) {
                        new GVRRotationByAxisAnimation(GVRAccessibilityItem.this, duration, -180, 0, 1, 0)
                                .start(getGVRContext().getAnimationEngine())
                                .setOnFinish(new GVROnFinish() {

                                    @Override
                                    public void finished(GVRAnimation animation) {
                                        new GVRRelativeMotionAnimation(GVRAccessibilityItem.this, duration, 0, 0, 2).start(
                                                getGVRContext().getAnimationEngine()).setOnFinish(new GVROnFinish() {

                                            @Override
                                            public void finished(GVRAnimation animation) {
                                                isAnimating = false;
                                            }
                                        });
                                    }
                                });
                    }
                });
            }
            isActive = !isActive;
        }
    }

}
