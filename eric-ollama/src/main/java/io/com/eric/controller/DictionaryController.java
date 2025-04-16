package com.eric.controller;

import com.eric.entity.ExportPO;
import com.eric.service.DictionaryService;
import com.eric.utils.file.FileNameUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.*;

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

    @RequestMapping("export")
    public void export() throws IOException {
        List<ExportPO> list = new ArrayList<>();
        ExportPO po = new ExportPO(1, "eric", "test");
        list.add(po);
        Map<String, Object> param = new HashMap<>();
        param.put("datas", param);

        File file = new File(FileNameUtil.getTplFile(""));
        InputStream  stream = new FileInputStream(file);

        Workbook wb = new XSSFWorkbook(stream);

    }

    public static void main(String[] args) {
        ExportPO po = new ExportPO(1, "name", "purpose");
        Map<Object,ExportPO> map = new HashMap();
        Set poSet = map.entrySet();
        Iterator it = poSet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            System.out.println(entry.getValue());
        }
    }
}
