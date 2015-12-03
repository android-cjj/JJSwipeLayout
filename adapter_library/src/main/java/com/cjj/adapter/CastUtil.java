package com.cjj.adapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class CastUtil {

    public static <T> List<T> arrayToList(T[] array){
        List<T> list = new ArrayList<>();
        for (T t:array)
        {
            list.add(t);
        }
        return list;
    }

    public static <T> T[] listToArray(List<T> list){
        int length = list.size();
        T[] newArray = (T[]) Array.newInstance(list.getClass().getComponentType(),length);
        return newArray;
    }

}
