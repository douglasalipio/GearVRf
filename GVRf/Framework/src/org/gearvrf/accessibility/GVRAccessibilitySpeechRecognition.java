
package org.gearvrf.accessibility;

/**
 * This class initializes Speech Recognizer, capture user voice, convert it to
 * text and treats it as a command by comparing to a list of predefined text
 * commands.
 */
public class GVRAccessibilitySpeechRecognition {

    private GVRAccessibilityItem mAccessibilityItem;

    public void setAccessibilityItem(GVRAccessibilityItem accessibilityItem) {
        mAccessibilityItem = accessibilityItem;
    }
}
