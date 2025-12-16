package com.sso.common.thread;

import com.sso.common.util.Constants;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class WorkExecutorFactory {

    public static Map<String, ThreadPoolExecutor> workExecutors = new ConcurrentHashMap();

    @PostConstruct
    private void initWorkExecutors() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 5L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(50));

        workExecutors.put(Constants.RADAR_EXECUTOR, executor);
    }

    public static ExecutorService getWorkExecutor(String workExecutorName) {
        return workExecutors.get(workExecutorName);
    }

}
