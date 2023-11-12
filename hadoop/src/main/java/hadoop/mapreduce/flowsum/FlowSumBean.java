package hadoop.mapreduce.flowsum;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 要想使用该类作为map的key输出，则必须继承WritableComparable接口
 * 如果将WritableComparable分开写如（Writable，Comparable<T>）隔开则会报错
 * 作为map的value输出则需要继承Writable接口，可不继承Comparable；
 *
 * map到reduce时存在shuffle,若作为key，则需要实现WritableComparable
 * https://blog.csdn.net/magicianofcodes/article/details/77169735
 */
public class FlowSumBean implements WritableComparable<FlowSumBean> {
    private Long u_flow;
    private Long d_flow;
    private Long s_flow;

    //有参构造方法，便于赋值，不需要一个个调用set方法
    public FlowSumBean( Long u_flow, Long d_flow) {
        this.u_flow = u_flow;
        this.d_flow = d_flow;
        this.s_flow = u_flow + d_flow;
    }
    //因为设置了有参构造方法，所以需要显式定义空参构造方法，为了反序列化时调用
    public FlowSumBean() {
    }

    //getter setter

    public Long getU_flow() {
        return u_flow;
    }

    public void setU_flow(Long u_flow) {
        this.u_flow = u_flow;
    }

    public Long getD_flow() {
        return d_flow;
    }

    public void setD_flow(Long d_flow) {
        this.d_flow = d_flow;
    }

    public Long getS_flow() {
        return s_flow;
    }

    public void setS_flow(Long s_flow) {
        this.s_flow = s_flow;
    }

    //将数据序列化到流中
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(u_flow);
        dataOutput.writeLong(d_flow);
        dataOutput.writeLong(s_flow);

    }

    //从流中反序列化数据
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        u_flow=dataInput.readLong();
        d_flow=dataInput.readLong();
        s_flow=dataInput.readLong();
    }

    /*
    为了将flowsumbean对象写入文件中，需要重写tostring方法
    toString()方法返回反映这个对象的字符串
    因为toString方法是Object里面已经有了的方法，而所有类都是继承Object，所以“所有对象都有这个方法”。
    它通常只是为了方便输出，比如System.out.println(xx)，括号里面的“xx”如果不是String类型的话，就自动调用xx的toString()方法
    总而言之，它只是sun公司开发java的时候为了方便所有类的字符串操作而特意加入的一个方法
     */
    @Override
    public String toString() {
        return String.format("%d\t%d\t%d", u_flow, d_flow, s_flow);
    }



    @Override
    public int compareTo(FlowSumBean o) {
       return s_flow>o.getS_flow()?-1:1;
    }
}

