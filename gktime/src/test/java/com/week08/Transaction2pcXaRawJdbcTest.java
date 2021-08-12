package com.week08;

import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @DESCIRPTION 第一次插入数据正常，第二次插入数据由于主键冲突，回滚
 * @AUTHOR SCORPIO.HU
 * @DATE 2021/8/12 上午9:34
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class Transaction2pcXaRawJdbcTest {

    @Autowired
    DataSource dataSource;

    @Test
    public void testShardingXATransaction() throws SQLException {
        cleanData();

        TransactionTypeHolder.set(TransactionType.XA);
        Connection conn = dataSource.getConnection();
        String sql = "insert into t_order(user_id, order_id) value(?, ?)";

        System.out.println("First XA start insert data");
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (int i = 0; i < 11; i++) {
                statement.setLong(1, i);
                statement.setLong(2, i);
                statement.executeUpdate();
            }
            conn.commit();
        }
        System.out.println("First XA insert data end successful");

        System.out.println("XA start insert data second");
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            conn.setAutoCommit(false);
            for (int i = 20; i > 0; i--) {
                statement.setLong(1, i - 5);
                statement.setLong(2, i - 5);
                statement.executeUpdate();
            }
            conn.commit();
        } catch (Exception e) {
            System.out.println("Second XA fail " + e);
            conn.rollback();
        } finally {
            conn.close();
        }

        System.out.println("Second XA execute successful");
    }

    private void cleanData() {
        System.out.println("Clean up all data begin...");
        try (Connection conn = dataSource.getConnection(); Statement statement = conn.createStatement()) {
            statement.execute("delete from t_order");
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Clean up all data success...");
    }
}
