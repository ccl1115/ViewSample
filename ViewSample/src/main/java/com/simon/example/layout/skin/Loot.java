package com.simon.example.layout.skin;

import android.util.Log;

/**
 * An internal logger backend by log4j
 * <p/>
 * All log will append to android log system
 *
 * @author Simon Yu
 */
class Loot {

    static void logApply(String msg) {
        Log.d("skin.apply", msg);
    }

    static void logInflate(String msg) {
        Log.d("skin.inflate", msg);

    }

    static void logParse(String msg) {
        Log.d("skin.parse", msg);
    }
}
