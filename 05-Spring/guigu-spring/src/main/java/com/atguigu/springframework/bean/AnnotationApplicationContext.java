package com.atguigu.springframework.bean;

import com.atguigu.springframework.anno.Bean;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class AnnotationApplicationContext implements ApplicationContext {

    private final Map<Class, Object> beanFactory = new HashMap<>();
    private final static String rootPaht = "/home/pengbin/IdeaProjects/java-study/05-Spring/guigu-spring/target/classes/";

    public AnnotationApplicationContext(String basePackage) {
        String path = basePackage.replaceAll("\\.", "/");
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(path);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String finalPath = url.getPath();
                // scan package
                loadBean(new File(finalPath));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadBean(File file) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files == null || files.length == 0) return ;
            for (File f : files) {
                if (f.getPath().contains("test-classes")) continue ;
                if (f.isDirectory()) loadBean(f);
                else {
                    String pathWithClass = f.getAbsolutePath().substring(rootPaht.length());
                    if (pathWithClass.contains(".class")) {
                        String fullName = pathWithClass.replaceAll("/", ".").replace(".class", "");
                        Class<?> aClass = Class.forName(fullName);
                        if (!aClass.isInterface()) {
                            Bean bean = aClass.getAnnotation(Bean.class);
                            if (bean != null) {
                                Object newInstance = aClass.getConstructor().newInstance();
                                if (aClass.getInterfaces().length > 0) {
                                    beanFactory.put(aClass.getInterfaces()[0], newInstance);
                                } else {
                                    beanFactory.put(aClass, newInstance);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Object getBean(Class clazz) {
        return beanFactory.get(clazz);
    }
}
