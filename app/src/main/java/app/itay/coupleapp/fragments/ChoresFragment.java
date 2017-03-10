package app.itay.coupleapp.fragments;


import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import app.itay.coupleapp.R;
import app.itay.coupleapp.adapters.RVAdapter;
import app.itay.coupleapp.controllers.ChoresController;
import app.itay.coupleapp.models.Chore;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChoresFragment extends Fragment {

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
        View view = inflater.inflate(R.layout.fragment_chores, container, false);

        FloatingActionButton addChoreButton = (FloatingActionButton) view.findViewById(R.id.fab_new_task);
        addChoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mController.startTaskActivityChoreCreate();
            }
        });

        final ArrayList<Chore> chores = new ArrayList<>();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        if (getActivity().getResources().getConfiguration().screenLayout == Configuration.SCREENLAYOUT_SIZE_LARGE
                || getActivity().getResources().getConfiguration().screenLayout == Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            rv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rv.setLayoutManager(new GridLayoutManager(getContext(), 4));
        } else {
            rv.setLayoutManager(new GridLayoutManager(getContext(), 1));
        }

//        LinearLayoutManager llm = new LinearLayoutManager(getContext());
//        rv.setLayoutManager(llm);
        chores.add(new Chore("Clean the dishes", "100", "itay", "2", R.drawable.dishes));
        chores.add(new Chore("Take out the garbage", "20", "itay", "3", R.drawable.trash));
        chores.add(new Chore("Do laundry", "250", "itay", "5", R.drawable.laundry));
        chores.add(new Chore("Cook", "250", "itay", "2", R.drawable.cook));
        if (mController.getNewChore() != null) {
            chores.add(mController.getNewChore());
        }
        RVAdapter adapter = new RVAdapter(chores, getContext(), mController);
        rv.setAdapter(adapter);
        return view;
    }


}
