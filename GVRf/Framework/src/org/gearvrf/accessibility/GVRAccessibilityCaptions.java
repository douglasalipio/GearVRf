/*
 * Copyright [2016] [SIDIA Samsung Instituto de Informatica da Amazonia]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gearvrf.accessibility;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;

import org.gearvrf.GVRContext;
import org.gearvrf.GVRRenderData.GVRRenderMaskBit;
import org.gearvrf.periodic.GVRPeriodicEngine.PeriodicEvent;
import org.gearvrf.scene_objects.GVRViewSceneObject;
import org.gearvrf.scene_objects.view.GVRTextView;

/**
 * Object that generates subtitle information. It is created in a fixed
 * position, having the camera rig as its parent. It is shown indefinitely
 * unless the user sets a certain amount of time.
 */
public class GVRAccessibilityCaptions {
    private static final float VIEW_POSITION_Z = -.8f; // -.3f
    private static final float VIEW_POSITION_Y = .3333333f * VIEW_POSITION_Z; // -.1f
    private static final float VIEW_POSITION_X = 0f;
    private static final float VIEW_WIDTH = -.7666666f * VIEW_POSITION_Z; // .23f
    private static final float VIEW_HEIGHT = -.0416666f * VIEW_POSITION_Z; // .10f

    private static GVRAccessibilityCaptions sInstance;

    public static final float INFINITE_DURATION = -1;
    private float duration = INFINITE_DURATION;
    private PeriodicEvent event;

    private GVRContext mGvrContext;
    private GVRTextView mTextViewR;
    private GVRTextView mTextViewL;
    private GVRViewSceneObject mViewSceneObjectR;
    private GVRViewSceneObject mViewSceneObjectL;
    private boolean isActive = true;

    /**
     * Creates a single instance of captions
     * 
     * @param gvrContext
     * @return
     */
    public static synchronized GVRAccessibilityCaptions getInstance(GVRContext gvrContext) {
        if (sInstance == null) {
            sInstance = new GVRAccessibilityCaptions(gvrContext);
        }
        return sInstance;
    }

    private GVRAccessibilityCaptions(GVRContext gvrContext) {
        mGvrContext = gvrContext;
        mGvrContext.getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
                mTextViewR = new GVRTextView(mGvrContext.getActivity());
                configureGVRTextView(mTextViewR);

                mTextViewL = new GVRTextView(mGvrContext.getActivity());
                configureGVRTextView(mTextViewL);

                mGvrContext.runOnGlThread(new Runnable() {

                    @Override
                    public void run() {
                        mViewSceneObjectR = new GVRViewSceneObject(mGvrContext, mTextViewR, VIEW_WIDTH, VIEW_HEIGHT);
                        mViewSceneObjectR.getRenderData().setRenderMask(GVRRenderMaskBit.Right);
                        mViewSceneObjectR.getTransform().setPosition(VIEW_POSITION_X, VIEW_POSITION_Y, VIEW_POSITION_Z);

                        mViewSceneObjectL = new GVRViewSceneObject(mGvrContext, mTextViewL, VIEW_WIDTH, VIEW_HEIGHT);
                        mViewSceneObjectL.getRenderData().setRenderMask(GVRRenderMaskBit.Left);
                        mViewSceneObjectL.getTransform().setPosition(VIEW_POSITION_X, VIEW_POSITION_Y, VIEW_POSITION_Z);
                    }
                });
            }

            private void configureGVRTextView(final GVRTextView textView) {
                textView.setBackgroundColor(Color.BLACK);
                textView.setTextColor(Color.WHITE);

                textView.setTextSize(30);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                textView.setPadding(0, -90, 0, 0);
                textView.setTypeface(Typeface.SANS_SERIF, Typeface.BOLD);
            }

        });

    }

    /**
     * @return current subtitles
     */
    public CharSequence getText() {
        return mTextViewR.getText();
    }

    /**
     * Set new captions
     * 
     * @param text
     */
    public void setText(final CharSequence text) {
        if (isActive) {
            mGvrContext.getMainScene().getMainCameraRig().getRightCamera().addChildObject(mViewSceneObjectR);
            mGvrContext.getMainScene().getMainCameraRig().getLeftCamera().addChildObject(mViewSceneObjectL);

            removeSubtitlesAfterDuration();

            mGvrContext.getActivity().runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    updateText(text, mTextViewR, mViewSceneObjectR);
                    updateText(text, mTextViewL, mViewSceneObjectL);
                }

                private void updateText(final CharSequence text, final GVRTextView textView, final GVRViewSceneObject viewSceneObject) {
                    textView.setText(text);
                    final int lines = textView.getLineCount();
                    mGvrContext.runOnGlThread(new Runnable() {

                        @Override
                        public void run() {
                            viewSceneObject.getRenderData().setMesh(mGvrContext.createQuad(VIEW_WIDTH, VIEW_HEIGHT * lines));
                            textView.post(new Runnable() {

                                @Override
                                public void run() {
                                    // workaround because sometimes
                                    // textView.getLineCount() returns 0
                                    final int currentLines = textView.getLineCount();
                                    int correctLines = Math.max(lines, currentLines);
                                    if (correctLines > 0) {
                                        textView.setTextSize(240 / correctLines);
                                        textView.setTextScaleX(correctLines / 8f);
                                    }
                                }

                            });
                        }
                    });
                }
            });
        }
    }

    private void removeSubtitlesAfterDuration() {
        if (event != null) {
            event.cancel();
        }
        if (duration != INFINITE_DURATION) {
            event = mGvrContext.getPeriodicEngine().runAfter(new Runnable() {

                @Override
                public void run() {
                    mGvrContext.getMainScene().getMainCameraRig().getRightCamera().removeChildObject(mViewSceneObjectR);
                    mGvrContext.getMainScene().getMainCameraRig().getLeftCamera().removeChildObject(mViewSceneObjectL);
                    event.cancel();
                    event = null;
                }
            }, duration);
        }
    }

    /**
     * Set how much time captions will be shown. The default value is {@link #INFINITE_DURATION}
     * 
     * @param duration
     */

    public void setSubtitleDuration(float duration) {
        this.duration = duration;
        removeSubtitlesAfterDuration();
    }

    /**
     * Get captions' total duration
     * 
     * @return
     */
    public float getSubtitleDuration() {
        return duration;
    }

    /**
     * Show captions for given duration
     */
    public void show() {
        isActive = true;
        setText(getText());
    }

    /**
     * Immediately hide captions
     */
    public void hide() {
        isActive = false;
        if (event != null) {
            event.cancel();
        }
        mGvrContext.getMainScene().getMainCameraRig().getRightCamera().removeChildObject(mViewSceneObjectR);
        mGvrContext.getMainScene().getMainCameraRig().getLeftCamera().removeChildObject(mViewSceneObjectL);
    }

}
