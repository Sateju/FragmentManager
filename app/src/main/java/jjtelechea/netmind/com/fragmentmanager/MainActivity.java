package jjtelechea.netmind.com.fragmentmanager;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import jjtelechea.netmind.com.fragmentmanager.fragments.FragmentFirst;
import jjtelechea.netmind.com.fragmentmanager.fragments.FragmentSecond;

public class MainActivity extends AppCompatActivity implements FragmentFirst.OnFirstFragmentInterface, View.OnClickListener {

    private int display_mode;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private FragmentSecond fragmentSecond;
    private double randomNumber;

    public double getRandomNumber() {
        return randomNumber;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        display_mode = getResources().getConfiguration().orientation;
        fragmentManager = getSupportFragmentManager();

        if (display_mode == Configuration.ORIENTATION_LANDSCAPE) {
            Button btnSwapFragment = (Button) findViewById(R.id.btnSwapFragment);
            btnSwapFragment.setOnClickListener(this);
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayoutMainLandscape, new FragmentFirst(), "first fragment");
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onCommFromFragmentOne(double rNumber) {
//        Toast.makeText(this,"randomNumber " + rNumber, Toast.LENGTH_SHORT).show();

        if (display_mode == Configuration.ORIENTATION_PORTRAIT) {
            fragmentSecond = (FragmentSecond) fragmentManager.findFragmentById(R.id.fragment_second);
            fragmentSecond.setRandomNumber(rNumber);
        } else {
            //LandScape
            randomNumber = rNumber;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSwapFragment:
                swapFragment();
                break;
        }
    }

    private void swapFragment() {
        if (display_mode == Configuration.ORIENTATION_LANDSCAPE) {
            Fragment whatFragment = fragmentManager.findFragmentByTag(getString(R.string.first_fragment_tag)); //Busco el primero
            fragmentTransaction = fragmentManager.beginTransaction();
            if (whatFragment != null) { // Si está en el primer fragment
                fragmentTransaction.replace(R.id.frameLayoutMainLandscape, new FragmentSecond(), getString(R.string.second_fragment_tag));
                //fragmentTransaction.addToBackStack(null); //para poder volver atras con el boton back
                fragmentTransaction.commit();
                fragmentSecond = (FragmentSecond) fragmentManager.findFragmentByTag(String.valueOf(R.string.second_fragment_tag));
            } else {
                fragmentTransaction.replace(R.id.frameLayoutMainLandscape, new FragmentFirst(), getString(R.string.first_fragment_tag));
                fragmentTransaction.commit();
            }
        } else {
            Log.d("Pruebas", "No estas en landscape al hacer el swapFragment");
        }
    }

}
