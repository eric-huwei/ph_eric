package com.sso.common.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.MDC;

import java.util.Map;
import java.util.UUID;

import static com.sso.common.util.Constants.MDC_TRACE_ID_KEY;


@Slf4j
public abstract class MdcTaskUtils {

    public static Runnable adaptMdcRunnable(Runnable runnable, Map<String, String> parentThreadContextMap) {
        return () -> {
            log.debug("parentThreadContextMap: {}, currentThreadContextMap: {}", parentThreadContextMap,
                    MDC.getCopyOfContextMap());
            if (MapUtils.isEmpty(parentThreadContextMap) || !parentThreadContextMap.containsKey(MDC_TRACE_ID_KEY)) {
                log.debug("can not find a parentThreadContextMap, maybe task is fired using async or schedule task.");
                MDC.put(MDC_TRACE_ID_KEY, UUID.randomUUID().toString());
            } else {
                MDC.put(MDC_TRACE_ID_KEY, parentThreadContextMap.get(MDC_TRACE_ID_KEY));
            }
            try {
                runnable.run();
            } finally {
                MDC.remove(MDC_TRACE_ID_KEY);
            }
        };
    }

}