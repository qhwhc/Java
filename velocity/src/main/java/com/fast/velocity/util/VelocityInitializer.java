package com.fast.velocity.util;

import org.apache.velocity.app.Velocity;

import java.util.Properties;

public class VelocityInitializer {
    public static void initVelocity() {
        Properties p = new Properties();
        try {
            p.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            p.setProperty("ISO-8859-1", "UTF-8");
            p.setProperty("output.encoding", "UTF-8");
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
