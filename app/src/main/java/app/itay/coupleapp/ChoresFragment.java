package app.itay.coupleapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChoresFragment extends Fragment {

    private View v;


    public ChoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_chores, container, false);
        final ArrayList<Chore> chores= new ArrayList<>();
        ChoresCardViewAdapter adapter=new ChoresCardViewAdapter(getActivity(),chores);

        ListView listView = (ListView) v.findViewById(R.id.list);
        listView.setAdapter(adapter);

        chores.add(new Chore("Clean the dishes", "100", "Itay",R.drawable.dishes));
        chores.add(new Chore("Take out the garbage", "20", "Shiran",R.drawable.dishes));
        chores.add(new Chore("Do laundry", "250", "Shiran",R.drawable.dishes));
        chores.add(new Chore("Cook", "250", "Shiran",R.drawable.dishes));

        return v;
    }

}
