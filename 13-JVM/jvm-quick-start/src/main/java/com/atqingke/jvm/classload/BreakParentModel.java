package com.atqingke.jvm.classload;

import java.io.FileInputStream;
import java.lang.reflect.Method;

public class BreakParentModel {
    static class MyClassLoader extends ClassLoader {
        private String classPath;

        public MyClassLoader(String classPath) {
            this.classPath = classPath;
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {

            byte[] bytes = loadByte(name);

            return defineClass(name, bytes, 0, bytes.length);
        }

        private byte[] loadByte(String name) {
            name = name.replaceAll("\\.", "/");
            try {
                FileInputStream is = new FileInputStream(classPath + "/" + name + ".class");
                int length = is.available();
                byte[] bytes = new byte[length];
                is.read(bytes);
                is.close();
                return bytes;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException {
            synchronized (getClassLoadingLock(name)) {
                // First, check if the class has already been loaded
                Class<?> c = findLoadedClass(name);
                if (c == null) {
                    long t0 = System.nanoTime();


                    if (c == null) {
                        // If still not found, then invoke findClass in order
                        // to find the class.
                        long t1 = System.nanoTime();
                        if (!name.startsWith("com.atqingke.jvm.classload.User")) {
                            c = this.getParent().loadClass(name);
                        }else{

                            c = findClass(name);
                        }

                        // this is the defining class loader; record the stats
                        sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                        sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                        sun.misc.PerfCounter.getFindClasses().increment();
                    }
                }
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            }
        }

    }

    public static void main(String[] args) throws Exception {
        BreakParentModel.MyClassLoader myClassLoader = new BreakParentModel.MyClassLoader("/home/pengbin/tmp");
        Class<?> clazz = myClassLoader.loadClass("com.atqingke.jvm.classload.User");
        Object o = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("test", null);
        method.invoke(o, null);
        System.out.println(clazz.getClassLoader().getClass().getName());

    }
}
