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
import app.itay.coupleapp.adapters.RewardsRVAdapter;
import app.itay.coupleapp.controllers.ChoresController;
import app.itay.coupleapp.models.Reward;


/**
 * A simple {@link Fragment} subclass.
 */
public class RewardsFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_rewards, container, false);

        FloatingActionButton addChoreButton = (FloatingActionButton) view.findViewById(R.id.fab_new_task);
        addChoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mController.startTaskActivityCreateReward();
            }
        });

        final ArrayList<Reward> rewards = new ArrayList<>();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_rewards);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        rewards.add(new Reward("Beer with friends", "1500", "Itay", R.drawable.shopping));
        rewards.add(new Reward("Shopping", "2500", "Shiran", R.drawable.shopping));
        rewards.add(new Reward("Vacation in Paris", "6000", "Shiran", R.drawable.paris1));
        rewards.add(new Reward("Playstation", "1000", "Itay", R.drawable.playstaion));
        RewardsRVAdapter adapter = new RewardsRVAdapter(rewards, getContext(), mController);
        rv.setAdapter(adapter);

        return view;
    }

}
