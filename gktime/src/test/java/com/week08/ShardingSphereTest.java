package com.week08;

import com.week08.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @DESCIRPTION
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/8/11 下午10:10
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ShardingSphereTest {

    @Resource
    OrderMapper orderMapper;

    @Test
    public void testShardingSphere() {
        orderMapper.getOrderInfo(1, 1991);
    }
}
