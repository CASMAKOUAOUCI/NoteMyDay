package ka.ti.asma.univ8.notemyday.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by abdelmadjidchaibi on 20/12/2016.
 */

public class helperDate {
    public static String dateFormatString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String _date = sdf.format(date);
        return _date;
    }
}
