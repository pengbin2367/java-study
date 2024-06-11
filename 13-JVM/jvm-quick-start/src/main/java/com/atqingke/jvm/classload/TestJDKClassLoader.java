package com.atqingke.jvm.classload;

import sun.misc.Launcher;

import java.net.URL;

public class TestJDKClassLoader {

    public static void main(String[] args) {
        System.out.println(String.class.getClassLoader());
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader());
        System.out.println(TestJDKClassLoader.class.getClassLoader());

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        ClassLoader extClassloader = systemClassLoader.getParent();
        ClassLoader bootstrap = extClassloader.getParent();
        System.out.println(bootstrap);
        System.out.println(extClassloader);
        System.out.println();
        System.out.println("bootstrap加载以下文件");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL);
        }
        System.out.println("extClassLoader加载以下文件");
        System.out.println(System.getProperty("java.ext.dirs"));

        System.out.println("appClassloader加载以下文件");
        System.out.println(System.getProperty("java.class.path"));
    }
}
