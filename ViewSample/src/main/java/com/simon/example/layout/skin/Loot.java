package com.simon.example.layout.skin;

import android.content.Context;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;

import de.mindpipe.android.logging.log4j.LogConfigurator;

/**
 * An internal logger backend by log4j
 * <p/>
 * All log will append to android log system
 *
 * @author Simon Yu
 */
class Loot {
    private static Logger sApplyLogger;
    private static Logger sInflateLogger;
    private static Logger sParseLogger;


    static void configure(Context context) {
        final LogConfigurator logConfigurator = new LogConfigurator();

        logConfigurator.setFileName(context.getFilesDir() + File.separator + "skin_service.log");
        logConfigurator.setRootLevel(Level.DEBUG);
        logConfigurator.setUseLogCatAppender(true);
        logConfigurator.configure();
    }

    synchronized static Logger logApply() {
        if (sApplyLogger == null) {
            sApplyLogger = Logger.getLogger("skin.apply");
        }
        return sApplyLogger;
    }

    synchronized static Logger logInflate() {
        if (sInflateLogger == null) {
            sInflateLogger = Logger.getLogger("skin.inflate");
        }
        return sInflateLogger;
    }

    synchronized static Logger logParse() {
        if (sParseLogger == null) {
            sParseLogger = Logger.getLogger("skin.parse");
        }
        return sParseLogger;
    }
}
