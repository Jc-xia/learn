package hadoop.mapreduce.wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WcReducer extends Reducer<Text,LongWritable,  Text, LongWritable> {

    //传入的数据样例（hello，{1，1，1，1，。。。}）
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        //遍历values的迭代器
        int count=0;
        for (LongWritable i :values){
            //longwriteble类型有个get方法直接获得long类型值
            count += i.get();
        }

        context.write(key, new LongWritable(count));
    }
}
