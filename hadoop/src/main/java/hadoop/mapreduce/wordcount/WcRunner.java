package hadoop.mapreduce.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WcRunner {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
//        Configuration conf = getConf();
//        Job job = new Job();

        //设置job类所在jar包
        job.setJarByClass(WcRunner.class);

        //指定map和reduce类
        job.setMapperClass(WcMapper.class);
        job.setReducerClass(WcReducer.class);

        //指定reduce输出数据kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        //指定输入输出文件
        FileInputFormat.addInputPath(job,new Path("C:\\Users\\xjc\\Desktop\\test.sql"));
        FileOutputFormat.setOutputPath(job ,new Path("C:\\Users\\xjc\\Desktop\\wc_result"));
        //C:\Users\xjc\Desktop\

        //类用错了，需要的参数类型不一样
        // FileInputFormat.addInputPath(job,new Path("/test.sql"));
        // FileOutputFormat.setOutputPath(job ,new Path("/wc_result"));

        //打印进度
        job.waitForCompletion(true);
    }
}
