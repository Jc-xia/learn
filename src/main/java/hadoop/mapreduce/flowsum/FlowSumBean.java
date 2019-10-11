package hadoop.mapreduce.flowsum;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowSumBean implements Writable {
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
}

