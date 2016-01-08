
package org.gearvrf.accessibility;

import java.security.InvalidParameterException;
import java.util.Locale;

import org.gearvrf.utility.Log;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;

/**
 * {@link GVRAccessibilityTextSpeech} responsible for converting the value from
 * a string variable to audio.
 */
public class GVRAccessibilityTextSpeech {

    private TextToSpeech mTextToSpeech;
    private Locale mLocale;
    private Context mContext;
    private String mText;
    private static final String TAG = Log.tag(GVRAccessibilityTextSpeech.class);

    /**
     * @param locale target language
     * @param context Android context
     * @param text to be converted
     */
    public GVRAccessibilityTextSpeech(Locale locale, Context context, String text) {
        mText = text;
        mLocale = locale;
        mContext = context;
        init();
    }

    /**
     * Inicialize {@code TextToSpeech} and treats not supported languages
     * exceptions setting a default language.
     */
    private void init() {

        mTextToSpeech = new TextToSpeech(mContext, new OnInitListener() {

            @Override
            public void onInit(int status) {

                if (status == TextToSpeech.SUCCESS) {
                    int result = mTextToSpeech.setLanguage(mLocale);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e(TAG, "This language is not supported - " + "Country:"
                                + mLocale.getCountry()
                                + " - Language:" + mLocale.getLanguage());

                        throw new InvalidParameterException("This language is not supported - "
                                + "Country:" + mLocale.getCountry()
                                + " - Language:" + mLocale.getLanguage());
                    }
                }
            }
        });

    }

    /**
     * @return
     */
    public Locale getLocale() {
        return mLocale;
    }

    /**
     * @param mLocale
     */
    public void setLocale(Locale mLocale) {
        this.mLocale = mLocale;
        init();
    }

    /**
     * @param text
     */
    public void speak() {
        mTextToSpeech.speak(mText, TextToSpeech.QUEUE_FLUSH, null);
    }

}
