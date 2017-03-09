package app.itay.coupleapp.adapters;

import android.content.Context;
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
import app.itay.coupleapp.models.Goal;

/**
 * Created by itayl on 09/03/2017.
 */

public class GoalsRVAdapter extends RecyclerView.Adapter<GoalsRVAdapter.GoalViewHolder>{
    private List<Goal> mGoals;
    private Context mContext;
    private ChoresController mController;

    static class GoalViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView goalTitle;
        private TextView goalCoins;
        private ImageView goalImgSrc;
        private TextView subTitle;

        GoalViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.chore_card_view);
            goalTitle = (TextView)itemView.findViewById(R.id.txt_title_chores_card);
            goalCoins = (TextView)itemView.findViewById(R.id.chore_card_coins);
            goalImgSrc = (ImageView)itemView.findViewById(R.id.chore_img_card_src);
            subTitle=(TextView) itemView.findViewById(R.id.txt_subtitle_chores_card);
        }
    }

    public GoalsRVAdapter(List<Goal> goals, Context context, ChoresController controller){
        mGoals = goals;
        mContext= context;
        mController = controller;
    }

    @Override
    public int getItemCount() {
        return mGoals.size();
    }

    @Override
    public GoalViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chores_card_view, viewGroup, false);
        Toolbar toolbar = (Toolbar) v.findViewById(R.id.chore_toolbar);
        toolbar.inflateMenu(R.menu.menu_task_card);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mController.startTaskActivityEditGoal(mGoals.get(i).getTitle());
            }
        });

        return new GoalViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GoalViewHolder goalViewHolder, int i) {

        goalViewHolder.goalTitle.setText(mGoals.get(i).getTitle());
        goalViewHolder.goalCoins.setText(mGoals.get(i).getCoins());
        goalViewHolder.goalImgSrc.setImageResource(mGoals.get(i).getImgSrc());
        goalViewHolder.subTitle.setText(String.format(mContext.getString(R.string.chore_info_created_by),
                mGoals.get(i).getCreator()));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
