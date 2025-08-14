package com.sso.entity;

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
        //2025-07-14 22:30:52
        private String timestamp;
        //"00:49:603659 [>24924<] OUT PDBWebServ D;24;3422168;1752501661;P;url=http://10.15.28.203:12005/iserv BodyMsg=1#ivvr_actionAndFeedback#20250714220049#COMMON#1#14#30#302507142159264018874#1###02710000#18163371550###8320000#72#4114##0#getdigits#2#1######302507142159264018874#########"
        private String message;
    }
}
