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

    private String authorization;

    @Builder.Default
    private List analysisViews = new ArrayList<>();

    private String catalogId;

    @Builder.Default
    private List filterFields = new ArrayList<>();

    @Builder.Default
    private String interval = "hour";

    private String keyword;

    @Builder.Default
    private String queryType = "query";

    @Builder.Default
    private Integer from = 0;

    @Builder.Default
    private Integer size = 6000;

    private Long startTime;

    private Long endTime;

}


