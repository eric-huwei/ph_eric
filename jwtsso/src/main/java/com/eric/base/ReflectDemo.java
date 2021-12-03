package com.eric.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @DESCIRPTION  SPRING IOC 反射+工厂
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/12/3 2:08 PM
 */
public class ReflectDemo {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
//        Fruit fruit = Factory.getInstance("Orange");
        Fruit fruit = Factory.getInstance("com.eric.base.Apple");
        fruit.eat();

        Class clazz = Class.forName("com.eric.base.Orange");
        Fruit fruit1 = (Fruit) clazz.newInstance();
        Method eatEx = clazz.getDeclaredMethod("eatEx");
        eatEx.setAccessible(true);
        eatEx.invoke(fruit1);
    }
}

class Factory {
    public static Fruit getInstance(String className) {
        Fruit fruit = null;
        try {
            fruit = (Fruit) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fruit;
    }
    /*public static Fruit getInstance(String fruitName) {
        Fruit fruit = null;

        if ("Apple".equals(fruitName)) {
            fruit = new Apple();
        }
        if ("Orange".equals(fruitName)) {
            fruit = new Orange();
        }

        return fruit;
    }*/
}

interface Fruit {
    void eat();
}

class Apple implements Fruit {
    @Override
    public void eat() {
        System.out.println("Apple Eat");
    }
}

class Orange implements Fruit {

    private void eatEx() {
        System.out.println("private Orange Eat");
    }

    @Override
    public void eat() {
        System.out.println("Orange Eat");
    }
}