package app.itay.coupleapp.controllers;

import app.itay.coupleapp.models.Chore;
import app.itay.coupleapp.models.Goal;
import app.itay.coupleapp.models.Reward;

/**
 * Created by isaturina on 3/6/17.
 */

public interface ChoresController {

    void startTaskActivityChoreCreate();

    void startTaskActivityChoreEdit(Chore chore);

    void startTaskActivityCreateReward();

    void startTaskActivityEditReward(Reward reward);

    void startTaskActivityCreateGoal();

    void startTaskActivityEditGoal(Goal goal);

    Chore getNewChore();

    void updateCoinsStatus(String coins);

}
