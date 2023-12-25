package mysqltest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 事务测试
 * 根据表中 third_id和status字段查询记录，有则更新，没有则插入
 * 1. 开启事务不会加锁，事务中有写操作才会
 * 2. 写入语句，包括 select …… for update 语句 会加锁，当字段有索引时，加的时行级锁，否则加的是表级锁。加的是写锁，还能能查相关数据，但是不能写入
 * 3. 所以利用 select …… for update 可以实现分布式锁，在插入数据前需要用 select …… for update 查询，放在同一个事务内
 *
 * @author JC
 * @create 2023/12/7
 */
public class TransactionTest {

    private Connection connection;

    public TransactionTest() {
        // 初始化数据库连接
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertRecord(int thirdId, String status, String content) {
        try {
            // 开始事务
            connection.setAutoCommit(false);

            // 检查是否存在相同id的记录
            PreparedStatement selectStatement = connection.prepareStatement(
                    "SELECT * FROM test_third_id WHERE third_id = ? and status='approving'");
            selectStatement.setInt(1, thirdId);
            if (selectStatement.executeQuery().next()) {
//                    // 如果存在记录，则说明获取到锁
                System.out.println("get lock!!!");
//                    connection.rollback();
//                    return;
            }

            // 插入新记录
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO test_third_id (third_id, status, content) VALUES (?,?, ?)");
            insertStatement.setInt(1, thirdId);
            insertStatement.setString(2, status);
            insertStatement.setString(3, content);
            insertStatement.executeUpdate();

            // 更新记录
//                PreparedStatement updateStatement = connection.prepareStatement(
//                        "update test_third_id set content='update content' where third_id=1;");
//                updateStatement.executeUpdate();

            // 提交事务
            connection.commit();

        } catch (SQLException e) {
            // 发生异常时回滚事务
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            // 恢复自动提交模式
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TransactionTest transactionTest = new TransactionTest();
        transactionTest.insertRecord(1, "approving", "test content111");
    }
}
