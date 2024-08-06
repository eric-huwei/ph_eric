package com.sso.common.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

@Service
public class ConcurrentExcutor {
    /*public void execute(DifferenceReq differenceReq, String beanId, CountDownLatch doneSignal, List<Future<String>> futures) {
        ExecutorService executorService = WorkExecutorFactory.getWorkExecutor(DifferenceConstant.SEC_DIFFERENCE_EXECUTOR);
        if (null == executorService) {
            throw new IllegalArgumentException("指定的工作执行队列不存在!");
        }
        futures.add(executorService.submit(new Executor(differenceReq, beanId, doneSignal, UserTransmittableUtils.get())));
    }*/

}

@Slf4j
class Executor implements Callable {

    private String beanId;
    private CountDownLatch doneSignal;
    private Object object;
    public Executor(String beanId, CountDownLatch doneSignal, Object obj) {
        super();
        this.beanId = beanId;
        this.doneSignal = doneSignal;
        this.object = obj;
    }

    @Override
    public String call() {
        try {
        } catch (Exception e) {
            log.error("线程池回调函数异常", e);
        } finally {
            doneSignal.countDown();
        }
        return null;
    }
}
