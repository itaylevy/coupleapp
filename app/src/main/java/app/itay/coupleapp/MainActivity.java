package app.itay.coupleapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView manAvatar;
    private ImageView womanAvatar;
    private Bitmap manBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        manAvatar=(ImageView) findViewById(R.id.manAvatar);
        womanAvatar=(ImageView) findViewById(R.id.womanAvatar);

        BitmapDrawable drawable = (BitmapDrawable) manAvatar.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
//        manAvatar.buildDrawingCache();
//        Bitmap bitmap=manAvatar.getDrawingCache();
        RoundedBitmapDrawable roundDraw =
                RoundedBitmapDrawableFactory
                        .create(getResources(), bitmap);

        roundDraw.setCircular(false);


        // Create an adapter that knows which fragment should be shown on each page
        viewPager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager(),
                MainActivity.this));

        // Set the adapter onto the view pager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
//        setupTabLayout(tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        roundDraw.setCircular(true);
    }
    public void setupTabLayout(TabLayout tabLayout) {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        ViewPager mViewpager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout.setupWithViewPager(mViewpager);

        TextView tab = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tab.setText("Library");
        tab.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.ic_launcher, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tab);
        //..
    }
}
