package com.sso.service;

import com.sso.vo.SceLogDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
public abstract class Processor { //extends ServiceImpl<SceVariationMapper, SceVariation> {

    public abstract String processor(SceLogDto sceLogDto);

}
