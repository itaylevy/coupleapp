package app.itay.coupleapp.fragments;


import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import app.itay.coupleapp.R;
import app.itay.coupleapp.adapters.RVAdapter;
import app.itay.coupleapp.models.Chore;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChoresFragment extends Fragment {


    public ChoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chores, container, false);

        FloatingActionButton addChoreButton = (FloatingActionButton) view.findViewById(R.id.fab_new_task);
        addChoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openAddChoreDialog();
            }
        });

        final ArrayList<Chore> chores = new ArrayList<>();
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv.setLayoutManager(llm);

        chores.add(new Chore("Clean the dishes", "100", "Itay", R.drawable.dishes));
        chores.add(new Chore("Take out the garbage", "20", "Shiran", R.drawable.trash));
        chores.add(new Chore("Do laundry", "250", "Shiran", R.drawable.laundry));
        chores.add(new Chore("Cook", "250", "Shiran", R.drawable.cook));
        RVAdapter adapter = new RVAdapter(chores, getContext());
        rv.setAdapter(adapter);
        return view;
    }

    private void openAddChoreDialog() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_new_chore);
        dialog.setTitle(getContext().getString(R.string.new_chore_title));
        dialog.setCancelable(false);

        dialog.findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Task creation will bw implemented soon", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
