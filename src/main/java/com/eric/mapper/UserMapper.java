package com.eric.mapper;

import com.eric.entity.User;
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
