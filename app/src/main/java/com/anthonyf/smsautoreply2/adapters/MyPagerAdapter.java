package com.anthonyf.smsautoreply2.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.anthonyf.smsautoreply2.fragments.ContactsFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentStateAdapter {

    private final List<Fragment> fragmentList= new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    public MyPagerAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    public void setTabTitle(TabLayout.Tab tab, int position) {
        tab.setText(fragmentTitleList.get(position));
    }
}
