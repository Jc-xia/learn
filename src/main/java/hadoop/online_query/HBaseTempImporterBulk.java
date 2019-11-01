package hadoop.online_query;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.FileOutputStream;
import java.io.IOException;

public class HBaseTempImporterBulk extends Configured implements Tool {


    static class ImportMapper extends Mapper<LongWritable,Text, BytesWritable,KeyValue>{
        /*
        map的输出类型，key为rowkey，类型不确定，可以自定义，也可以使用ImmutableBytesWritable，一般是bytes数据类型
         value为数据行，且数据中要包含rowkey，如put类型，KeyValue类型？
         */
        NcdcRecordParser parser = new NcdcRecordParser();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            parser.parse(value);
            if(parser.isValidTemp()) {
                byte[] rowKey = RowKeyConverter.makeObservationRowKey(parser.getStationId(),
                        parser.getObservationDate().getTime());
                KeyValue keyValue = new KeyValue(rowKey,
                        HBaseTemperatureQuery.DATA_COLUMNFAMILY,
                        HBaseTemperatureQuery.AIRTEMP_QUALIFIER,
                        Bytes.toBytes(parser.getAirTemp()));
                context.write(new BytesWritable(rowKey), keyValue);
            }
        }
    }

    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","number3:2181,number4:2181,number5:2181");
        Job job = Job.getInstance(conf);

        job.setJarByClass(getClass());

        job.setMapOutputKeyClass(BytesWritable.class);
        job.setMapOutputValueClass(KeyValue.class);

        FileOutputFormat.setOutputPath(job,new Path("hdfs://number3/HfileOut"));

        //生成hfile
        Connection connection = ConnectionFactory.createConnection(conf);
        Table table =connection.getTable(TableName.valueOf("observations1"));
        TableDescriptor td = table.getDescriptor();

        HFileOutputFormat2.configureIncrementalLoadMap(job,td);

        return 0;
    }


    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new HBaseTempImporterBulk(),args);
        System.exit(exitCode);
    }
}
