package cn.edu.nuc.acmicpc.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IDEA
 * User: chuninsane
 * Date: 2016/3/8
 * Object util
 */
public class ObjectUtil {

    public static String toString(Object object) {
        if (object == null)
            return "null";
        List<String> list = new ArrayList<>();
        if (object instanceof Iterable) {
            for (Object element : (Iterable)object) {
                list.add(toString(element));
            }
        }
        else {
            Class<?> cls = object.getClass();
            for (Field field : cls.getFields()) {
                try {
                    list.add(String.format("%s : %s", field.getName(), field.get(object).toString()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            for (Method method : cls.getMethods()) {
                String name = method.getName();
                if (!name.startsWith("get"))
                    continue;
                name = name.substring(3);
                try {
                    list.add(String.format("%s : %s", name, method.invoke(object).toString()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return String.format("{%s}", ArrayUtil.join(list.toArray(), ","));
    }
}
