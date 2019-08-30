package com.geekofia.cacheaway.Utils;

public class CleanCommands {

    public static final String CACHE_DALVIK = "rm -rf /data/dalvik-cache | rm -rf /data/cache",
            CACHE = "rm -rf /cache/*",
            CACHE_APP_INTERNAL = "rm -rf /data/data/*/cache",
            CACHE_APP_EXTERNAL = "rm -rf $EXTERNAL_STORAGE/Android/data/*/cache",
            CACHE_APP = CACHE_APP_INTERNAL + " | " + CACHE_APP_EXTERNAL,
            CACHE_TEMP_FILES = "rm -rf /data/local/tmp",
            CACHE_LOGS = "rm -rf /data/log",
            CACHE_LOGGER = "rm -rf /data/logger",
            CACHE_DROP_BOX = "rm -rf /data/system/dropbox",
            CACHE_TOMBSTONE = "rm -rf /data/tombstones",
            CACHE_USAGE_STATS = "rm -rf /data/system/usagestats",
            CACHE_ANR = "rm -rf /data/anr",
            CACHE_LOST_DIR = "rm -rf $EXTERNAL_STORAGE/LOST.DIR",
            CACHE_THUMBNAILS = "rm -rf $EXTERNAL_STORAGE/DCIM/.thumbnails/* | rm -rf $EXTERNAL_STORAGE/DCIM/.thumbnails/.*";

    public static String[] LIST_COMMANDS = {CACHE_DALVIK, CACHE, CACHE_APP, CACHE_TEMP_FILES,
            CACHE_LOGS, CACHE_LOGGER, CACHE_DROP_BOX,
            CACHE_TOMBSTONE, CACHE_USAGE_STATS, CACHE_ANR,
            CACHE_LOST_DIR, CACHE_THUMBNAILS};
}

