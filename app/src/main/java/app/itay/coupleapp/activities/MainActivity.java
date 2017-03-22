package app.itay.coupleapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.Serializable;

import app.itay.coupleapp.Constants;
import app.itay.coupleapp.R;
import app.itay.coupleapp.adapters.SimpleFragmentPagerAdapter;
import app.itay.coupleapp.controllers.ChoresController;
import app.itay.coupleapp.models.Chore;
import app.itay.coupleapp.models.Goal;
import app.itay.coupleapp.models.Reward;


public class MainActivity extends AppCompatActivity implements ChoresController {
    private SimpleFragmentPagerAdapter simpleFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences(Constants.PREFS_FILE, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Constants.CURRENT_USER, "Anonymous");
        editor.commit();

        simpleFragmentPagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
//

        // Create an adapter that knows which fragment should be shown on each page
        viewPager.setAdapter(simpleFragmentPagerAdapter);
        // Set the adapter onto the view pager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_list_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_favorite_black_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_security_black_24dp);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_trophy);
        ColorStateList colors;
        if (Build.VERSION.SDK_INT >= 23) {
            colors = getResources().getColorStateList(R.color.tab_icon, getTheme());
        } else {
            colors = getResources().getColorStateList(R.color.tab_icon);
        }

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            Drawable icon = tab.getIcon();

            if (icon != null) {
                icon = DrawableCompat.wrap(icon);
                DrawableCompat.setTintList(icon, colors);
            }
        }

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
    public void startTaskActivityChoreEdit(Chore chore) {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra(Constants.TAG, Constants.TAG_EDIT_CHORE);
        intent.putExtra(Constants.CHORE, chore);
        intent.putExtra("menu", R.menu.menu_edit_task);
        startActivity(intent);
    }

    @Override
    public void startTaskActivityCreateReward() {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra(Constants.TAG, Constants.TAG_CREATE_REWARD);
        intent.putExtra(Constants.TITLE, "Create new reward");
        intent.putExtra("menu", R.menu.menu_create_task);
        startActivity(intent);
    }

    @Override
    public void startTaskActivityEditReward(Reward reward) {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra(Constants.TAG, Constants.TAG_EDIT_REWARD);
        intent.putExtra(Constants.TITLE, (Serializable) reward);
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
    public void startTaskActivityEditGoal(Goal goal) {
        Intent intent = new Intent(this, TaskActivity.class);
        intent.putExtra(Constants.TAG, Constants.TAG_EDIT_GOAL);
        intent.putExtra(Constants.GOAL, goal);
        intent.putExtra("menu", R.menu.menu_edit_task);
        startActivity(intent);
    }

    @Override
    public Chore getNewChore() {
        return (Chore) getIntent().getSerializableExtra(Constants.NEW_CHORE);
    }

    @Override
    public void updateCoinsStatus(String coins) {
        Toast.makeText(this, "Congtart! You received " + coins + " coins!", Toast.LENGTH_LONG).show();
    }
}