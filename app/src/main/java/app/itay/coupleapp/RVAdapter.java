package app.itay.coupleapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by itay.levy on 3/5/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    List<Chore> persons;
    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.chore_card_view);
            personName = (TextView)itemView.findViewById(R.id.txt_title_chores_card);
            personAge = (TextView)itemView.findViewById(R.id.chore_card_coins);
            personPhoto = (ImageView)itemView.findViewById(R.id.chore_img_card_src);
        }
    }
    RVAdapter(List<Chore> persons){
        this.persons = persons;
    }
    @Override
    public int getItemCount() {
        return persons.size();
    }
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chores_card_view, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }
    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).getmTitle());
        personViewHolder.personAge.setText(persons.get(i).getmCoins());
        personViewHolder.personPhoto.setImageResource(persons.get(i).getmImgSrc());
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}