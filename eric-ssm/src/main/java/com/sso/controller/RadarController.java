package com.sso.controller;

import com.sso.common.thread.ConcurrentExcutor;
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

    @Autowired
    private SceLogProcessor sceLogProcessor;

    @PostMapping("/sceLog")
    public String sceLog(@RequestBody RadarDto radarDto) {
        List<String> numList = radarDto.getNumList();
        /*if (CollectionUtil.isEmpty(numList)) {
            return "";
        }*/

        List<Future<String>> futures = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(numList.size());
        ConcurrentExcutor concurrentExcutor = new ConcurrentExcutor();

        for (String logId : numList) {
            SceLogDto sceLogDto = SceLogDto.builder().catalogId(radarDto.getCatalogId())
                    .keyword("\\\\[>"+logId+"<\\\\] AND (OUT OR ( ERR ActTimeOut \\\"Evt=2\\\" ))")
                    .startTime(radarDto.getStartTime())
                    .endTime(radarDto.getEndTime()).build();
            concurrentExcutor.execute("sceLogProcessor", countDownLatch, sceLogDto, futures);
        }

        for (int i = 0; i < futures.size(); i++) {
            Future<String> future = futures.get(i);
            try {
                if (future.isDone() && !ObjectUtils.isEmpty(future.get(2, TimeUnit.SECONDS))) {
                    String content = future.get();
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                e.printStackTrace();
            }
        }

        return "";
    }

}
