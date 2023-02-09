package com.eric.utils.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2022/8/6 5:08 PM
 */
@Slf4j
public class FileNameUtil extends FilenameUtils {

    public static String getTplFile(String templateFile) {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] res = resolver.getResources("classpath*:tpl/" + templateFile);
            return res[0].getURL().getFile();
        } catch (IOException e) {
            log.error("解析文件「"+templateFile+"」路径错误！");
            return null;
        }
    }

}
