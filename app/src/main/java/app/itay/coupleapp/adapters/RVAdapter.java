package app.itay.coupleapp.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.itay.coupleapp.R;
import app.itay.coupleapp.models.Chore;

/**
 * Created by itay.levy on 3/5/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    private List<Chore> mPersons;
    private Context mContext;

    static class PersonViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView personName;
        private TextView personAge;
        private ImageView personPhoto;
        private TextView subTitle;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.chore_card_view);
            personName = (TextView)itemView.findViewById(R.id.txt_title_chores_card);
            personAge = (TextView)itemView.findViewById(R.id.chore_card_coins);
            personPhoto = (ImageView)itemView.findViewById(R.id.chore_img_card_src);
            subTitle=(TextView) itemView.findViewById(R.id.txt_subtitle_chores_card);
        }
    }

    public RVAdapter(List<Chore> persons, Context context){
        mPersons = persons;
        mContext= context;
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chores_card_view, viewGroup, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(mPersons.get(i).getTitle());
        personViewHolder.personAge.setText(mPersons.get(i).getCoins());
        personViewHolder.personPhoto.setImageResource(mPersons.get(i).getImgSrc());
        personViewHolder.subTitle.setText(String.format(mContext.getString(R.string.chore_info_created_by),
                mPersons.get(i).getCreator()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}