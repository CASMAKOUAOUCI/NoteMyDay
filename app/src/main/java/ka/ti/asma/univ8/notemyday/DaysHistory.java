package ka.ti.asma.univ8.notemyday;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ka.ti.asma.univ8.notemyday.dao.DayDAO;
import ka.ti.asma.univ8.notemyday.model.CriteriaDay;
import ka.ti.asma.univ8.notemyday.model.Day;


/**
 * A simple {@link Fragment} subclass.
 */
public class DaysHistory extends ListFragment {

    private List<Day> dayList;
    private DayDAO dayDAO;

    public DaysHistory() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_days_history, container, false);
        dayDAO = new DayDAO(this.getContext());

        dayList = dayDAO.getAllDays();
        TextView textView = (TextView) view.findViewById(R.id.nav_fragment_days_history_textView);

        if (dayList.size() > 0 )
        {
            // créer l'adapteur pour la listeView
            ArrayAdapter<Day> listViewAdapter =  new ArrayAdapter<Day>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    dayList
            );
            setListAdapter(listViewAdapter); //setter l'adapteur a la listeView
            textView.setVisibility(View.GONE);
        }else
        {
            textView.setVisibility(View.VISIBLE);
        }

        return view;
    }
}
