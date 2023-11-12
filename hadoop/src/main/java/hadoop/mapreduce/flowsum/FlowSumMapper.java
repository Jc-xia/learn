package hadoop.mapreduce.flowsum;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * FlowSumBean是自己定义的数据类型，用于序列化数据
 * 需要继承hadoop的序列化接口（主要是Writeable）和方法
 */
public class FlowSumMapper extends Mapper<LongWritable, Text,Text,FlowSumBean> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] lines = line.split("\t");

        String phone = lines[0];
        /*
        这里的getlong方法只能返回代表系统属性的字符串的long值，
        如sun.arch.data.modelsun.arch.data.model包名，等
         long u_flow = Long.getLong(lines[1]);
         long d_flow = Long.getLong(lines[2]);
         */
        long u_flow = Long.parseLong(lines[1]);
        long d_flow = Long.parseLong(lines[2]);

        context.write(new Text(phone),new FlowSumBean(u_flow,d_flow));

    }
}
