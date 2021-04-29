package com.eric.mybatis.mapper;

import com.eric.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/4/25 下午10:09
 */
@Mapper
public interface UserMapper {

    User getUserInfo(int id);
}
