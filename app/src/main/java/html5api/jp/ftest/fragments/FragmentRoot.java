package html5api.jp.ftest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import java.util.List;

import html5api.jp.ftest.MainActivity;
import html5api.jp.ftest.R;

/**
 * Created by kgotoh on 2016/08/06.
 */
public class FragmentRoot extends Fragment {

    private Class<? extends CommonFragment> topFragment;
    private int tabNumber;

    protected void setTopFragment(int tabNumber, Class<? extends CommonFragment> clazz) {
        topFragment = clazz;
        this.tabNumber = tabNumber;
    }

    protected void addFragment(Fragment fragment, String tag) {
        Bundle bundle = new Bundle();
        bundle.putInt("key", tabNumber);
        bundle.putString("tag", tag);
        fragment.setArguments(bundle);

        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_child, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public void addFragmentFromOthers(int fromTab, Fragment fragment, String tag) {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.remove(fragmentManager.getFragments().get(0));

        Bundle bundle = new Bundle();
        bundle.putInt("key", fromTab);
        bundle.putString("tag", tag);
        fragment.setArguments(bundle);

        transaction.replace(R.id.fragment_child, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    public void backToTop() {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(fragmentManager.getFragments().get(0));

        try {
            CommonFragment fragment = (CommonFragment) topFragment.newInstance();
            Bundle bundle = new Bundle();
            bundle.putInt("key", tabNumber);
            bundle.putString("tag", "top");
            fragment.setArguments(bundle);
            transaction.replace(R.id.fragment_child, fragment);
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void popFragment() {
        if (getChildFragmentManager().getBackStackEntryCount() > 1) {
            Log.v("****", "StackEntryCoun;" + (getChildFragmentManager().getBackStackEntryCount()));
            getChildFragmentManager().popBackStackImmediate();
        }
    }
}
