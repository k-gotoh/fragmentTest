package html5api.jp.ftest;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import html5api.jp.ftest.R;


import html5api.jp.ftest.fragments.FragmentCart;
import html5api.jp.ftest.fragments.FragmentFavorite;
import html5api.jp.ftest.fragments.FragmentHome;
import html5api.jp.ftest.fragments.FragmentRecommend;
import html5api.jp.ftest.fragments.FragmentRoot;
import html5api.jp.ftest.fragments.FragmentSearch;

public class MainActivity extends AppCompatActivity {

    private TextView[] tvs;
    private FrameLayout[] layouts;
    private int currentTab = 0;
    private int[] fromTabs = {0,1,2,3,4};
    private String[] currentTags = {"top","top","top","top","top"};

    Map<Integer, FragmentRoot> fragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        tvs = new TextView[5];
        tvs[0] = (TextView) findViewById(R.id.t1);
        tvs[1] = (TextView) findViewById(R.id.t2);
        tvs[2] = (TextView) findViewById(R.id.t3);
        tvs[3] = (TextView) findViewById(R.id.t4);
        tvs[4] = (TextView) findViewById(R.id.t5);


        layouts = new FrameLayout[5];
        layouts[0] = (FrameLayout) findViewById(R.id.f1);
        layouts[1] = (FrameLayout) findViewById(R.id.f2);
        layouts[2] = (FrameLayout) findViewById(R.id.f3);
        layouts[3] = (FrameLayout) findViewById(R.id.f4);
        layouts[4] = (FrameLayout) findViewById(R.id.f5);

        fragmentMap = new HashMap<>();
        for (int i = 0; i < tvs.length; i++) {

            final int j = i;
            tvs[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentTab == j) {
                        fragmentMap.get(currentTab).backToTop();
                    } else {
                        currentTab = j;
                        tabChanged(j);
                    }

                }
            });
        }
        setFragment(0, R.id.f1, new FragmentHome());
        setFragment(1, R.id.f2, new FragmentRecommend());
        setFragment(2, R.id.f3, new FragmentSearch());
        setFragment(3, R.id.f4, new FragmentFavorite());
        setFragment(4, R.id.f5, new FragmentCart());

        setContentsVisible();
    }

    private void tabChanged(int tab) {
        currentTab = tab;
        setContentsVisible();
    }

    public void addFragmentFromOthers(int tab, int fromTab, Fragment fragment, String tag) {

        fragmentMap.get(tab).addFragmentFromOthers(fromTab, fragment, tag);
        currentTab = tab;

        setContentsVisible();
    }


    private void setContentsVisible() {
        Log.v("*******"," fromTab:"+fromTabs[currentTab]+"  currentTag:"+currentTags[currentTab] + " currentTab:"+currentTab);
        for (int i = 0; i < layouts.length; i++) {
            if (currentTab == i) {
                layouts[i].setVisibility(View.VISIBLE);
                tvs[i].setTextColor(Color.RED);
            } else {
                layouts[i].setVisibility(View.GONE);
                tvs[i].setTextColor(Color.BLACK);
            }
        }
    }

    private void setFragment(int tab, int layoutId, FragmentRoot newFragment) {
        fragmentMap.put(tab, newFragment);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(layoutId, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



    public void setFromTab(int fromTab, String tag) {
        currentTags[currentTab] = tag;
        fromTabs[currentTab] = fromTab;
        Log.v("****"," fromTab:"+fromTab+"  currerntTag:"+currentTags[currentTab]);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_BACK) {
            return super.onKeyDown(keyCode, event);
        } else {
            if (currentTab == fromTabs[currentTab]) {
                fragmentMap.get(currentTab).popFragment();
            } else {
                currentTab = fromTabs[currentTab];
                setContentsVisible();
            }

            return true;
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data){
//        Log.v("*****","onActivityResultをActivityで受けてみる");
//        if (data != null) {
//            String aa = data.getStringExtra("test");
//            Log.v("*****","aa="+aa);
//        }
//    }
}
