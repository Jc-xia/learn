package hadoop.mapreduce.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

//默认情况下，传入的key为文件中要处理的这一行的起始偏移量，value为这一行的内容
public class WcMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    //mapreuce框架每处理一行数据调用一次该方法
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split(" ");
        for (String word : words) {
            context.write(new Text(word), new LongWritable(1));
        }
    }

}
