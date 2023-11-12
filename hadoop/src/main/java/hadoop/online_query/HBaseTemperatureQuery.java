package hadoop.online_query;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;


public class HBaseTemperatureQuery extends Configured implements Tool {

    static final byte[] DATA_COLUMNFAMILY = Bytes.toBytes("data");
    static final byte[] AIRTEMP_QUALIFIER = Bytes.toBytes("airtemp");

    public NavigableMap<Long, Integer> getStationObservations(
            Table table, String stationId, long maxStamp, int maxCount) throws IOException {
        byte[] startRow = RowKeyConverter.makeObservationRowKey(stationId, maxStamp);
        NavigableMap<Long, Integer> resultMap = new TreeMap<Long, Integer>();
        Scan scan = new Scan(startRow);
        scan.addColumn(DATA_COLUMNFAMILY, AIRTEMP_QUALIFIER);
        ResultScanner scanner = table.getScanner(scan);
        try {
            Result res;
            int count = 0;
            while ((res = scanner.next()) != null && count++ < maxCount) {
                byte[] row = res.getRow();
                byte[] value = res.getValue(DATA_COLUMNFAMILY, AIRTEMP_QUALIFIER);
                /*
                hbase存储时不是真实的时间戳，而是Long.MAX_VALUE-时间戳，
                所以保证了存储时每一个观测站的数据按时间逆序，时间最新在前,取出时也是取出最新的十条数据
                而取数据时，再做一次此运算取出对应的时间戳，且取出的row中包含stationId，所以要截取一下，取后面的时间戳
                取出到map以后，由于map会自动排序，所以map中的数据是按照时间顺序来排列的，所以打印时调用descendingMap()
                使返回值仍然回归到降序。
                 */
                Long stamp = Long.MAX_VALUE -
                        Bytes.toLong(row, row.length - Bytes.SIZEOF_LONG, Bytes.SIZEOF_LONG);
                Integer temp = Bytes.toInt(value);
                resultMap.put(stamp, temp);
            }
        } finally {
            scanner.close();
        }
        return resultMap;
    }

    public int run(String[] args) throws IOException {

        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum","number3:2181,number4:2181,number5:2181");
        Connection connection = ConnectionFactory.createConnection(config);
        try {
            TableName tableName = TableName.valueOf("observations");
            Table table = connection.getTable(tableName);
            try {
                NavigableMap<Long, Integer> observations =
                        getStationObservations(table, "011990-99999", Long.MAX_VALUE, 10).descendingMap();
                for (Map.Entry<Long, Integer> observation : observations.entrySet()) {
                    // Print the date, time, and temperature
                    System.out.printf("%1$tF %1$tR\t%2$s\n", observation.getKey(),
                            observation.getValue());
                }
                return 0;
            } finally {
                table.close();
            }
        } finally {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(HBaseConfiguration.create(),
                new HBaseTemperatureQuery(), args);
        System.exit(exitCode);
    }

}

