package hadoop.mapreduce.flowsum;

import hadoop.mapreduce.wordcount.WcDriver;
import hadoop.mapreduce.wordcount.WcMapper;
import hadoop.mapreduce.wordcount.WcReducer;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class FlowSumRunner extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        Job job = new Job(getConf(),"Flow Sum");
        //设置job类所在jar包
        job.setJarByClass(getClass());

        //指定map和reduce类
        job.setMapperClass(FlowSumMapper.class);
        job.setReducerClass(FlowSumReducer.class);

        //指定输入输出文件
        FileInputFormat.addInputPath(job,new Path("C:\\Users\\xjc\\Desktop\\test.sql"));
        FileOutputFormat.setOutputPath(job ,new Path("C:\\Users\\xjc\\Desktop\\wc_result"));

        return job.waitForCompletion(true)?0:1;
    }

    public static void main(String[] args) throws Exception {
        int exitcode = ToolRunner.run(new WcDriver(),args);
        System.exit(exitcode);
    }

}
