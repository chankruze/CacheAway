package com.geekofia.cacheaway.Utils;

public class CleanCommands {

    public static final String CACHE_DALVIK = "/data/dalvik-cache /data/cache",
            CACHE = "/cache/*",
            CACHE_APP_INTERNAL = "/data/data/*/cache",
            CACHE_APP_EXTERNAL = "$EXTERNAL_STORAGE/Android/data/*/cache",
            CACHE_APP = CACHE_APP_INTERNAL + " " + CACHE_APP_EXTERNAL,
            CACHE_TEMP_FILES = "/data/local/tmp",
            CACHE_LOGS = "/data/log",
            CACHE_LOGGER = "/data/logger",
            CACHE_DROP_BOX = "/data/system/dropbox",
            CACHE_TOMBSTONE = "/data/tombstones",
            CACHE_USAGE_STATS = "/data/system/usagestats",
            CACHE_ANR = "/data/anr",
            CACHE_LOST_DIR = "$EXTERNAL_STORAGE/LOST.DIR",
            CACHE_THUMBNAILS = "$EXTERNAL_STORAGE/DCIM/.thumbnails/* $EXTERNAL_STORAGE/DCIM/.thumbnails/.*";

    public static String[] LIST_COMMANDS = {CACHE_DALVIK, CACHE, CACHE_APP, CACHE_TEMP_FILES,
            CACHE_LOGS, CACHE_LOGGER, CACHE_DROP_BOX,
            CACHE_TOMBSTONE, CACHE_USAGE_STATS, CACHE_ANR,
            CACHE_LOST_DIR, CACHE_THUMBNAILS};
}

