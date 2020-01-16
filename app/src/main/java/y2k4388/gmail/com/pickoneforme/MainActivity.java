package y2k4388.gmail.com.pickoneforme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements MainConstants.View{
    String TAG = "MAIN_ACTIVITY";

    MainConstants.Presenter mainPresenter;

    ViewPager viewPager;
    TabLayout tabLayout;
    PagerAdapter pagerAdapter;

    void initVariables(){
        mainPresenter = new MainPresenter();

        viewPager = findViewById(R.id.main_viewpager);
        viewPager.setOffscreenPageLimit(3);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());

        tabLayout = findViewById(R.id.main_tablayout);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initVariables();

        ViewpagerFragment1 viewpagerFragment1 = new ViewpagerFragment1();
        pagerAdapter.addItem(viewpagerFragment1);
        ViewpagerFragment2 viewpagerFragment2 = new ViewpagerFragment2();
        pagerAdapter.addItem(viewpagerFragment2);
        ViewpagerFragment3 viewpagerFragment3 = new ViewpagerFragment3();
        pagerAdapter.addItem(viewpagerFragment3);

        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addTab(tabLayout.newTab().setText("홈").setIcon(getResources().getDrawable(R.drawable.ic_home_black_24dp)));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tabLayout.getTabAt(0).getIcon().setTint(getColor(R.color.colorSecondaryPrimaryDark));
        }
        tabLayout.addTab(tabLayout.newTab().setText("기록").setIcon(getResources().getDrawable(R.drawable.ic_history_black_24dp)));
        tabLayout.addTab(tabLayout.newTab().setText("설정").setIcon(getResources().getDrawable(R.drawable.ic_settings_black_24dp)));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                Log.e(TAG, "onTabSelected: "+ tab.getPosition());

                viewPager.setCurrentItem(tab.getPosition());
                Log.e(TAG, "onTabSelected: " + tab.getIcon() );

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tab.getIcon().setTint(getColor(R.color.colorSecondaryPrimaryDark));
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    tab.getIcon().setTint(Color.BLACK);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



    }
}
