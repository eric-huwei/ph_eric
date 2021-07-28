package com.eric.java8;

import com.alibaba.fastjson.JSON;

import java.util.*;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/5/20 下午4:16
 */
public class StreamDemo {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(JSON.toJSONString(list));

        Optional firstOptional = list.stream().findFirst();
        System.out.println(firstOptional);

        List<Map<String, Object>> widget = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("color", "red");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("color", "green");
        Map<String, Object> map3 = new HashMap<>();
        map3.put("color", "blue");
        Map<String, Object> map4 = new HashMap<>();
        map4.put("color", "red");

        widget.add(0, map1);
        widget.add(1, map2);
        widget.add(2, map3);
        widget.add(3, map4);

        int size = widget.stream().filter(wid -> wid.get("color") == "red").mapToInt(wid -> wid.size()).sum();
        System.out.println("size == " + size);

    }
}
