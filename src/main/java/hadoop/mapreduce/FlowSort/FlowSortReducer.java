package hadoop.mapreduce.FlowSort;

import hadoop.mapreduce.flowsum.FlowSumBean;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowSortReducer extends Reducer<FlowSumBean, Text,Text,FlowSumBean> {

    @Override
    protected void reduce(FlowSumBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        context.write(values.iterator().next(),key);
    }
}
