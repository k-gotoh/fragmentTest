package html5api.jp.ftest.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import html5api.jp.ftest.DetailActivity;
import html5api.jp.ftest.R;

/**
 * Created by kgotoh on 2016/08/06.
 */
public class FragmentTest   extends CommonFragment {

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
        view = inflater.inflate(R.layout.fragment_test, null);
        Bundle bundle = getBundle();
        setMyTabNumber(0);
        ((Button) view.findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetailActivity.class);
                startActivityForResult(intent,0);
            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        Log.v("*****","onActivityResultをfragmentのcontextで受けてみる");
        if (data != null) {
            String aa = data.getStringExtra("test");
            Log.v("*****","aa="+aa);
        }
    }

}