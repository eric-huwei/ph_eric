package com.eric.controller;

import com.eric.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2020/1/4 下午4:22
 */
@RestController
public class DictionaryController {

    @Autowired
    DictionaryService dictionaryService;

    @RequestMapping(value = "/getDictionary/{typeId}", method = RequestMethod.GET)
    public String getDictionary(@PathVariable String typeId) {
        return dictionaryService.getDictionary(typeId).toString();
    }
}
