package ka.ti.asma.univ8.notemyday;

import java.util.Date;

/**
 * Created by Asma kouaouci on 18/12/2016.
 */

public class CriteriaDay {


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
}

