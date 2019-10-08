package hadoop.mapreduce.wordcount;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WcDriver extends Configured implements Tool {


    @Override
    public int run(String[] args) throws Exception {

        Job job = new Job(getConf(),"Word Count");
        //设置job类所在jar包
        job.setJarByClass(getClass());

        //指定map和reduce类
        job.setMapperClass(WcMapper.class);
        job.setReducerClass(WcReducer.class);

        //指定reduce输出数据kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

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
