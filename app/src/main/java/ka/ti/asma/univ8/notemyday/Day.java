package ka.ti.asma.univ8.notemyday;

import java.util.Date;

/**
 * Created by Asma kouaouci on 18/12/2016.
 */
public class Day {


    private final Date date;

    private final CriteriaDay[] criteriasDay;

    public Day(Date date, CriteriaDay[] criteriasDay) {
        this.date = date;
        this.criteriasDay = criteriasDay;
    }
    public CriteriaDay[] getCriteriasDay() {
        return criteriasDay;
    }
}


