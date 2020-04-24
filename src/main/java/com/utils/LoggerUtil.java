package com.utils;

import org.slf4j.LoggerFactory;

public class LoggerUtil {

    private static org.slf4j.Logger logger = getLogger(org.slf4j.Logger.class);

    public static org.slf4j.Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    public static void debug(String className, String mothedName) {
        logger.debug("class:" + className + ";mothed" + mothedName);
    }

    public static void info(String className, String mothedName) {
        logger.info("className:" + className + ";mothedName:" + mothedName);
    }

    public static void error(String className, String mothedName) {
        logger.error("className:" + className + ";mothedName:" + mothedName);
    }
}
