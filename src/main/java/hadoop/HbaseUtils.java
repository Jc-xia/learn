package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class HbaseUtils {

    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();

        conf.set("hbase.zookeeper.quorum","number3:2181,number4:2181,number5:2181");

        Connection connection = ConnectionFactory.createConnection(conf);


        Admin admin = connection.getAdmin();

        TableName tname = TableName.valueOf("Hbase-test");

        //不能用，需要先建表，才能get
        // Table htable = connection.getTable(tname);

        //过时方法
        // HTableDescriptor htd = new HTableDescriptor(TableName.valueOf("Hbase-test"));

        TableDescriptorBuilder htdBuilder = TableDescriptorBuilder.newBuilder(tname);
        TableDescriptor htd = htdBuilder.build();
        ColumnFamilyDescriptorBuilder hcdBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("famliy1"));
        ColumnFamilyDescriptor hcd = hcdBuilder.build();

        /*
        ColumnFamilyDescriptorBuilder用来定义列族信息，然后创建ColumnFamilyDescriptor
        TableDescriptorBuilder用来创建列族，参数为ColumnFamilyDescriptor，创建TableDescriptor
        admin用来创建表，参数为TableDescriptor
         */
        htdBuilder.setColumnFamily(hcd);
        admin.createTable(htd);

        TableName[] tableList = admin.listTableNames();
        for (TableName t : tableList){
            System.out.println("表名：" + t.getNameAsString());
        }




    }
}
