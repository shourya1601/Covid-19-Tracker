package com.example.sudokusolver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.tl);
        viewPager=findViewById(R.id.vp);

        ArrayList<String> arrayList=new ArrayList<>();

        arrayList.add("Gloabal");
        arrayList.add("Country");
        arrayList.add("India");

        prepareViewPagger(viewPager,arrayList);

        tabLayout.setupWithViewPager(viewPager);
    }

    private void prepareViewPagger(ViewPager viewPager,ArrayList<String> arrayList)
    {
        MainAdapter adapter=new MainAdapter(getSupportFragmentManager());

        GlobalFragment global=new GlobalFragment();
        Bundle bundle0=new Bundle();
        bundle0.putString("title",arrayList.get(0));
        global.setArguments(bundle0);
        adapter.addFragment(global,arrayList.get(0));

        CountriesFragment countries=new CountriesFragment();
        Bundle bundle1=new Bundle();
        bundle1.putString("title",arrayList.get(1));
        countries.setArguments(bundle1);
        adapter.addFragment(countries,arrayList.get(1));

        IndiaFragment india=new IndiaFragment();
        Bundle bundle2=new Bundle();
        bundle2.putString("title",arrayList.get(2));
        india.setArguments(bundle2);
        adapter.addFragment(india,arrayList.get(2));

        viewPager.setAdapter(adapter);
    }

    private class MainAdapter extends FragmentPagerAdapter {

        ArrayList<String> arrayList=new ArrayList<>();
        List<Fragment> fragmentList=new ArrayList<>();

        public void addFragment(Fragment fragment,String title)
        {
            arrayList.add(title);
            fragmentList.add(fragment);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public CharSequence getPageTitle(int position)
        {
            return arrayList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime+2000>System.currentTimeMillis())
        {
            super.onBackPressed();
            finishAffinity();
            return;
        }
        else
        {
            Toast.makeText(MainActivity.this,"Press again to exit",Toast.LENGTH_SHORT).show();
        }
        backPressedTime=System.currentTimeMillis();
    }
}