package com.sso.common.thread;

import cn.hutool.extra.spring.SpringUtil;
import com.sso.common.util.Constants;
import com.sso.entity.CallEntity;
import com.sso.service.Processor;
import com.sso.vo.SceLogDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class ConcurrentExcutor {
    public void execute(String beanId, CountDownLatch doneSignal, SceLogDto sceLogDto, List<Future<List<CallEntity.CallLogList>>> futures) {
        ExecutorService executorService = WorkExecutorFactory.getWorkExecutor(Constants.RADAR_EXECUTOR);
        if (null == executorService) {
            throw new IllegalArgumentException("指定的工作执行队列不存在!");
        }
        futures.add(executorService.submit(new Executor(beanId, doneSignal, sceLogDto)));
    }

}

@Slf4j
class Executor implements Callable {

    private String beanId;
    private CountDownLatch doneSignal;
    private SceLogDto sceLogDto;
    public Executor(String beanId, CountDownLatch doneSignal, SceLogDto sceLogDto) {
        super();
        this.beanId = beanId;
        this.doneSignal = doneSignal;
        this.sceLogDto = sceLogDto;
    }

    @Override
    public List<CallEntity.CallLogList> call() {
        try {
            Processor processor = SpringUtil.getBean(beanId);
            return processor.processor(sceLogDto);
        } catch (Exception e) {
            log.error("线程池回调函数异常", e);
        } finally {
            doneSignal.countDown();
        }
        return null;
    }
}
