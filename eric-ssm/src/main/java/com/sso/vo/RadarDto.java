package com.sso.vo;

import lombok.Data;

import java.util.List;

/**
 * @author yoyuming
 * @description:
 * @date 2024/8/29 11:04
 */
@Data
public class RadarDto {

    private String authorization;

    private String logRuleConfigId;

    private String catalogId;

    private String keyword;

    private List<String> numList;

    private Long startTime;

    private Long endTime;

}



/*
[
  {
    "logRuleConfigId": 936,
    "catalogId": 136,
    "keyword": "ActTimeOut",
    "numList": [
      "25501",
      "24030"
    ]
  },
  {
    "logRuleConfigId": 937,
    "catalogId": 180,
    "keyword": "ActTimeOut",
    "numList": [
      "25075",
      "24106",
      "20862",
      "20874",
      "24120"
    ]
  }
]
* */