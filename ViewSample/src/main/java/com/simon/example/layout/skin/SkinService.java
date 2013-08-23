package com.simon.example.layout.skin;

import android.app.Activity;
import android.content.Context;

import com.simon.example.layout.skin.impl.DayHookSet;

/**
 * 皮肤服务，替代SkinManager
 * @author yulu02
 */
public class SkinService {

    public static SkinLayoutFactory sSkinLayoutFactory;

    public synchronized static SkinLayoutFactory getFactory(Context context) {
        if (sSkinLayoutFactory == null) {
            sSkinLayoutFactory = new SkinLayoutFactory(context);
            sSkinLayoutFactory.setHookerSet(new DayHookSet());
        }
        return sSkinLayoutFactory;
    }

    public static void applyTheme(Activity activity) {
        
    }
}
