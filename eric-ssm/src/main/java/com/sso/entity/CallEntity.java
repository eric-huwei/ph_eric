package com.sso.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import java.util.List;

@Data
public class CallEntity {


    private String status;
    private String message;
    private CallData data;


    @Data
    public class CallData {
        private List<CallLogList> logList;
    }
    @Data
    public class CallLogList {
        private String timestamp;
        private String message;
        private FieldValue fieldValue;
    }

    @Data
    public class FieldValue {
        private String mod;
        private String ip;
        
        @JSONField(name = "log.offset")
        private String logOffset;
        
        @JSONField(name = "log.file.path")
        private String logFilePath;
        
        private String host;
        private String index_prefix;
    }
}