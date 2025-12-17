package com.sso.controller;

import com.sso.common.thread.ConcurrentExcutor;
import com.sso.entity.CallEntity;
import com.sso.service.impl.SceLogProcessor;
import com.sso.vo.RadarDto;
import com.sso.vo.SceLogDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@RestController
@RequestMapping("/manbot/radar")
public class RadarController {

    @PostMapping("/sceLog")
    public List sceLog(@RequestBody RadarDto radarDto) {
        List<List<CallEntity.CallLogList>> result = new ArrayList<>();

        List<String> numList = radarDto.getNumList();
        /*if (CollectionUtil.isEmpty(numList)) {
            return "";
        }*/

        List<Future<List<CallEntity.CallLogList>>> futures = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(numList.size());
        ConcurrentExcutor concurrentExcutor = new ConcurrentExcutor();

        for (String logId : numList) {
            SceLogDto sceLogDto = SceLogDto.builder().catalogId(radarDto.getCatalogId())
                    .startTime(radarDto.getStartTime())
                    .authorization(radarDto.getAuthorization())
                    .keyword("\\\\[>"+logId+"<\\\\] AND (OUT OR ( ERR ActTimeOut \\\"Evt=2\\\" ))")
                    .endTime(radarDto.getEndTime()).build();
            concurrentExcutor.execute("sceLogProcessor", countDownLatch, sceLogDto, futures);
        }

        for (int i = 0; i < futures.size(); i++) {
            Future<List<CallEntity.CallLogList>> future = futures.get(i);
            try {
                if (!ObjectUtils.isEmpty(future.get(3, TimeUnit.SECONDS)) && future.isDone()) {
                    result.add(future.get());
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}
