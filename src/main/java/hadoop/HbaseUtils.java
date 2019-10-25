package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class HbaseUtils {

    Admin admin = null;
    TableName tname= null;
    Connection connection =null;
    @Before
    public void initCon() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum","number3:2181,number4:2181,number5:2181");

        connection = ConnectionFactory.createConnection(conf);
        admin = connection.getAdmin();
        tname = TableName.valueOf("Hbase-test");
    }

    /*
    建表
     */
    @Test
    public void hbaseCreateTable() throws IOException {

            /*
           ColumnFamilyDescriptorBuilder用来定义列族信息，然后创建ColumnFamilyDescriptor
           TableDescriptorBuilder用来创建列族，参数为ColumnFamilyDescriptor，创建TableDescriptor
           admin用来创建表，参数为TableDescriptor
            */

        TableDescriptorBuilder tdBuilder = TableDescriptorBuilder.newBuilder(tname);

        ColumnFamilyDescriptorBuilder cdBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("family1"));
        ColumnFamilyDescriptor cd = cdBuilder.build();

        tdBuilder.setColumnFamily(cd);
        TableDescriptor td = tdBuilder.build();
        //建表，如果存在删除后重建
        if(admin.tableExists(tname)) {
            admin.disableTable(tname);
            admin.deleteTable(tname);
            admin.createTable(td);
        }
    }

    /*
    增加列族
     */
    @Test
    public void hbaseAddFamily() throws Exception{
        ColumnFamilyDescriptorBuilder cdBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("family2"));
        ColumnFamilyDescriptor cd = cdBuilder.build();
        admin.addColumnFamily(tname, cd);
    }

    /*
    添加数据，需指定列族和列名，相当于添加列,如果指定列名已存在则相当于更新操作
     */
    @Test
    public void hbaseAddDataOrColumn() throws IOException {
        Table table = connection.getTable(tname);
        byte[] family = Bytes.toBytes("family1");

        for (int i=2;i<5;i++){
        byte[] rowid = Bytes.toBytes("row"+i);
        Put put = new Put(rowid);
        byte[] qualifier = Bytes.toBytes("column"+i);
        byte[] data = Bytes.toBytes("column-data-change1"+i);
        put.addColumn(family,qualifier,data);
        table.put(put);
        }
        table.close();
    }

    /*
    查询数据
     */
    @Test
    public void hbaseGet() throws IOException {
        Table table = connection.getTable(tname);
        Get get = new Get(Bytes.toBytes("row1"));
        Result result = table.get(get);
        System.out.println(result.toString());
        table.close();
    }

    /*
    全表扫描
     */
    @Test
    public void hbaseScan() throws IOException {
        Table table = connection.getTable(tname);
        Scan scan = new Scan();
        ResultScanner result = table.getScanner(scan);
        for (Result r :result) {
            System.out.println(r);
        }
        table.close();
    }

    @After
    public  void hbaseCloseCon() throws IOException {
        admin.close();
    }

    public static void main(String[] args) throws IOException {

    }
}
