package hadoop.mapreduce.FlowSort;

import hadoop.mapreduce.flowsum.FlowSumBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;


public   class FlowSortMapper extends Mapper<LongWritable, Text, FlowSumBean,Text>{
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
