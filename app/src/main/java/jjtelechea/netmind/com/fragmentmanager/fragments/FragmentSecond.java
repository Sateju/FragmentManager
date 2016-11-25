package jjtelechea.netmind.com.fragmentmanager.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jjtelechea.netmind.com.fragmentmanager.MainActivity;
import jjtelechea.netmind.com.fragmentmanager.R;


public class FragmentSecond extends Fragment {

    private static final String TAG_SECOND_FRAGMENT = "Tag second fragment";
    private TextView textView;
    private double mRandomNumber;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.i(TAG_SECOND_FRAGMENT, "onCreateView()");
        View fragView = inflater.inflate(R.layout.fragment_2,container,false);
        textView = (TextView) fragView.findViewById(R.id.txtViewFragment2);

        /*
        if (savedInstanceState != null) {
            mRandomNumber = savedInstanceState.getDouble("valorRandom");
        }
        */
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            MainActivity activity = (MainActivity) getActivity();
            mRandomNumber = activity.getRandomNumber();
        }


        textView.setText(String.valueOf(mRandomNumber));
        return fragView; //super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void setRandomNumber(double randomNumber){
        //textView.setText(String.valueOf(randomNumber));
        mRandomNumber = randomNumber;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            textView.setText(String.valueOf(randomNumber));
        }
    }

    /*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putDouble("valorRandom",mRandomNumber);
        super.onSaveInstanceState(outState);
    }
    */
}
