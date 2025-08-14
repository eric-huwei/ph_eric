package com.sso.common.util;

import com.alibaba.fastjson.JSON;
import com.sso.entity.CallEntity;

import java.io.*;
import java.util.List;

public class CallMes {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\ideaprojects\\ph_eric\\eric-ssm\\src\\main\\resources\\call.json");
        readerMethod(file);
    }

    private static void readerMethod(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        Reader reader = new InputStreamReader(new FileInputStream(file), "Utf-8");
        int ch = 0;
        StringBuffer sb = new StringBuffer();
        while ((ch = reader.read()) != -1) {
            sb.append((char) ch);
        }
        fileReader.close();
        reader.close();
        String jsonStr = sb.toString().replace('\u0001', '#').replace('\u0002', '#').replace('\u0003', '#')
                .replace('\u0004', '#').replace('\u001A', '#');
        CallEntity entity = JSON.parseObject(jsonStr, CallEntity.class);
        List<CallEntity.CallLogList> callLogLists = entity.getData().getLogList();

        for (CallEntity.CallLogList info : callLogLists) {
            String message = info.getMessage().substring(0,12);
            info.setTimestamp(message);
        }
    }

}
