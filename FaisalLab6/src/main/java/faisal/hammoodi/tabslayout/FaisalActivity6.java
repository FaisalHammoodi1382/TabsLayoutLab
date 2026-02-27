/*
 Name: Faisal Hammoodi
 Student ID: n01701382
*/

package faisal.hammoodi.tabslayout;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class FaisalActivity6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new LeftFa());
        fragmentList.add(new Rightdi());

        ViewPager2 viewPager = findViewById(R.id.faiViewPager);
        viewPager.setAdapter(new ViewPagerAdapter(this, fragmentList));

        TabLayout tabLayout = findViewById(R.id.faiTabLayout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(getString(R.string.tab_label, position + 1))
        ).attach();
    }
}

