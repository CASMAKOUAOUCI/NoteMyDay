package ka.ti.asma.univ8.notemyday;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ka.ti.asma.univ8.notemyday.dao.CriteriaDayDAO;
import ka.ti.asma.univ8.notemyday.dao.DayDAO;
import ka.ti.asma.univ8.notemyday.model.CriteriaDay;
import ka.ti.asma.univ8.notemyday.model.Day;


/**
 * A simple {@link Fragment} subclass.
 */
public class DaysHistory extends ListFragment {

    private List<Day> dayList;
    private DayDAO dayDAO;
    private CriteriaDayDAO criteriaDayDAO;
    
    public DaysHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_days_history, container, false);
        this.getActivity().setTitle("Mon historique");

        criteriaDayDAO = new CriteriaDayDAO(this.getContext());

        if (savedInstanceState == null)
        {
            dayDAO = new DayDAO(this.getContext());
            dayList = dayDAO.getAllDays();
            dayDAO.close();
        }

        if (dayList.size() > 0 )
        {
            for (int i = 0 ; i< dayList.size();i++)
            {
                Day day = dayList.get(i);
                List<CriteriaDay> criteriasDayList = criteriaDayDAO.getCriteriaDayOfDay(day.getDateString());
                float rating = 0;
                for (int j = 0 ; j< criteriasDayList.size();j++)
                {
                    CriteriaDay cday = criteriasDayList.get(j);
                    rating += cday.getRating();
                }
                day.setRating(rating/criteriasDayList.size());
            }
            // créer l'adapteur pour la listeView
            List<Object> dayListObject = (List<Object>)(List<?>) dayList;
            setListAdapter(new CustomAdapter(this.getActivity(), dayListObject,0,this));
        }
        criteriaDayDAO.close();

        return view;
    }
}
