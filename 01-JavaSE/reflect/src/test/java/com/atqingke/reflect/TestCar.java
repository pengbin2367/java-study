package com.atqingke.reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TestCar {

    // 1. 获取Class对象多种方式
    @Test
    public void testGetClassObject() throws Exception {
        Class<Car> carClass = Car.class;
        Class<? extends Car> aClass = new Car().getClass();
        Class<?> aClass1 = Class.forName("com.atqingke.reflect.Car");
        Car car = carClass.getDeclaredConstructor().newInstance();
        System.out.println(car);
    }

    // 2. 获取构造方法
    @Test
    public void testGetConstructor() throws Exception {
        Class<Car> carClass = Car.class;
        Constructor<?>[] constructors = carClass.getConstructors();
        // Declared 可以获取所有构造，包括私有的
        // carClass.getDeclaredConstructors();
        Arrays.stream(constructors).forEach(System.out::println);

        Constructor<Car> constructor = carClass.getConstructor(String.class, String.class, String.class);
        Car car = constructor.newInstance("小米", "Max", "极光紫");
        System.out.println("公共汽车 " + car);

        Constructor<Car> declaredConstructor = carClass.getDeclaredConstructor(String.class, String.class);
        declaredConstructor.setAccessible(true);
        Car car1 = declaredConstructor.newInstance("小米", "Pro");
        System.out.println("私有汽车 " + car1);
    }

    // 3. 获取属性
    @Test
    public void testGetField() throws Exception {
        Class<Car> carClass = Car.class;
        Car car = carClass.getDeclaredConstructor().newInstance();
        Field[] fields = carClass.getDeclaredFields();
        Arrays.stream(fields).forEach(field -> {
            field.setAccessible(true);
            if ("brand".equals(field.getName())) {
                try {
                    field.set(car, "小米");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(car);
        });
    }

    // 4. 获取方法
    @Test
    public void testGetMethod() throws Exception {
        Car car = new Car("小米", "Max", "极光紫");
        Class<? extends Car> aClass = car.getClass();
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            if ("toString".equals(method.getName())) {
                String toString = (String) method.invoke(car);
                System.out.println(toString);
            }
            if ("startCar".equals(method.getName())) {
                method.setAccessible(true);
                method.invoke(car);
            }
        }
    }
}
