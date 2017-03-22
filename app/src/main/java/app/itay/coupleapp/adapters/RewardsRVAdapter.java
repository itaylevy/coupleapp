package app.itay.coupleapp.adapters;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import app.itay.coupleapp.Constants;
import app.itay.coupleapp.R;
import app.itay.coupleapp.controllers.ChoresController;
import app.itay.coupleapp.events.GoalRedeemedEvent;
import app.itay.coupleapp.models.Reward;

/**
 * Created by itayl on 10/03/2017.
 */

public class RewardsRVAdapter extends RecyclerView.Adapter<RewardsRVAdapter.RewardViewHolder> {
    private List<Reward> mRewards;
    private Context mContext;
    private ChoresController mController;
    private Toolbar mToolbar;
    private View mView;

    static class RewardViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView goalTitle;
        private TextView goalCoins;
        private ImageView goalImgSrc;
        private TextView subTitle;

        RewardViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.chore_card_view);
            goalTitle = (TextView) itemView.findViewById(R.id.txt_title_chores_card);
            goalCoins = (TextView) itemView.findViewById(R.id.chore_card_coins);
            goalImgSrc = (ImageView) itemView.findViewById(R.id.chore_img_card_src);
            subTitle = (TextView) itemView.findViewById(R.id.txt_subtitle_chores_card);

        }
    }

    public RewardsRVAdapter(List<Reward> goals, Context context, ChoresController controller) {
        mRewards = goals;
        mContext = context;
        mController = controller;
    }
    @Override
    public int getItemCount() {
        return mRewards.size();
    }

    @Override
    public RewardsRVAdapter.RewardViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chores_card_view, viewGroup, false);
        mToolbar = (Toolbar) mView.findViewById(R.id.chore_toolbar);
        mToolbar.inflateMenu(R.menu.menu_task_card);

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mController.startTaskActivityEditReward(mRewards.get(i));
            }
        });

        return new RewardsRVAdapter.RewardViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final RewardsRVAdapter.RewardViewHolder goalViewHolder, int position) {

        final int i = position;

        goalViewHolder.goalTitle.setText(mRewards.get(i).getTitle());
        goalViewHolder.goalCoins.setText(mRewards.get(i).getCoins());
        goalViewHolder.goalImgSrc.setImageResource(mRewards.get(i).getImgSrc());
        goalViewHolder.subTitle.setText(String.format(mContext.getString(R.string.reward_created_by)+" "+
                mRewards.get(i).getCreator()));

        final BitmapDrawable bitmapDrawable = (BitmapDrawable) goalViewHolder.goalImgSrc.getDrawable();


        final Dialog dialog = new Dialog(mContext);
        dialog.setContentView(R.layout.badge_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimation;
        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if (mRewards.get(i).getImgSrc() != 0) {
            goalViewHolder.goalImgSrc.setImageResource(mRewards.get(i).getImgSrc());
        } else if (mRewards.get(i).getImgPath() != null) {
            goalViewHolder.goalImgSrc.setImageURI(Uri.parse(mRewards.get(i).getImgPath()));
        }

        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.edit:
                        mController.startTaskActivityEditReward(mRewards.get(i));
                        break;
                    case R.id.delete:
                        String user = mContext.getSharedPreferences(Constants.PREFS_FILE, Context.MODE_PRIVATE).getString(Constants.CURRENT_USER, "");
                        if (!user.equals(mRewards.get(i).getCreator())) {
                            Toast.makeText(mContext, "You cannot delete this task", Toast.LENGTH_SHORT).show();
                        }
                        mRewards.remove(i);
                        notifyDataSetChanged();
                        break;
                    case R.id.redeem:
                        dialog.show();
                        EventBus.getDefault().post(new GoalRedeemedEvent());
                        mController.updateCoinsStatus(mRewards.get(i).getCoins());
                        mRewards.remove(i);
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
                mController.startTaskActivityEditReward(mRewards.get(i));
            }
        });


    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
