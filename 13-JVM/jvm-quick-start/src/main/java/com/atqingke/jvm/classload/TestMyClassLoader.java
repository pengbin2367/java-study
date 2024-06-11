package com.atqingke.jvm.classload;

import java.io.FileInputStream;
import java.lang.reflect.Method;

public class TestMyClassLoader {

    static class MyClassLoader extends ClassLoader {
        private static final String basePath = "/home/pengbin/tmp";

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] bytes = loadBytes(name);
            return defineClass(name, bytes, 0, bytes.length);
        }

        private byte[] loadBytes(String name) {
            name = name.replace('.', '/').concat(".class");
            String finalPath = basePath + "/" + name;
            try {
                FileInputStream fileInputStream = new FileInputStream(finalPath);
                int len = fileInputStream.available();
                byte[] bytes = new byte[len];
                fileInputStream.read(bytes);
                fileInputStream.close();
                return bytes;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> aClass = myClassLoader.loadClass("com.atqingke.jvm.classload.User");
        System.out.println("加载User.class的类加载器:" + aClass.getClassLoader().getClass().getName());
        Object user = aClass.newInstance();
        Method test = aClass.getDeclaredMethod("test", null);
        test.invoke(user, null);
    }
}
