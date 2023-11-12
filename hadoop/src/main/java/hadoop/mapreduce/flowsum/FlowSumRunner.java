package hadoop.mapreduce.flowsum;


import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import java.io.File;

public class FlowSumRunner extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        Job job = new Job(getConf(),"Flow Sum");
        //设置job类所在jar包
        job.setJarByClass(getClass());

        //指定map和reduce类
        job.setMapperClass(FlowSumMapper.class);
        job.setReducerClass(FlowSumReducer.class);

//        //map输出数据类型
//        job.setMapOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(FlowSumBean.class);
        //reduce输出数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowSumBean.class);

        //指定partitoner
        job.setPartitionerClass(FlowSumPartitioner.class);

        //指定reducer个数
        job.setNumReduceTasks(6);

        //指定输入输出文件
        FileInputFormat.addInputPath(job,new Path("C:\\Users\\xjc\\Desktop\\test.sql"));
        FileOutputFormat.setOutputPath(job ,new Path("C:\\Users\\xjc\\Desktop\\wc_result"));

        return job.waitForCompletion(true)?0:1;
    }

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\xjc\\Desktop\\wc_result") ;
        if(file.exists()) {
            File[] files = file.listFiles();
            if (files.length > 0) {
                for (File f : files) {
                    f.delete();
                }
            }
            file.delete();
        }

        int exitcode = ToolRunner.run(new FlowSumRunner(),args);
        System.exit(exitcode);
    }

}
