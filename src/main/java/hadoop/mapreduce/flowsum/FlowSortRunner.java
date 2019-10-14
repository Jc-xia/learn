package hadoop.mapreduce.flowsum;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;

public class FlowSortRunner {

    public   static  class FlowSortMapper extends Mapper<LongWritable, Text, FlowSumBean,Text> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] lines = line.split("\t");
            String phone = lines[0];
            long u_flow = Long.parseLong(lines[1]);
            long d_flow = Long.parseLong(lines[2]);
            context.write(new FlowSumBean(u_flow,d_flow),new Text(phone));
        }
    }


    public class FlowSortReducer extends Reducer<FlowSumBean, Text,Text,FlowSumBean> {

        @Override
        protected void reduce(FlowSumBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

            context.write(values.iterator().next(),key);
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
//        Configuration conf = getConf();
//        Job job = new Job();

        //设置job类所在jar包
        job.setJarByClass(FlowSortRunner.class);

        //指定map和reduce类
        job.setMapperClass(FlowSortMapper.class);
        job.setReducerClass(FlowSortReducer.class);


        job.setMapOutputKeyClass(FlowSumBean.class);
        job.setMapOutputValueClass(Text.class);

        //指定reduce输出数据kv类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowSumBean.class);

        //指定输入输出文件
        FileInputFormat.addInputPath(job, new Path("C:\\Users\\xjc\\Desktop\\wc_result\\part-r-00000"));
        FileOutputFormat.setOutputPath(job, new Path("C:\\Users\\xjc\\Desktop\\wc_result2"));
        //C:\Users\xjc\Desktop\

        File file = new File("C:\\Users\\xjc\\Desktop\\wc_result2") ;
        File[] files = file.listFiles();
        if(files.length > 0){
            for (File f : files) {
                f.delete();
            }
        }
        file.delete();

        job.waitForCompletion(true);
    }
}
