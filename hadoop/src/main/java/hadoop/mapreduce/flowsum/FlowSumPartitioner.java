package hadoop.mapreduce.flowsum;

import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

public class FlowSumPartitioner extends Partitioner {

    HashMap<String, Integer> area = new HashMap();

    {
        area.put("123",0);
        area.put("124",1);
        area.put("125",2);
        area.put("126",3);
        area.put("127",4);
    }


    @Override
    public int getPartition(Object o, Object o2, int i) {
        int areacode = area.get(o.toString())==null?5:area.get(o.toString());
        return areacode;
    }
}
