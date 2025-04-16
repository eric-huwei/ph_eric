package com.eric.mapper;

import com.eric.entity.Dictionary;
import org.apache.ibatis.annotations.Mapper;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2020/1/4 下午4:06
 */
@Mapper
public interface DictionaryMapper {

    Dictionary getDictionary(String typeId);
}
