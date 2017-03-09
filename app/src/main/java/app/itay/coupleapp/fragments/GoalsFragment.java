package app.itay.coupleapp.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.itay.coupleapp.R;
import app.itay.coupleapp.adapters.GoalsRVAdapter;
import app.itay.coupleapp.controllers.ChoresController;
import app.itay.coupleapp.models.Goal;


/**
 * A simple {@link Fragment} subclass.
 */
public class GoalsFragment extends Fragment {

    private ChoresController mController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mController = (ChoresController) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement ChoresController");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goals, container, false);

        FloatingActionButton addChoreButton = (FloatingActionButton) view.findViewById(R.id.fab_new_task);
        addChoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mController.startTaskActivityCreateGoal();
            }
        });
        final ArrayList<Goal> goals = new ArrayList<>();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_goals);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        goals.add(new Goal("Be Romantic", "100", "Shiran", R.drawable.dishes));
        goals.add(new Goal("Be Spontaneous", "300", "Shiran", R.drawable.trash));
        goals.add(new Goal("Be Supportive", "250", "Shiran", R.drawable.laundry));
        goals.add(new Goal("Be Attentive", "350", "Shiran", R.drawable.cook));
        GoalsRVAdapter adapter = new GoalsRVAdapter(goals, getContext(), mController);
        rv.setAdapter(adapter);
        return view;
    }

}
