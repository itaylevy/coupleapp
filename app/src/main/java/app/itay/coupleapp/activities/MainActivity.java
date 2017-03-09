package app.itay.coupleapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import app.itay.coupleapp.Constants;
import app.itay.coupleapp.R;
import app.itay.coupleapp.adapters.SimpleFragmentPagerAdapter;
import app.itay.coupleapp.controllers.ChoresController;

public class MainActivity extends AppCompatActivity implements ChoresController {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        viewPager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));
        // Set the adapter onto the view pager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        // setupTabLayout(tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void startTaskActivityChoreCreate() {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra(Constants.TAG, Constants.TAG_CREATE_CHORE);
        intent.putExtra("title", "Add new chore");
        intent.putExtra("menu", R.menu.menu_create_task);
        startActivity(intent);
    }

    @Override
    public void startTaskActivityChoreEdit(String taskName) {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra(Constants.TAG, Constants.TAG_EDIT_CHORE);
        intent.putExtra("title", taskName);
        intent.putExtra("menu", R.menu.menu_edit_task);
        startActivity(intent);
    }

    @Override
    public void startTaskActivityCreateReward() {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra(Constants.TAG, Constants.TAG_CREATE_REWARD);
        intent.putExtra("title", "Create new reward");
        intent.putExtra("menu", R.menu.menu_create_task);
        startActivity(intent);
    }

    @Override
    public void startTaskActivityEditReward(String taskName) {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra(Constants.TAG, Constants.TAG_EDIT_REWARD);
        intent.putExtra("title", taskName);
        intent.putExtra("menu", R.menu.menu_edit_task);
        startActivity(intent);
    }

    @Override
    public void startTaskActivityCreateGoal() {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra(Constants.TAG, Constants.TAG_CREATE_GOAL);
        intent.putExtra("title", "Create new goal");
        intent.putExtra("menu", R.menu.menu_create_task);
        startActivity(intent);
    }

    @Override
    public void startTaskActivityEditGoal(String taskName) {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra(Constants.TAG, Constants.TAG_EDIT_GOAL);
        intent.putExtra("title", taskName);
        intent.putExtra("menu", R.menu.menu_edit_task);
        startActivity(intent);
    }
}