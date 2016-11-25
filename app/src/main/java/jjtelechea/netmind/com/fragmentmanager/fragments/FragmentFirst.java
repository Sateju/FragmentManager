package jjtelechea.netmind.com.fragmentmanager.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

import jjtelechea.netmind.com.fragmentmanager.R;


public class FragmentFirst extends Fragment implements View.OnClickListener {

    private static final String TAG_FIRST_FRAGMENT = "Tag first fragment";
    private OnFirstFragmentInterface mFirstFragCallback;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.i(TAG_FIRST_FRAGMENT, "onCreateView()");
        View fragView = inflater.inflate(R.layout.fragment_1,container,false);

        final Button fragBtnActivitySecond = (Button) fragView.findViewById(R.id.firstFragmentButton);
        fragBtnActivitySecond.setOnClickListener(this);
        return fragView; //super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.firstFragmentButton:
                mFirstFragCallback.onCommFromFragmentOne(new Random().nextInt());
                break;
        }
    }

    public interface  OnFirstFragmentInterface{
        void onCommFromFragmentOne(double rNumber);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFirstFragCallback = (OnFirstFragmentInterface) context;

    }


}
