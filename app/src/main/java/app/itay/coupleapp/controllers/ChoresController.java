package app.itay.coupleapp.controllers;

import app.itay.coupleapp.models.Chore;
import app.itay.coupleapp.models.Goal;

/**
 * Created by isaturina on 3/6/17.
 */

public interface ChoresController {

    void startTaskActivityChoreCreate();
    void startTaskActivityChoreEdit(Chore chore);
    void startTaskActivityCreateReward();
    void startTaskActivityEditReward(String taskName);
    void startTaskActivityCreateGoal();
    void startTaskActivityEditGoal(Goal goal);
    Chore getNewChore();

    void updateCoinsStatus(String coins);

}
