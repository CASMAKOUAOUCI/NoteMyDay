package ka.ti.asma.univ8.notemyday;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.Serializable;
import java.util.Date;

import ka.ti.asma.univ8.notemyday.model.CriteriaDay;
import ka.ti.asma.univ8.notemyday.model.Day;


/**
 * A simple {@link ListFragment} subclass.
 */
public class MydayFragment extends ListFragment {

    public MydayFragment() {
        // Required empty public constructor
    }
    private Day day;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myday, container, false);

        Date date = new Date();

        CriteriaDay criteria1 = new CriteriaDay(date,"Santé","En trés bonne forme",5);
        CriteriaDay criteria2 = new CriteriaDay(date,"culture","j'ai lu 15 page du livre 'comment etre heureux'",3);
        CriteriaDay criteria3 = new CriteriaDay(date,"Sport","j'ai fais 20 min de sport",2);

        CriteriaDay[] criteriasDay = {criteria1,criteria2,criteria3}; // initializer une liste statique

        day = new Day(date,criteriasDay);

        // créer l'adapteur pour la listeView
        ArrayAdapter<CriteriaDay> listViewAdapter =  new ArrayAdapter<CriteriaDay>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                criteriasDay
        );
        setListAdapter(listViewAdapter); //setter l'adapteur a la listeView
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getContext(), CriteriaDayActivity.class);
        CriteriaDay criteriaDay = day.getCriteriasDay()[position];
        intent.putExtra("CriteriaDay", (Serializable) criteriaDay);
        startActivity(intent);
    }
}
