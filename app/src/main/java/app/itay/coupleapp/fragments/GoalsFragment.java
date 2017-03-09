package app.itay.coupleapp.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.itay.coupleapp.R;
import app.itay.coupleapp.controllers.ChoresController;


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
        return view;
    }

}
