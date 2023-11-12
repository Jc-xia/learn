package hadoop.online_query;

import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

public class RowKeyConverter {

    private static final int STATION_ID_LENGTH = 12;

    /**
     * @return A row key whose format is: <station_id> <reverse_order_timestamp>
     */
    public static byte[] makeObservationRowKey(String stationId,
                                               long observationTime) {
        byte[] row = new byte[STATION_ID_LENGTH + Bytes.SIZEOF_LONG];
        Bytes.putBytes(row, 0, Bytes.toBytes(stationId), 0, STATION_ID_LENGTH);
        long reverseOrderTimestamp = Long.MAX_VALUE - observationTime;
        Bytes.putLong(row, STATION_ID_LENGTH, reverseOrderTimestamp);
        return row;
    }
    @Test
    public void test(){
        NcdcRecordParser parser = new NcdcRecordParser();
        parser.parse("0029029070999991901010106004+64333+023450FM-12+000599999V0202701N015919999999N0000001N9-00781+99999102001ADDGF108991999999999999999999");
        byte[] rowKey = RowKeyConverter.makeObservationRowKey(parser.getStationId(),
                parser.getObservationDate().getTime());
    }

}
