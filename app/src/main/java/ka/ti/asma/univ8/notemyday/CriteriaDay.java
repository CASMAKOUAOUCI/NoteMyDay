package ka.ti.asma.univ8.notemyday;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Asma kouaouci on 18/12/2016.
 */

public class CriteriaDay implements Serializable {


    private final Date date;
    private final String name;
    private final String description;
    private final float rating;

    public CriteriaDay(Date date, String name, String description, float rating) {

        this.date = date;
        this.name = name;
        this.description = description;
        this.rating = rating;
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

    public float getRating() {
        return rating;
    }

}

