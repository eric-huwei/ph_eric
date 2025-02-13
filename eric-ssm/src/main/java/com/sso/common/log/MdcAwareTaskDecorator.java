package com.sso.common.log;

import org.slf4j.MDC;
import org.springframework.core.task.TaskDecorator;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MdcAwareTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        Map<String, String> parentThreadContextMap = MDC.getCopyOfContextMap();
        return MdcTaskUtils.adaptMdcRunnable(runnable, parentThreadContextMap);
    }
}
