package univ8.notemyday.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.Date;

import univ8.notemyday.R;
import univ8.notemyday.dao.CriteriaDayDAO;
import univ8.notemyday.model.CriteriaDay;

public class NewCriteriaDayActivity extends AppCompatActivity {

    private CriteriaDayDAO criteriaDayDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_criteria_day);
        setTitle("Nouveau Critére");

        Button annulerButton = (Button) findViewById(R.id.activity_new_criteria_day_annuler);
        annulerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    finish();
            }
        });;

        Button addCrtiteriaDay = (Button) findViewById(R.id.activity_new_criteria_day_saveButton);
        addCrtiteriaDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criteriaDayDAO = new CriteriaDayDAO(getBaseContext());

                EditText nameCriteriaDay = (EditText) findViewById(R.id.activity_new_criteria_day_EditText_Name);

                EditText descriptionCriteriaDay = (EditText) findViewById(R.id.activity_new_criteria_day_EditText_description);

                RatingBar ratingCriteriaDay = (RatingBar) findViewById(R.id.activity_new_criteria_day_ratingBar);

                if (nameCriteriaDay.getText() == null || ratingCriteriaDay.getRating() == 0) {
                    Toast.makeText(getBaseContext(), "Veuillez remplir tous les champs", Toast.LENGTH_LONG).show();

                } else
                {
                    CriteriaDay criteriaDay = new CriteriaDay(new Date(),nameCriteriaDay.getText().toString(),descriptionCriteriaDay.getText().toString(),ratingCriteriaDay.getRating());
                    criteriaDayDAO.insertCriteriaDay(criteriaDay);
                    criteriaDayDAO.close();
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
            }
        });;
    }
}
