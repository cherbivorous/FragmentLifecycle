package com.example.onjira.fragmentlifecycle;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Onjira on 28/07/2015.
 */
public class FragmentA extends Fragment implements View.OnClickListener {

    Button button;
    int counter = 0;
    Communicator comm; //interface

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    // No need onCreate method,
    // Because we not access the UI before the activity is ready

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            counter = 0;
        } else {
            counter = savedInstanceState.getInt("counter", 0);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        comm = (Communicator) getActivity();
        button = (Button) getActivity().findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Store data in case of rotate screen
        outState.putInt("counter", counter);
    }

    @Override
    public void onClick(View v) {
        counter++;
        comm.respond("The button was clicked " + counter + " times");
    }
}
