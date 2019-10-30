package hadoop.online_query;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Uses HBase's {@link TableOutputFormat} to load temperature data into a HBase table.
 */
public class HBaseTempImporter extends Configured implements Tool {

    static class HBaseTemperatureMapper<K> extends Mapper<LongWritable, Text, K, Put> {
        private NcdcRecordParser parser = new NcdcRecordParser();


        @Override
        public void map(LongWritable key, Text value, Context context) throws
                IOException, InterruptedException {
            parser.parse(value.toString());
            if (parser.isValidTemp()) {
                byte[] rowKey = RowKeyConverter.makeObservationRowKey(parser.getStationId(),
                        parser.getObservationDate().getTime());
                Put p = new Put(rowKey);

                p.addColumn(HBaseTemperatureQuery.DATA_COLUMNFAMILY,
                        HBaseTemperatureQuery.AIRTEMP_QUALIFIER,
                        Bytes.toBytes(parser.getAirTemp()));
                context.write(null, p);
            }
        }
    }

    @Override
    public int run(String[] args) throws Exception {
//        if (args.length != 1) {
//            System.err.println("Usage: HBaseTemperatureImporter <input>");
//            return -1;
//        }
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","number3:2181,number4:2181,number5:2181");
        conf.set(TableOutputFormat.OUTPUT_TABLE, "observations");
        Job job = Job.getInstance(conf);
        job.setJarByClass(getClass());
        FileInputFormat.addInputPath(job, new Path("hdfs://192.168.233.3:9000/1901.txt"));
        //job.getConfiguration().set(TableOutputFormat.OUTPUT_TABLE, "observations");
        job.setMapperClass(HBaseTemperatureMapper.class);
        job.setNumReduceTasks(0);
        job.setOutputFormatClass(TableOutputFormat.class);
        return job.waitForCompletion(true) ? 0 : 1;
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(HBaseConfiguration.create(),
                new HBaseTempImporter(), args);
        System.exit(exitCode);
    }
}