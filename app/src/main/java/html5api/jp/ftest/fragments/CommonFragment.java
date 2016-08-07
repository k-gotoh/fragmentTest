package html5api.jp.ftest.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import html5api.jp.ftest.MainActivity;
import html5api.jp.ftest.R;
/**
 * Created by kgotoh on 2016/08/06.
 */
public class CommonFragment extends Fragment {

    private int fromTab;
    private String currentTag;
    protected int tabNumber;

    protected void setMyTabNumber(int tabNumber) {
        this.tabNumber = tabNumber;
    }
    protected Bundle getBundle() {
        final Bundle bundle = getArguments();
        fromTab = bundle.getInt("key");
        currentTag = bundle.getString("tag");
       Log.v("****","fromTab:"+fromTab);
        return bundle;
    }
    protected void addFragment(Fragment fragment, String tag) {
        Log.v("******",">>addFragment tag:"+tag);

        final Bundle bundle = new Bundle();
        bundle.putInt("key", tabNumber);
        bundle.putString("tag", tag);
        fragment.setArguments(bundle);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_child, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    protected void addFragmentFromOthers(int toTab, Fragment fragment, String tag) {
        ((MainActivity) getActivity()).addFragmentFromOthers(toTab, tabNumber, fragment, tag);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).setFromTab(fromTab, currentTag);

    }


}
