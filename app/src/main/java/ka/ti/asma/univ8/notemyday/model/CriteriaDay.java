package ka.ti.asma.univ8.notemyday.model;

import android.content.ContentValues;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ka.ti.asma.univ8.notemyday.dao.DBHelper;

/**
 * Created by Asma kouaouci on 18/12/2016.
 */

public class CriteriaDay implements Serializable {

    private  Date date;



    private  long id;
    private  String dateString;
    private  String name;
    private  String description;
    private  float rating;
    private  Day day;

    public CriteriaDay() {

    }

    public CriteriaDay(Date date, String name, String description, float rating) {

        this.date = date;
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.dateString = helperDate.dateFormatString(date);
    }

    public String toString() {
        return name + "   " + rating;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRating() {
        return rating;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
    public long getId() {
        return id;
    }
    public String getDateString() {
        return dateString;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public ContentValues contentValue()
    {
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_CRITERIADAY_NAME, name);
        values.put(DBHelper.COLUMN_CRITERIADAY_DESCRIPTION, description);
        values.put(DBHelper.COLUMN_CRITERIADAY_DAY_ID, this.getDateString());
        values.put(DBHelper.COLUMN_CRITERIADAY_RATING, rating);
        return values;
    }

}

