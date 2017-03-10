package app.itay.coupleapp.adapters;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.itay.coupleapp.Constants;
import app.itay.coupleapp.R;
import app.itay.coupleapp.controllers.ChoresController;
import app.itay.coupleapp.models.Chore;

/**
 * Created by itay.levy on 3/5/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    private List<Chore> mChores;
    private Context mContext;
    private ChoresController mController;
    private Toolbar mToolbar;
    private View mView;

    static class PersonViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView coins;
        private ImageView picture;
        private TextView subTitle;

        PersonViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.txt_title_chores_card);
            coins = (TextView) itemView.findViewById(R.id.chore_card_coins);
            picture = (ImageView) itemView.findViewById(R.id.chore_img_card_src);
            subTitle = (TextView) itemView.findViewById(R.id.txt_subtitle_chores_card);
        }
    }

    public RVAdapter(List<Chore> chores, Context context, ChoresController controller) {
        mChores = chores;
        mContext = context;
        mController = controller;
    }

    @Override
    public int getItemCount() {
        return mChores.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chores_card_view, viewGroup, false);
        mToolbar = (Toolbar) mView.findViewById(R.id.chore_toolbar);
        mToolbar.inflateMenu(R.menu.menu_task_card);

        return new PersonViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, final int position) {

        final int i = position;

        personViewHolder.title.setText(mChores.get(i).getTitle());
        personViewHolder.coins.setText(mChores.get(i).getCoins());
        personViewHolder.subTitle.setText(String.format(mContext.getString(R.string.chore_info_created_by),
                mChores.get(i).getCreator()));


        final BitmapDrawable bitmapDrawable = (BitmapDrawable) personViewHolder.picture.getDrawable();

        Palette.generateAsync(bitmapDrawable.getBitmap(), new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                final Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                if (vibrantSwatch != null) {
                    mContext.getResources().getDrawable(R.drawable.rectangle_background).setTint(vibrantSwatch.getPopulation());
                }
            }
        });


        if (mChores.get(i).getImgSrc() != 0) {
            personViewHolder.picture.setImageResource(mChores.get(i).getImgSrc());
        } else if (mChores.get(i).getImgPath() != null) {
            personViewHolder.picture.setImageURI(Uri.parse(mChores.get(i).getImgPath()));
        }
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit:
                        mController.startTaskActivityChoreEdit(mChores.get(i));
                        break;
                    case R.id.delete:
                        String user = mContext.getSharedPreferences(Constants.PREFS_FILE, Context.MODE_PRIVATE).getString(Constants.CURRENT_USER, "");
                        if (!user.equals(mChores.get(i).getCreator())) {
                            Toast.makeText(mContext, "You cannot delete this task", Toast.LENGTH_SHORT).show();
                        }
                        mChores.remove(i);
                        notifyDataSetChanged();
                        break;
                    case R.id.redeem:
                        mController.updateCoinsStatus(mChores.get(i).getCoins());
                        mChores.remove(i);
                        notifyItemRemoved(i);
                        break;
                    default:
                        return false;
                }
                return false;
            }
        });

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mController.startTaskActivityChoreEdit(mChores.get(i));
            }
        });

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}