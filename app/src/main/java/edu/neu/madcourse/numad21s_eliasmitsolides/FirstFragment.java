package edu.neu.madcourse.numad21s_eliasmitsolides;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        view.findViewById(R.id.button_aboutme).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //this bit sets the view from First Fragment to Second Fragment, scenes in Unity??
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);

                // Added this \/\/\/ to make a pop up ribbon on the bottom show up saying stuff
                // Thats what 'Snackbar' is, the ribbon notification at the bottom
//                Snackbar.make(view, "Elias Mitsolides --- mitsolides.e@husky.neu.edu",
//                        Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }


    public void buttonToLetter(View view)
    {
//        Intent intentFromMainToLetter = new Intent(this, MainActivityLetter.class);
//        startActivity(intentFromMainToLetter);

    }
}