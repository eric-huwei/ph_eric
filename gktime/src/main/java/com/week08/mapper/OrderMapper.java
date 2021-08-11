package com.week08.mapper;

import com.week08.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/8/11 下午4:23
 */
@Mapper
public interface OrderMapper {

    Order getOrderInfo(@Param("orderId") int orderId, @Param("userId") int userId);

}
