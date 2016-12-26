package univ8.notemyday.model;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by abdelmadjidchaibi on 20/12/2016.
 */

public class helperDate {
    public static String dateFormatString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String _date = sdf.format(date);
        return _date;
    }

    public static String dateLongFormatString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE dd MMMM yyyy");
        String _date = sdf.format(date);
        return _date;
    }

    public static Date dateFromString(String dateString)  {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date _date = dateFormat.parse(dateString);
            return _date;

        } catch (ParseException ex) {
            // handle parsing exception if date string was different from the pattern applying into the SimpleDateFormat contructor
        }
        return null;
    }

}