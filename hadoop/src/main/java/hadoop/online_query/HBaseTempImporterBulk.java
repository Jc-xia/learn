package hadoop.online_query;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.HFileOutputFormat2;
import org.apache.hadoop.hbase.tool.LoadIncrementalHFiles;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.IOException;

public class HBaseTempImporterBulk extends Configured implements Tool {


    static class ImportMapper extends Mapper<LongWritable,Text, ImmutableBytesWritable,KeyValue>{
        /*
        map的输出类型，key为rowkey，使用ImmutableBytesWritable
         value为数据行，且数据中要包含rowkey，为put类型或KeyValue类型
         */
        NcdcRecordParser parser = new NcdcRecordParser();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            parser.parse(value);
            if(parser.isValidTemp()) {
                byte[] rowKey = RowKeyConverter.makeObservationRowKey(parser.getStationId(),
                        parser.getObservationDate().getTime());

                KeyValue kv = new KeyValue(rowKey,HBaseTemperatureQuery.DATA_COLUMNFAMILY,
                        HBaseTemperatureQuery.AIRTEMP_QUALIFIER,
                        Bytes.toBytes(parser.getAirTemp()));
                //value为Put类型也可以
//                Put p = new Put(rowKey);
//                p.add(kv);
//                p.addColumn(HBaseTemperatureQuery.DATA_COLUMNFAMILY,
//                        HBaseTemperatureQuery.AIRTEMP_QUALIFIER,
//                        Bytes.toBytes(parser.getAirTemp()));
                context.write(new ImmutableBytesWritable(rowKey), kv);
            }
        }
    }



    @Override
    public int run(String[] strings) throws Exception {
        Configuration conf = new Configuration();
        conf.set("hbase.zookeeper.quorum","number3:2181,number4:2181,number5:2181");
        Job job = Job.getInstance(conf);

        job.setJarByClass(getClass());

        job.setMapOutputKeyClass(ImmutableBytesWritable.class);
        job.setMapOutputValueClass(KeyValue.class);
        job.setMapperClass(ImportMapper.class);
        job.setNumReduceTasks(0);

        Path hInPath = new Path("hdfs://number3:9000/1901.txt");
        Path hOutPath = new Path("hdfs://number3:9000/HfileOut");
        FileInputFormat.addInputPath(job,hInPath );
        FileOutputFormat.setOutputPath(job,hOutPath);

        //生成hfile
        Connection connection = ConnectionFactory.createConnection(conf);
        TableName tableName = TableName.valueOf("observations1");
        Table table =connection.getTable(tableName);
        TableDescriptor td = table.getDescriptor();
        /*
        HFileOutputFormat2.configureIncrementalLoadMap(job, table);
        这里不用configureIncrementalLoadMap,而是configureIncrementalLoad方法。在configureIncrementalLoad方法中指定了Put和KeyValue的reducer类，
        而configureIncrementalLoadMap方法没有，所以在生成job时需要手动指定。
         */

        HFileOutputFormat2.configureIncrementalLoad(job,td,connection.getRegionLocator(tableName));

        if (!job.waitForCompletion(true)) {
            return 1;
        }

        //导入hbase
        LoadIncrementalHFiles loader = new LoadIncrementalHFiles(conf);
        loader.doBulkLoad(hOutPath,
                connection.getAdmin(),
                connection.getTable(tableName),
                connection.getRegionLocator(tableName));
        //FileSystem.get(conf).delete(hOutPath, true);
        return 0;
    }


    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new HBaseTempImporterBulk(),args);
        System.exit(exitCode);
    }
}
