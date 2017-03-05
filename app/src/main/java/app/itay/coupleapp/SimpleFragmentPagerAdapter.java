package app.itay.coupleapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by itayl on 28/02/2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[]{"Chores", "Goals", "Achievements", "Rewards"};
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
//        return PageFragment.newInstance(position + 1);

        if (position == 0) {
            return new ChoresFragment();
        } else if (position == 1) {
            return new GoalsFragment();
        } else if (position == 2) {
            return new AchievementsFragment();
        } else {
            return new RewardsFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
