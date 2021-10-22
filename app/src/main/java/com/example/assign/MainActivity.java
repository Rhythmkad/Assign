package com.example.assign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView team1_score, team2_score;
    private ToggleButton button1, button2;
    private Button reset;
    private RadioGroup radioGroupTeam1, radioGroupTeam2;
    private RadioButton radioButtonTeam1Score, radioButtonTeam2Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initializing Ids*/
        initializeIds();

    }

    @Override
    protected void onStart() {
        super.onStart();

        // changing score
        changeScore();
    }

    /* Initializing Ids*/
    public void initializeIds(){
        team1_score = findViewById(R.id.score_1);
        team2_score = findViewById(R.id.score_2);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        radioGroupTeam1 = findViewById(R.id.radio_group1);
        radioGroupTeam2 = findViewById(R.id.radio_group2);
        reset = findViewById(R.id.resetScore);
    }

    /* changing score through radio buttons */
    public void changeScore(){

        // reset button on click listener
        reset.setOnClickListener(this);

        /* Radio group method for selecting team.*/
        radioGroupTeam1.setOnCheckedChangeListener((radioGroup, i) -> {

            /* getting selected radio button ID in the radio group */
            radioButtonTeam1Score = findViewById(radioGroupTeam1.getCheckedRadioButtonId());
            int add = Integer.parseInt(radioButtonTeam1Score.getText().toString()) + Integer.parseInt(team1_score.getText().toString());
            team1_score.setText(String.valueOf(add));
            changeButtonView();

        });

        /* radio group changeListener method for selecting Scores */
        radioGroupTeam2.setOnCheckedChangeListener((radioGroup, a) -> {

            /* getting selected radio button ID in the radio group */
            radioButtonTeam2Score = findViewById(radioGroupTeam2.getCheckedRadioButtonId());
            int add = Integer.parseInt(radioButtonTeam2Score.getText().toString()) + Integer.parseInt(team2_score.getText().toString());
            team2_score.setText(String.valueOf(add));
            changeButtonView();
        });
    }

    // reset button on click method
    @Override
    public void onClick(View view) {
        // reset everything

        // radio button check to false
        radioButtonTeam1Score.setChecked(false);
        radioButtonTeam2Score.setChecked(false);

        // toggle button check to false
        button1.setChecked(false);
        button2.setChecked(false);

        //setting text view value to 0
        team1_score.setText("0");
        team2_score.setText("0");
    }

    // Changing button view and button text according to score
    private void changeButtonView() {
        int teamCanadaScore = Integer.parseInt(team1_score.getText().toString());
        int teamUseScore = Integer.parseInt(team2_score.getText().toString());
        if (teamCanadaScore > teamUseScore) {
            button1.setText(button1.getContext().getText(R.string.win));
            button1.setChecked(true);
            button2.setTextOff("");
            button2.setChecked(false);
        } else if (teamCanadaScore < teamUseScore) {
            button1.setTextOff("");
            button1.setChecked(false);
            button2.setText(button2.getContext().getText(R.string.win));
            button2.setChecked(true);
        } else {
            button1.setTextOff(button1.getContext().getText(R.string.equal));
            button2.setTextOff(button2.getContext().getText(R.string.equal));
            button1.setChecked(false);
            button2.setChecked(false);
        }
    }
}
