package com.arkangel.arkangel.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.arkangel.arkangel.Fragments.menu16Fragment;
import com.arkangel.arkangel.Fragments.menu17Fragment;
import com.arkangel.arkangel.R;
import com.arkangel.arkangel.Fragments.menu10Fragment;
import com.arkangel.arkangel.Fragments.menu11Fragment;
import com.arkangel.arkangel.Fragments.menu12Fragment;
import com.arkangel.arkangel.Fragments.menu13Fragment;
import com.arkangel.arkangel.Fragments.menu14Fragment;
import com.arkangel.arkangel.Fragments.menu15Fragment;
import com.arkangel.arkangel.Fragments.menu2Fragment;
import com.arkangel.arkangel.Fragments.menu3Fragment;
import com.arkangel.arkangel.Fragments.menu4Fragment;
import com.arkangel.arkangel.Fragments.menu5Fragment;
import com.arkangel.arkangel.Fragments.menu6Fragment;
import com.arkangel.arkangel.Fragments.menu7Fragment;
import com.arkangel.arkangel.Fragments.menu8Fragment;
import com.arkangel.arkangel.Fragments.menu9Fragment;
import com.arkangel.arkangel.Fragments.menuFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class activity_pregunta extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregunta);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_layout, menu); //your file name
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        //Toast.makeText(this, "Selected Item: " +item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.principal:
                // do your code
                Intent intent = new Intent(getBaseContext(), options_user.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                // do your code
                Intent intent2 = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new menuFragment(), "1");
        adapter.addFragment(new menu2Fragment(), "2");
        adapter.addFragment(new menu3Fragment(), "3");
        adapter.addFragment(new menu4Fragment(), "4");
        adapter.addFragment(new menu5Fragment(), "5");
        adapter.addFragment(new menu6Fragment(), "6");
        adapter.addFragment(new menu7Fragment(), "7");
        adapter.addFragment(new menu8Fragment(), "8");
        adapter.addFragment(new menu9Fragment(), "9");
        adapter.addFragment(new menu10Fragment(), "10");
        adapter.addFragment(new menu11Fragment(), "11");
        adapter.addFragment(new menu12Fragment(), "12");
        adapter.addFragment(new menu13Fragment(), "13");
        adapter.addFragment(new menu14Fragment(), "14");
        adapter.addFragment(new menu15Fragment(), "15");
        adapter.addFragment(new menu16Fragment(), "16");
        adapter.addFragment(new menu17Fragment(), "17");
        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
