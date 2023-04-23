package com.anthonyf.smsautoreply2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.anthonyf.smsautoreply2.adapters.MyPagerAdapter;
import com.anthonyf.smsautoreply2.fragments.ContactsFragment;
import com.anthonyf.smsautoreply2.fragments.MessagesFragment;
import com.anthonyf.smsautoreply2.model.Contact;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity  {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private ContactsFragment mContactsFragment;
    private MessagesFragment mMessagesFragment;
    private MyPagerAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate the ViewPager
        viewPager =findViewById(R.id.activity_main_viewpager);
        // Instantiate and configure the TabLayout
        tabLayout =findViewById(R.id.tab_layout);

        // Create an Adapter for the ViewPager
        pagerAdapter = new MyPagerAdapter(this);
        // Adding fragments and fragments' titles to the Adapter
        pagerAdapter.addFragment(new ContactsFragment(), "Contacts");
        pagerAdapter.addFragment(new MessagesFragment(), "Réponses");

        // Bind the Adapter to the ViewPager
        viewPager.setAdapter(pagerAdapter);

        // Associate TabLayout to the ViewPager by using a TabLayoutMediator
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
                pagerAdapter.setTabTitle(tab, position);
            }
        ).attach();





        if(savedInstanceState == null) {
            /*
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container, ContactsFragment.class, null)
                    .commit();
            */

        }
    }


}