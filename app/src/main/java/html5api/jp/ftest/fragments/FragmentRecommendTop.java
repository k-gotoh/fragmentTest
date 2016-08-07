package html5api.jp.ftest.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import html5api.jp.ftest.R;

/**
 * Created by kgotoh on 2016/08/06.
 */
public class FragmentRecommendTop extends CommonFragment {

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
        view = inflater.inflate(R.layout.fragment_recommend, null);
        Bundle bundle = getBundle();
        setMyTabNumber(1);


        ((Button) view.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragmentFromOthers(0, new FragmentCatalogue(), "catalogue");
            }
        });

        return view;
    }

}