package com.eric.service;

import com.eric.entity.Dictionary;
import com.eric.mapper.DictionaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2020/1/4 下午4:05
 */
@Service
public class DictionaryService {

    @Autowired
    DictionaryMapper dictionaryMapper;

    public Dictionary getDictionary(String typeId) {
        return Dictionary.builder().id(1).typeName("test").build();
//        return dictionaryMapper.getDictionary(typeId);
    }
}
