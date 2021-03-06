/* Copyright 2015 Samsung Electronics Co., LTD
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

package org.gearvrf.io;

import org.gearvrf.GVRCursorController;

import android.hardware.input.InputManager;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * This class represents an internal {@link GVRCursorController}. It makes use
 * of the {@link GVRInputManager} to get all the input devices attached to the
 * android device.
 * 
 * Android devices that report to the {@link InputManager} can make use of this
 * class.
 * 
 */
abstract class GVRBaseController extends GVRCursorController {

    public GVRBaseController(GVRCursorType cursorType) {
        super(cursorType);
    }

    abstract boolean dispatchKeyEvent(KeyEvent event);

    abstract boolean dispatchMotionEvent(MotionEvent event);
}