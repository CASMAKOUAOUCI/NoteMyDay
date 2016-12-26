package univ8.notemyday.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

import univ8.notemyday.R;
import univ8.notemyday.dao.CriteriaDayDAO;
import univ8.notemyday.model.CriteriaDay;

public class CriteriaDayActivity extends AppCompatActivity implements Serializable{

    CriteriaDay criteriaDay;
    private CriteriaDayDAO criteriaDayDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criteria_day);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        criteriaDayDAO = new CriteriaDayDAO(getBaseContext());
        Intent intent = getIntent();
        criteriaDay = (CriteriaDay) intent.getSerializableExtra("CriteriaDay");
        refreshActivity();

        Button cancelButton = (Button)findViewById(R.id.activity_new_criteria_day_cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
            }
        });

        Button updateButton = (Button)findViewById(R.id.activity_new_criteria_day_saveButton);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCriteriaDay();
                finishActivityCD();
            }
        });

        List<CriteriaDay> list = criteriaDayDAO.getCriteriaDayOfDay(criteriaDay.getDateString());

        Button deleteButton = (Button)findViewById(R.id.activity_new_criteria_day_deleteButton);
        if (list.size()>1) deleteButton.setEnabled(true);
        else deleteButton.setEnabled(false);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criteriaDayDAO = new CriteriaDayDAO(getBaseContext());
                deleteCriteriaDay();
                finishActivityCD();

            }
        });
    }

    private void finishActivityCD() {
        Intent returnIntent = new Intent();
        setResult(this.RESULT_OK,returnIntent);
        finish();
    }

    private void updateCriteriaDay() {
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBare_critiria_day);
        criteriaDay.setRating(ratingBar.getRating());
        EditText textDescription = (EditText) findViewById(R.id.editText_critiria_day);
        criteriaDay.setDescription(textDescription.getText().toString());
        criteriaDayDAO.updateCriteriaDay(criteriaDay);
        criteriaDayDAO.close();
    }

    private void deleteCriteriaDay() {
        criteriaDayDAO.deleteCriteriaDay(criteriaDay);
        criteriaDayDAO.close();
    }

    private void refreshActivity() {
        Intent intent = getIntent();
        if (criteriaDay != null){
            TextView titleView = (TextView) findViewById(R.id.activity_criteria_day_toolbar_title);
            titleView.setText(criteriaDay.getName());
            RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBare_critiria_day);
            ratingBar.setRating(criteriaDay.getRating());
            EditText textDescription = (EditText) findViewById(R.id.editText_critiria_day);
            textDescription.setText(criteriaDay.getDescription());
        }

    }
}
