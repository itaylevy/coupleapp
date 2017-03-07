package app.itay.coupleapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

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
    public void startChoreActivityCreate() {
        Intent intent = new Intent(this, ChoreActivity.class);
        intent.putExtra("tag", "create");
        intent.putExtra("title", "Add new chore");
        intent.putExtra("menu", R.menu.menu_create_chore_card);
        startActivity(intent);
    }

    @Override
    public void startChoreActivityEdit(String taskName) {
        Intent intent = new Intent(this, ChoreActivity.class);
        intent.putExtra("tag", "edit");
        intent.putExtra("title", taskName);
        intent.putExtra("menu", R.menu.menu_chore_card);
        startActivity(intent);
    }

    @Override
    public void startChoreActivityReward() {
        Intent intent = new Intent(this, ChoreActivity.class);
        intent.putExtra("tag", "create");
        intent.putExtra("title", "Add new reward");
        intent.putExtra("menu", R.menu.menu_create_chore_card);
        startActivity(intent);   }

    @Override
    public void startChoreActivityGoal() {
        Intent intent = new Intent(this, ChoreActivity.class);
        intent.putExtra("tag", "create");
        intent.putExtra("title", "Add new Goal");
        intent.putExtra("menu", R.menu.menu_create_chore_card);
        startActivity(intent);   }
}