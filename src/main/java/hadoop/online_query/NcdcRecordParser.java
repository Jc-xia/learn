package hadoop.online_query;

import org.apache.hadoop.io.Text;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NcdcRecordParser {
    private static final int MISSING_TEMP= 9999;

    private String year;
    private int airTemp;
    private String quality;
    private String stationId;
    private Date observationDate;

    public void parse(String record){
        year = record.substring(15,19);
        String airTempString;
        stationId =record.substring(4,10)+"-"+record.substring(10,15);

        String str=record.substring(15,25);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhh");
        try {
            observationDate = sdf.parse(str);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(record.charAt(87) == '+'){
            airTempString = record.substring(88,92);
        }else {
            airTempString = record.substring(87,92);
        }

        airTemp = Integer.parseInt(airTempString);
        quality = record.substring(92,93);


    }
@Test
    public void test(){
        parse("0029029070999991901010106004+64333+023450FM-12+000599999V0202701N015919999999N0000001N9-00781+99999102001ADDGF108991999999999999999999");
    }

    public void parse(Text record){
        parse(record.toString());
    }

    public boolean isValidTemp(){
        return airTemp != MISSING_TEMP && quality.matches("[01459]");
    }

    public String getYear() {
        return year;
    }

    public String getQuality() {
        return quality;
    }

    public int getAirTemp() {
        return airTemp;
    }

    public String getStationId() {
        return stationId;
    }

    public Date getObservationDate() {
        return observationDate;
    }
}
