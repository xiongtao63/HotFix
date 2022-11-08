package com.enjoy.patch;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 反射工具类
 */
public class SharedReflectUtils {


    /**
     * 反射获取一个属性
     *
     * @param instance PathClasLoader
     * @param name
     * @return
     */
    public static Field getField(Object instance, String name) throws NoSuchFieldException {
        for (Class<?> cls = instance.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Field declaredField = cls.getDeclaredField(name);
                //设置权限
                declaredField.setAccessible(true);
                return declaredField;
            } catch (NoSuchFieldException e) {
            }
        }
        throw new NoSuchFieldException("Field:" + name + " not found in " + instance.getClass());
    }


    /**
     * 反射获取一个方法
     *
     * @param instance PathClasLoader
     * @param name
     * @return
     */
    public static Method getMethod(Object instance, String name,Class<?>... parameterTypes) throws NoSuchMethodException {
        for (Class<?> cls = instance.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(name,parameterTypes);
                //设置权限
                declaredMethod.setAccessible(true);
                return declaredMethod;
            } catch (NoSuchMethodException e) {
            }
        }
        throw new NoSuchMethodException("Method:" + name + " not found in " + instance.getClass());
    }

    /**
     * @param instance
     * @param fieldName
     * @param fixs      补丁的Element数组
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public static void expandFieldArray(Object instance, String fieldName, Object[] fixs)
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        //拿到 classloader中的dexelements 数组
        Field jlrField = getField(instance, fieldName);
        //old Element[]
        Object[] old = (Object[]) jlrField.get(instance);


        //合并后的数组
        Object[] newElements = (Object[]) Array.newInstance(old.getClass().getComponentType(),
                old.length + fixs.length);

        // 先拷贝新数组
        System.arraycopy(fixs, 0, newElements, 0, fixs.length);
        System.arraycopy(old, 0, newElements, fixs.length, old.length);

        //修改 classLoader中 pathList的 dexelements
        jlrField.set(instance, newElements);
    }
}
