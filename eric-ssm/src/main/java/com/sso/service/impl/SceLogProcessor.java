package com.sso.service.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.sso.entity.CallEntity;
import com.sso.service.Processor;
import com.sso.vo.SceLogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("sceLogProcessor")
public class SceLogProcessor extends Processor {

    @Override
    public List<CallEntity.CallLogList> processor(SceLogDto sceLogDto) {
        List<CallEntity.CallLogList> simpMessage = new ArrayList<>();

        String result = "";
        
        // 1. HTTP请求可能异常：网络超时、连接失败、HTTP错误码
        try {
            result = HttpUtil.createPost("http://132.126.196.99:20026/aiops/api/v1/logservice/FaultDiagnosisLogCtrl/searchEsDate")
                    .header("Authorization", sceLogDto.getAuthorization())
                    .header("Content-Type", "application/json")
                    .body(JSON.toJSONString(sceLogDto))
                    .execute()
                    .body();
        } catch (Exception e) {
            log.error("流水号{}调用一体化接口异常{}", sceLogDto.getKeyword(), e.getMessage());
        }

        // 2. JSON解析可能异常：result为null、格式错误
        if (result == null || result.isEmpty()) {
            log.error("流水号{}调用一体化接口响应结果为空", sceLogDto.getKeyword());
            return simpMessage;
        }

        CallEntity entity = JSON.parseObject(result, CallEntity.class);

        // 3. NullPointerException：entity、data、logList可能为null
        if (entity == null || entity.getData() == null || entity.getData().getLogList() == null) {
            log.error("解析结果为空，entity: {}", entity);
            return simpMessage;
        }

        List<CallEntity.CallLogList> callLogLists = entity.getData().getLogList();

        if (callLogLists.isEmpty()) {
            log.warn("流水号{}日志列表为空", sceLogDto.getKeyword());
            return simpMessage;
        }

        // 4. StringIndexOutOfBoundsException：message或timestamp长度不足
        for (CallEntity.CallLogList info : callLogLists) {
            try {
                String message = info.getMessage();
                String timestamp = info.getTimestamp();

                if (message != null && message.length() >= 12 && timestamp != null && timestamp.length() >= 14) {
                    String messagePart = message.substring(0, 12);
                    info.setTimestamp(timestamp.substring(0, 14) + messagePart);
                } else {
                    log.warn("日志格式异常，跳过该条记录。message length: {}, timestamp length: {}",
                            message != null ? message.length() : 0,
                            timestamp != null ? timestamp.length() : 0);
                }
            } catch (Exception e) {
                log.error("处理单条日志时异常: {}", e.getMessage());
                // 继续处理下一条
            }
        }

        // 5. NullPointerException：getTimestamp()可能返回null
        List<CallEntity.CallLogList> sortedMessages = callLogLists.stream()
                .filter(item -> item.getTimestamp() != null)
                .sorted(Comparator.comparing(CallEntity.CallLogList::getTimestamp))
                .collect(Collectors.toList());

        // 遍历排序后的数组，找到包含ActTimeOut的元素并获取前一项
        for (int i = 0; i < sortedMessages.size(); i++) {
            CallEntity.CallLogList currentItem = sortedMessages.get(i);
            String message = currentItem.getMessage();

            // 检查是否包含ActTimeOut
            if (message != null && message.contains("ActTimeOut")) {
                // 获取前一项
                if (i > 0) {
                    simpMessage.add(sortedMessages.get(i - 1));
                }
                simpMessage.add(currentItem);
            }
        }
            
        log.info("流水号{}处理完成，返回结果: {}", sceLogDto.getKeyword(), simpMessage);
        return simpMessage;
    }


}
