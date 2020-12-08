package com.example.grocery.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {
    private List<String> navBarTitle = new ArrayList<>();
    private List<Fragment> fragmentList = new ArrayList<>();

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void addFragment(String title, Fragment fragment) {
        fragmentList.add(fragment);
        navBarTitle.add(title);
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

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return navBarTitle.get(position);

    }
}
