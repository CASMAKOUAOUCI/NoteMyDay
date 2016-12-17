package ka.ti.asma.univ8.notemyday;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


/**
 * A simple {@link ListFragment} subclass.
 */
public class MydayFragment extends ListFragment {

    public MydayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_myday, container, false);

        String[] elements = {"Santé","Culture","sport"}; // initializer une liste statique

        // créer l'adapteur pour la listeView
        ArrayAdapter<String> listViewAdapter =  new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                elements
        );
        setListAdapter(listViewAdapter); //setter l'adapteur a la listeView
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(getContext(), CriteriaDayActivity.class);
        startActivity(intent);
    }
}
