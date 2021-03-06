package univ8.notemyday.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.List;

import univ8.notemyday.R;
import univ8.notemyday.dao.CriteriaDayDAO;
import univ8.notemyday.dao.DayDAO;
import univ8.notemyday.model.CriteriaDay;
import univ8.notemyday.model.Day;

/**
 * A simple {@link Fragment} subclass.
 */
public class GrapheFragment extends Fragment {

    private DayDAO dayDAO;
    private CriteriaDayDAO criteriaDayDAO;

    private List<Day> dayList;

    public GrapheFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graphe, container, false);
        GraphView graph = (GraphView) view.findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
        });
        getActivity().setTitle("Historique Graphe");

        dayDAO = new DayDAO(this.getContext());
        criteriaDayDAO = new CriteriaDayDAO(this.getContext());

        dayList = dayDAO.getAllDays();
        int size = dayList.size();

        for (int i = 0; i < size ; i++) {
            Day day = dayList.get(i);

            List<CriteriaDay> criteriasDayList = criteriaDayDAO.getCriteriaDayOfDay(day.getDateString());
            float rating = 0;
            for (int j = 0 ; j< criteriasDayList.size();j++)
            {
                CriteriaDay cday = criteriasDayList.get(j);
                rating += cday.getRating();
            }
            day.setRating(rating/criteriasDayList.size());
            series.appendData(new DataPoint(i,day.getRating()),false,size);
        }
        dayDAO.close();
        criteriaDayDAO.close();

// set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(size);

// set manual Y bounds
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(5);

        graph.addSeries(series);
        return view;
    }
}
