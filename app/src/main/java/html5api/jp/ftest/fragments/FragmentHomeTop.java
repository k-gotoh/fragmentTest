package html5api.jp.ftest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import html5api.jp.ftest.R;

/**
 * Created by kgotoh on 2016/08/06.
 */
public class FragmentHomeTop  extends CommonFragment {

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
        Bundle bundle = getBundle();

        setMyTabNumber(0);
        view = inflater.inflate(R.layout.fragment_sale, null);
        ((Button) view.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new FragmentCatalogue(), "catalogue");
            }
        });

        return view;
    }

}