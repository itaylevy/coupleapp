package app.itay.coupleapp.controllers;

import app.itay.coupleapp.models.Chore;

/**
 * Created by isaturina on 3/6/17.
 */

public interface ChoresController {
    void startTaskActivityChoreCreate();
    void startTaskActivityChoreEdit(Chore chore);
    void startTaskActivityCreateReward();
    void startTaskActivityEditReward(String taskName);
    void startTaskActivityCreateGoal();
    void startTaskActivityEditGoal(String taskName);
    Chore getNewChore();

    void updateCoinsStatus(String coins);

}
