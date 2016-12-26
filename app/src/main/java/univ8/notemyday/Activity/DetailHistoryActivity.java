package univ8.notemyday.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.EditText;
import android.widget.RatingBar;

import java.util.List;

import univ8.notemyday.R;
import univ8.notemyday.dao.CriteriaDayDAO;
import univ8.notemyday.model.CriteriaDay;
import univ8.notemyday.model.Day;

public class DetailHistoryActivity extends AppCompatActivity {

    private Day day;
    private CriteriaDayDAO criteriaDayDAO;
    private List<CriteriaDay> criteriasDayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_history);
        Intent intent = getIntent();
        day = (Day) intent.getSerializableExtra("Day");

        RatingBar ratingBar = (RatingBar) findViewById(R.id.activity_detail_history_ratingBar);
        ratingBar.setRating(day.getRating());

        criteriaDayDAO = new CriteriaDayDAO(this);
        criteriasDayList = criteriaDayDAO.getCriteriaDayOfDay(day.getDateString());
        setTitle(day.toString());

        EditText editText = (EditText) findViewById(R.id.activity_detail_history_EditText);
        editText.setText("");

        for (int i = 0; i < criteriasDayList.size();i++)
        {
            CriteriaDay cd = criteriasDayList.get(i);
            String title = cd.getName();
            String description = cd.getDescription();
            String rating = String.valueOf(cd.getRating());
            if (description.length() < 1)
            {
                editText.append(Html.fromHtml("<b>" + title + " " +rating+"/5"+"</b><br /><br />"));
            }else
            {
                editText.append(Html.fromHtml("<b>" + title + " " +rating+"/5"+"</b>" +  "<br />" +
                        "<small>" + description + "</small>" + "<br />"));
            }

        }
    }
}
