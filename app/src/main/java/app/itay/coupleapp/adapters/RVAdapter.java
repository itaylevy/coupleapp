package app.itay.coupleapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import app.itay.coupleapp.R;
import app.itay.coupleapp.controllers.ChoresController;
import app.itay.coupleapp.models.Chore;

/**
 * Created by itay.levy on 3/5/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder>{

    private List<Chore> mPersons;
    private Context mContext;
    private ChoresController mController;

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

    public RVAdapter(List<Chore> persons, Context context, ChoresController controller){
        mPersons = persons;
        mContext= context;
        mController = controller;
    }

    @Override
    public int getItemCount() {
        return mPersons.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chores_card_view, viewGroup, false);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.chore_toolbar);
        toolbar.inflateMenu(R.menu.menu_task_card);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mController.startTaskActivityChoreEdit(mPersons.get(i).getTitle());
            }
        });

        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {

        personViewHolder.personName.setText(mPersons.get(i).getTitle());
        personViewHolder.personAge.setText(mPersons.get(i).getCoins());
        personViewHolder.subTitle.setText(mPersons.get(i).getCreator()+" hours left");

        final BitmapDrawable bitmapDrawable = (BitmapDrawable) personViewHolder.personPhoto.getDrawable();

        Palette.generateAsync(bitmapDrawable.getBitmap(), new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                int bgColor = palette.getMutedColor(mContext.getResources().getColor(android.R.color.black));
//                personViewHolder.personPhoto.setBackgroundColor(bgColor);
            }
        });
//        Palette.from(bitmapDrawable.getBitmap()).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
//                if (vibrantSwatch != null) {
//                    mContext.getResources().getDrawable(R.drawable.rectangle_background).setTint(vibrantSwatch.getPopulation());
//                }
//            }
//        });

        if(mPersons.get(i).getImgSrc() != 0) {
            personViewHolder.personPhoto.setImageResource(mPersons.get(i).getImgSrc());
        } else if (mPersons.get(i).getImgPath() != null) {
            personViewHolder.personPhoto.setImageURI(Uri.parse(mPersons.get(i).getImgPath()));
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}