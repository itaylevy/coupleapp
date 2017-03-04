package app.itay.coupleapp;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itayl on 04/03/2017.
 */

public class ChoresCardViewAdapter extends ArrayAdapter<Chore> {

    public ChoresCardViewAdapter(Activity context, ArrayList<Chore> chores) {
        super(context, 0, chores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.fragment_chores, parent, false);
        }

        Chore currentChore=getItem(position);
        TextView title=(TextView) listItemView.findViewById(R.id.txt_title_chores_card);
        TextView subTitle=(TextView) listItemView.findViewById(R.id.txt_subtitle_chores_card);
        TextView coins=(TextView) listItemView.findViewById(R.id.chore_card_coins);
        ImageView cardImg=(ImageView) listItemView.findViewById(R.id.chore_img_card_src);

        title.setText(currentChore.getmTitle());
        subTitle.setText("Created by "+currentChore.getmCreator());
        coins.setText(currentChore.getmCoins());
        cardImg.setImageResource(currentChore.getmImgSrc());
        return listItemView;
    }



}
