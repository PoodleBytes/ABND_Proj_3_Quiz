package com.example.android.anbd_proj_3_quiz;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/*
 *   Udacity Android Basics Nanodegree
 *   Project 3 - Simple Quiz
 *
 *       PoodleBytes
 *       2018.06.07
 *
 * FEATURES/NOTES
 *   used ProgressBar
 *   'inspired' Code from my quiz proj (intro android), github/Tia-C and Google.com
 * Question  -
 *      1 -  Is  there  a way to get progressbar to in the titlebar (or a way so it remains visible?
 *      2 -  I know private is preferred but my app would crash if I made the methods private
 *           Is there a way to do this w/or do I need an onClick listener?
 *      3 -  I don't like how I have to retype most everything for q1Answer, q2Answer etc
 *           is there a way to do this in a loop?
 *
 *           Thanks!
 * */


public class MainActivity extends AppCompatActivity {

    //declare variables that are used throughout app
    final int numQuestions = 4;
    int numAnswered = 0;
    int grade = 0;
    int q1Score = 0;
    int q2Score = 0;
    int q3Score = 0;
    int q4Score = 0;
    CheckBox q2Chk;
    ProgressBar status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);     //keep screen in portrait mode

        //initialize progress bar
        status = findViewById(R.id.progress_bar);
        status.setProgress(numAnswered);
    }

    //evaluate question 1
    public void q1Answer(View view) {
        int checkedId = view.getId();
        switch (checkedId) {   //The switch case will look at the checkedId
            case R.id.q1rb1: //incorrect
            case R.id.q1rb3: //incorrect
                //Since all three of these buttons are incorrect, you can lump them together with one break message.
                q1Score = 0;
                break;
            case R.id.q1rb2: // correct
                q1Score = 25;
                break;
        }
        Log.v("q1Answer", " Q1 answer:" + q1Score);
        updateNumAnswered();
    }

    //evaluate question 2
    public void q2Answer(View view) {
        int i = 0;            //count number of checked boxes
        q2Chk = findViewById(R.id.q2cb1);  //y
        if (q2Chk.isChecked()) {
            q2Score += 25;
            i++;
        }
        q2Chk = findViewById(R.id.q2cb2);  //y
        if (q2Chk.isChecked()) {
            q2Score += 0;
            i++;
        }
        q2Chk = findViewById(R.id.q2cb3);  //y
        if (q2Chk.isChecked()) {
            q2Score += 25;
            i++;
        }
        q2Chk = findViewById(R.id.q2cb4);  //y
        if (q2Chk.isChecked()) {
            q2Score += 0;
            i++;
        }
        q2Score = q2Score / i;
        Log.v("q2Answer", " Q2 answer:" + q2Score);
        if (i == 1) {     //only increment numAnswered once
            updateNumAnswered();
        }
    }

    //evaluate question 3
    public void q3Answer(View view) {
        int checkedId = view.getId();
        switch (checkedId) {   //The switch case will look at the checkedId
            case R.id.q3rb1: //correct
                q3Score = 25;
                break;
            case R.id.q3rb2: // incorrect
                q3Score = 0;
                break;
        }
        Log.v("q3Answer", " Q3 answer:" + q3Score);
        updateNumAnswered();
    }

    //evaluate question 4
    public void q4Answer(View view) {
        int checkedId = view.getId();
        switch (checkedId) {   //The switch case will look at the checkedId
            case R.id.q4rb1: //correct
                q4Score = 25;
                break;
            case R.id.q4rb2: // incorrect
                q4Score = 0;
                break;
        }
        Log.v("q4Answer", " Q4 answer:" + q4Score);
        updateNumAnswered();
    }

    /*
    GRADE QUIZ AND UPDATE POINTS FOR EACH QUESTION
    (allow user to know where there were right/wrong)
    */
    public void gradeQuiz(View view) {
        if (numAnswered < numQuestions) {   //are all questions answered
            Toast.makeText(this, "You have " + (numQuestions - numAnswered) + " unanswered questions.", Toast.LENGTH_LONG).show();
        } else {        //grade the test
            //update score for each question
            //Q1
            TextView scoreView = findViewById(R.id.q1Points);
            scoreView.setText(String.valueOf(q1Score));
            if (q1Score < 25) {
                scoreView.setTextColor(this.getResources().getColor(R.color.colorAccent));
            }
            scoreView.setVisibility(View.VISIBLE);

            //Q2
            scoreView = findViewById(R.id.q2Points);
            scoreView.setText(String.valueOf(q2Score));
            if (q2Score < 25) {
                scoreView.setTextColor(this.getResources().getColor(R.color.colorAccent));
            }
            scoreView.setVisibility(View.VISIBLE);

            //Q3
            scoreView = findViewById(R.id.q3Points);
            scoreView.setText(String.valueOf(q3Score));
            if (q3Score < 25) {
                scoreView.setTextColor(this.getResources().getColor(R.color.colorAccent));
            }
            scoreView.setVisibility(View.VISIBLE);

            //Q4
            scoreView = findViewById(R.id.q4Points);
            scoreView.setText(String.valueOf(q4Score));
            if (q4Score < 25) {
                scoreView.setTextColor(this.getResources().getColor(R.color.colorAccent));
            }
            scoreView.setVisibility(View.VISIBLE);

            //calc grade
            grade = q1Score + q2Score + q3Score + q4Score;

            //notify user of results
            printScore();
        }
    }

    /*
        FORMAT AND DISPLAY SCORE
    */
    public void printScore() {

        // get username
        EditText nameField = findViewById(R.id.name);
        String name = nameField.getText().toString();

        if (grade >= 100) {     //standard grading scale despite quiz only having 5 possible grades
            Toast.makeText(this, "Congratulations! " + name + ", You scored 100%.  Perfect!", Toast.LENGTH_LONG).show();
        } else if (grade >= 90) {
            Toast.makeText(this, "Awesome! " + name + ", You got an A. (" + grade + ")", Toast.LENGTH_LONG).show();
        } else if (grade >= 80) {
            Toast.makeText(this, "Great job! " + name + ", You got a B.(" + grade + ")", Toast.LENGTH_LONG).show();
        } else if (grade >= 70) {
            Toast.makeText(this, "Good job! " + name + ", You got a C.(" + grade + ")", Toast.LENGTH_LONG).show();
        } else if (grade >= 60) {
            Toast.makeText(this, "You need a little practice, " + name + ". You got a D.(" + grade + ")", Toast.LENGTH_LONG).show();
        } else if (grade < 60) {
            Toast.makeText(this, "Sorry, " + name + ". You didn't pass. Try again.(" + grade + ")", Toast.LENGTH_LONG).show();
        }
    }

    /*
    RESET
    */
    public void reset(View view) {
        setContentView(R.layout.activity_main);     //reload screen, resetting buttons
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);     //lock screen in portrait mode
        numAnswered = 0;
        grade = 0;
        q1Score = 0;
        q2Score = 0;
        q3Score = 0;
        q4Score = 0;
        status = findViewById(R.id.progress_bar);
        status.setProgress(numAnswered);
    } //end reset

    /*
    TRACK PROGRESS
     */
    private void updateNumAnswered() {
        numAnswered++;
        status.setProgress(numAnswered);
    }
}