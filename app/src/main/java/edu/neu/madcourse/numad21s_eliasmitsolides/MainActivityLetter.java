package edu.neu.madcourse.numad21s_eliasmitsolides;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivityLetter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_letter);
    }

    //ASK: Why Do I ned this one, even though the onClick in fragment_first.xml is the one in FirstFragment (via dropdown)
    //        EVEN THOOOUGH the method in FirstFragment.java says it's not being used?
    public void onClick(View view)
    {
        String pressed_current = getString(R.string.letter_pressed);
        TextView text = findViewById(R.id.letter_pressed);
        switch (view.getId())
        {
            case R.id.button_a:

                pressed_current = "Letter Pressed: A";
                text.setText(pressed_current);
                break;

            case R.id.button_b:
                pressed_current = "Letter Pressed: B";
                text.setText(pressed_current);
                break;

            case R.id.button_c:
                pressed_current = "Letter Pressed: C";
                text.setText(pressed_current);
                break;

            case R.id.button_d:
                pressed_current = "Letter Pressed: D";
                text.setText(pressed_current);
                break;

            case R.id.button_e:
                pressed_current = "Letter Pressed: E";
                text.setText(pressed_current);
                break;

            case R.id.button_f:
                pressed_current = "Letter Pressed: F";
                text.setText(pressed_current);
                break;

        }
    }
}