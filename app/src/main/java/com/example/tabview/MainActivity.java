package com.example.tabview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    //Partie assignation vue
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private void initUi() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager2);
    }

    //Variables globales
    //initialisation du view pager
    private FragmentAdaptater adapter;

    private void initViewPager() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new FragmentAdaptater(fragmentManager, getLifecycle());

        viewPager2.setAdapter(adapter);
    }

    //paramétrage tabLayout
    private void initiTablLayout() {

        tabLayout.addTab(tabLayout.newTab().setText("ONE"));
        tabLayout.addTab(tabLayout.newTab().setText("TWO"));
        tabLayout.addTab(tabLayout.newTab().setText("THREE"));

        tabLayout.getTabAt(0).setIcon(R.drawable.icon_1_red);
        tabLayout.getTabAt(1).setIcon(R.drawable.icon_2_red);
        tabLayout.getTabAt(2).setIcon(R.drawable.icon_3_red);
    }

    //Gestion du "back"
    private ViewPager2.OnPageChangeCallback callback;
    private Stack<Integer> pageHistory;
    boolean saveToHistory;

    //geston du click

    private void clickTab() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                Log.i("TAG", "onTabSelected: " + tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        saveToHistory = true;
        viewPager2.registerOnPageChangeCallback(callback);
    }

    private void gestionCallBack() {
        viewPager2.setOffscreenPageLimit(2);
        pageHistory = new Stack<>();

        callback = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(saveToHistory)
                    pageHistory.push(position);
                Log.i("TAG1", "onPageSelected: " + saveToHistory);
                Log.i("TAG2", "onPageSelected: " + pageHistory);
                Log.i("TAG4", "onPageSelected: " + position);
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }

        };
    }

    @Override
    public void onBackPressed() {

        if(pageHistory .isEmpty()) {
            super.onBackPressed();
        } else {
            saveToHistory = false;
            viewPager2.setCurrentItem(pageHistory.pop());
            saveToHistory = true;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initUi();
        initViewPager();
        initiTablLayout();
        gestionCallBack();
        clickTab();

    }
}