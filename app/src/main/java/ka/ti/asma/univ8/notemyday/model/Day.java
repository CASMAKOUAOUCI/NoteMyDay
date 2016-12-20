package ka.ti.asma.univ8.notemyday.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Asma kouaouci on 18/12/2016.
 */
public class Day {

    private Date date;

    public String getDateString() {
        return dateString;
    }

    private String dateString;
    private  CriteriaDay[] criteriasDay;

    public Day() {

    };
    public Day(Date date, CriteriaDay[] criteriasDay) {
        this.date = date;
        this.criteriasDay = criteriasDay;
    }
    public CriteriaDay[] getCriteriasDay() {
        return criteriasDay;
    }

    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String dateFormatString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(getDate());
        return date;
    }
}


