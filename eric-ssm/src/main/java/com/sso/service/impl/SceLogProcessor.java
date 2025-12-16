package com.sso.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.sso.entity.CallEntity;
import com.sso.service.Processor;
import com.sso.vo.SceLogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("sceLogProcessor")
public class SceLogProcessor extends Processor {

    @Value("${nlp.syncScenecodeAction.url:-1}")
    private String nlpSyncScenecodeActionUrl;

    @Override
    public String processor(SceLogDto sceLogDto) {
        String result = HttpUtil.post(nlpSyncScenecodeActionUrl, BeanUtil.beanToMap(sceLogDto));

        return "";
    }




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
            info.setTimestamp(info.getTimestamp().substring(0,14) + message);
        }


         List<CallEntity.CallLogList> sortedMessages = callLogLists.stream().sorted(Comparator.comparing(CallEntity.CallLogList::getTimestamp)).collect(Collectors.toList());

        System.out.println();
    }

}
