package ka.ti.asma.univ8.notemyday;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import ka.ti.asma.univ8.notemyday.dao.CriteriaDayDAO;
import ka.ti.asma.univ8.notemyday.dao.DBHelper;
import ka.ti.asma.univ8.notemyday.dao.DayDAO;
import ka.ti.asma.univ8.notemyday.model.CriteriaDay;
import ka.ti.asma.univ8.notemyday.model.Day;
import ka.ti.asma.univ8.notemyday.model.helperDate;


/**
 * A simple {@link ListFragment} subclass.
 */
public class MydayFragment extends ListFragment {

    public MydayFragment() {
        // Required empty public constructor
    }
    private Day day;
    private CriteriaDayDAO criteriaDayDAO;
    private DayDAO dayDAO;
    private List<CriteriaDay> criteriasDayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_myday, container, false);
        this.getActivity().setTitle("Ma Journée");

        criteriaDayDAO = new CriteriaDayDAO(this.getContext());
        dayDAO = new DayDAO(this.getContext());
        Date date = new Date();

        day = dayDAO.getDayById(helperDate.dateFormatString(date));

        if (day == null)
        {
            day = new Day(date);
            dayDAO.insertDay(day);
        }

        criteriasDayList = criteriaDayDAO.getCriteriaDayOfDay(day.getDateString());

        if (criteriasDayList.size() == 0 )
        {
            addDefaultCriteriaDay();
        }

        // créer l'adapteur pour la listeView
        ArrayAdapter<CriteriaDay> listViewAdapter =  new ArrayAdapter<CriteriaDay>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                criteriasDayList
        );
        List<Object> criteriaListObject = (List<Object>)(List<?>) criteriasDayList;

        setListAdapter(new CustomAdapter(this.getActivity(), criteriaListObject,1,this));

       refreshRating(view);

        Button addCrtiteriaDay = (Button)view.findViewById(R.id.fragment_myday_addCriteriadayButton);
        addCrtiteriaDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(view.getContext(), NewCriteriaDayActivity.class);
                    getActivity().startActivityForResult(intent,10001);
            }
        });

    return view;
    }

    private void refreshRating(View view) {
        if (criteriasDayList.size() > 0 ) {
            float rating = 0;
            for (int i = 0; i < criteriasDayList.size(); i++) {
                CriteriaDay criteriaDay = criteriasDayList.get(i);
                rating += criteriaDay.getRating();
            }
            day.rating = rating / criteriasDayList.size();
            RatingBar ratingBar = (RatingBar) view.findViewById(R.id.nav_fragment_myday_ratingBar);
            ratingBar.setRating(day.rating);
        }
    }

    private void addDefaultCriteriaDay() {
        CriteriaDay criteria2 = new CriteriaDay(day.getDate(),"Santé","",3);
        CriteriaDay criteria1 = new CriteriaDay(day.getDate(),"Culture","",3);
        CriteriaDay criteria3 = new CriteriaDay(day.getDate(),"Sport","",3);

        criteriaDayDAO.insertCriteriaDay(criteria1);
        criteriaDayDAO.insertCriteriaDay(criteria2);
        criteriaDayDAO.insertCriteriaDay(criteria3);

        criteriasDayList = criteriaDayDAO.getCriteriaDayOfDay(day.getDateString());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        criteriaDayDAO.close();
        dayDAO.close();
    }
}
