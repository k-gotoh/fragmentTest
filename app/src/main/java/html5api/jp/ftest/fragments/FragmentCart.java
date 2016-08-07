package html5api.jp.ftest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import html5api.jp.ftest.R;

/**
 * Created by kgotoh on 2016/08/06.
 */
public class FragmentCart  extends FragmentRoot {
    private View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return initData(inflater, container);
    }

    private View initData(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_layout, null);
        setTopFragment(4, FragmentCartTop.class);
        addFragment(new FragmentCartTop(), "top");

        return view;
    }
}
