package hadoop.online_query;
import org.apache.hadoop.hbase.util.Bytes;


public class HBaseTemperatureQuery {

    public static final byte[] DATA_COLUMNFAMILY = Bytes.toBytes("data");
    public static final byte[] AIRTEMP_QUALIFIER = Bytes.toBytes("airtemp");
}
