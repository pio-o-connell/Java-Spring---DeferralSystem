package com.grouptwo.controllers;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;

import javax.servlet.ServletException;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class HerokuMain {
    
    private static int getPort() {
        String port = System.getenv("PORT");
        if (port == null || port.isEmpty()) {
            return 8080;
        }
        return Integer.parseInt(port);
    }
    
    public static void main(String[] args) throws LifecycleException, URISyntaxException, ServletException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(getPort());
        tomcat.getConnector();
        
        String webappDirLocation = "src/main/webapp/";
        File webappDir = new File(webappDirLocation);
        
        // If not found in src/main/webapp, try to find it in target or current directory
        if (!webappDir.exists()) {
            webappDir = new File("target/SpringWebProject");
            if (!webappDir.exists()) {
                webappDir = new File("webapp");
            }
            if (!webappDir.exists()) {
                // Try to find it relative to the class location
                try {
                    CodeSource codeSource = HerokuMain.class.getProtectionDomain().getCodeSource();
                    if (codeSource != null) {
                        URL location = codeSource.getLocation();
                        File rootPath = new File(location.toURI().getPath()).getParentFile();
                        webappDir = new File(rootPath, "webapp");
                        if (!webappDir.exists()) {
                            webappDir = new File(rootPath.getParentFile(), "webapp");
                        }
                    }
                } catch (Exception e) {
                    // Ignore
                }
            }
        }
        
        if (!webappDir.exists()) {
            throw new RuntimeException("Webapp directory not found. Tried: " + webappDir.getAbsolutePath());
        }
        
        Context ctx = tomcat.addWebapp("", webappDir.getAbsolutePath());
        
        // Enable JSP compilation
        StandardRoot resources = new StandardRoot(ctx);
        File additionWebInfClasses = new File("target/classes");
        if (additionWebInfClasses.exists()) {
            resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                    additionWebInfClasses.getAbsolutePath(), "/"));
        }
        ctx.setResources(resources);
        
        tomcat.start();
        System.out.println("Tomcat started on port: " + getPort());
        System.out.println("Webapp directory: " + webappDir.getAbsolutePath());
        tomcat.getServer().await();
    }
}

