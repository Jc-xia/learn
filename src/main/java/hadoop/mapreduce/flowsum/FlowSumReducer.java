package hadoop.mapreduce.flowsum;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FlowSumReducer extends Reducer<Text,FlowSumBean,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<FlowSumBean> values, Context context) throws IOException, InterruptedException {
        long u_flow = 0,d_flow = 0,s_slow = 0;
        for(FlowSumBean bean :values){
            u_flow += bean.getU_flow();
            d_flow += bean.getD_flow();
            s_slow += bean.getS_flow();
        }
        String res = String.format("%d\t%d\t%d", u_flow, d_flow, s_slow);
        context.write(key,new Text(res));
    }
}
