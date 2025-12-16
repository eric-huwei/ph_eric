package com.sso.vo;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yoyuming
 * @description:
 * @date 2024/8/29 11:04
 */
@Data
@Builder
public class SceLogDto {

    private List analysisViews = new ArrayList<>();

    private String catalogId;

    private List filterFields = new ArrayList<>();

    private String interval = "hour";

    private String keyword;

    private String queryType = "query";

    private Integer from = 0;

    private Integer size = 6000;

    private Long startTime;

    private Long endTime;

}


