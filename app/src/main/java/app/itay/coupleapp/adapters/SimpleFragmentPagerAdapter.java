package app.itay.coupleapp.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import app.itay.coupleapp.fragments.AchievementsFragment;
import app.itay.coupleapp.fragments.ChoresFragment;
import app.itay.coupleapp.fragments.GoalsFragment;
import app.itay.coupleapp.fragments.RewardsFragment;

/**
 * Created by itayl on 28/02/2017.
 */

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "SimpleFragmentPagerAdap";
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[]{"Chores", "Goals", "Badges", "Rewards"};
    private Context mContext;

    public SimpleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
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
