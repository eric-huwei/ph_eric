package com.sso.service;

import com.sso.entity.CallEntity;
import com.sso.vo.SceLogDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


@Slf4j
@Data
public abstract class Processor { //extends ServiceImpl<SceVariationMapper, SceVariation> {

    public abstract List<CallEntity.CallLogList> processor(SceLogDto sceLogDto);

}
