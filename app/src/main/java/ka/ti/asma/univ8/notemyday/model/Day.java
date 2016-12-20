package ka.ti.asma.univ8.notemyday.model;

import android.content.ContentValues;

import java.io.Serializable;
import java.util.Date;

import ka.ti.asma.univ8.notemyday.dao.CriteriaDayDAO;
import ka.ti.asma.univ8.notemyday.dao.DBHelper;

/**
 * Created by Asma kouaouci on 18/12/2016.
 */
public class Day implements Serializable {

    private Date date;

    public String getDateString() {
        return dateString;
    }

    private String dateString;

    public Day() {

    };
    public Day(Date date) {
        this.date = date;
        this.dateString = helperDate.dateFormatString(date);
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

    public ContentValues contentValues()
    {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_DAY_ID, getDateString());
        return values;
    }

}


