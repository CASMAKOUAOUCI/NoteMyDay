package ka.ti.asma.univ8.notemyday;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.RatingBar;

import java.io.Serializable;

import ka.ti.asma.univ8.notemyday.model.CriteriaDay;

public class CriteriaDayActivity extends AppCompatActivity implements Serializable{

    CriteriaDay criteriaDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criteria_day);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        updateActivity();
    }

    private void updateActivity() {

        Intent intent = getIntent();
        criteriaDay = (CriteriaDay) intent.getSerializableExtra("CriteriaDay");

        if (criteriaDay != null){
            this.setTitle(criteriaDay.getName());
            RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBare_critiria_day);
            ratingBar.setRating(criteriaDay.getRating());
            EditText textDescription = (EditText) findViewById(R.id.editText_critiria_day);
            textDescription.setText(criteriaDay.getDescription());
        }
    }
}
