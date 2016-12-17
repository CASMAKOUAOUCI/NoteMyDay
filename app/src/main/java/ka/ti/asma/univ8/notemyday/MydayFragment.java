package ka.ti.asma.univ8.notemyday;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MydayFragment extends Fragment {


    public MydayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myday, container, false);

        String[] elements = {"Santé","Culture","sport"}; // initializer une liste statique

        ListView listView = (ListView) view.findViewById(R.id.listView_fragment_myday); // récupérer la listView du layout

        // créer l'adapteur pour la listeView
        ArrayAdapter<String> listViewAdapter =  new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                elements
        );
        listView.setAdapter(listViewAdapter); //setter l'adapteur a la listeView

        return view;
    }

}
