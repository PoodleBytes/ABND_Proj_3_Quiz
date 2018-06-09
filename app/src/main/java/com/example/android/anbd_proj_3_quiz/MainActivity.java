package com.example.android.anbd_proj_3_quiz;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
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

    /**
     * declare variables that are used throughout app
     */
    final int numQuestions = 4;
    int numAnswered = 0;
    int grade = 0;
    int q1Score = 0;
    int q2Score = 0;
    int q3Score = 0;
    int q4Score = 0;
    int q1AnswerID = 0;
    boolean q2cb1Chk = false;
    boolean q2cb2Chk = false;
    boolean q2cb3Chk = false;
    boolean q2cb4Chk = false;
    String q3text = "";
    int q4AnswerID = 0;

    boolean q1Answered = false;
    boolean q2Answered = false;
    boolean q3Answered = false;
    boolean q4Answered = false;

    CheckBox q2Chk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);     /** keep screen in portrait mode  */
    }

    /**
     * question 1 OnClick
     */
    public void q1Answer(View view) {
        q1AnswerID = view.getId();
    }

    /**
     * question 2  CheckBox 1 OnClick
     * set q2cb1Chk  = isChecked
     */
    public void q2cb1Answer(View view) {
        q2Chk = findViewById(view.getId());
        q2cb1Chk = q2Chk.isChecked();
    }

    /**
     * question 2  CheckBox 2 OnClick
     */
    public void q2cb2Answer(View view) {
        q2Chk = findViewById(view.getId());
        q2cb2Chk = q2Chk.isChecked();
    }

    /**
     * question 2  CheckBox 3 OnClick
     */
    public void q2cb3Answer(View view) {
        q2Chk = findViewById(view.getId());
        q2cb3Chk = q2Chk.isChecked();
    }

    /**
     * question 2  CheckBox 4 OnClick
     */
    public void q2cb4Answer(View view) {
        q2Chk = findViewById(view.getId());
        q2cb4Chk = q2Chk.isChecked();
    }

    /**
     * question 4 onClick
     */
    public void q4Answer(View view) {
        q4AnswerID = view.getId();
    }

    /**
     * GRADE QUIZ AND UPDATE POINTS FOR EACH QUESTION
     * (allow user to know where there were right/wrong)
     */
    public void gradeQuiz(View view) {
        /**
         * Grade Q1
         **/
        switch (q1AnswerID) {   /**The switch case will look at the checkedId */
            case R.id.q1rb1: /**incorrect */
            case R.id.q1rb3: /**incorrect */
                q1Score = 0;
                break;
            case R.id.q1rb2: /** correct */
                q1Score = 25;
                break;
        }

        /**
         * Grade Q2
         */
        int i = 0;            /** count number of checked boxes */
        if (q2cb1Chk) {         /** correct */
            q2Score += 25;
            i++;
        }
        if (q2cb2Chk) {
            q2Score += 0;
            i++;
        }
        if (q2cb3Chk) {
            q2Score += 25;
            i++;
        }
        if (q2cb4Chk) {
            q2Score += 0;
            i++;
        }
        if (i > 0) {
            q2Score = q2Score / i;
        } else {
            q2Score = 0;
        }


        /**Q3 */
        EditText q3text = findViewById(R.id.q3a1); /**get ID of Q3 EditText */
        String q3txt = q3text.getText().toString();
        String q3AnsTxt = getResources().getString(R.string.q3a1);
        if (q3txt.equalsIgnoreCase(q3AnsTxt)) {
            q3Score = 25;
        } else {
            q3Score = 0;
        }

        /**Q4 */
        switch (q4AnswerID) {   /** The switch case will look at the checkedId */
            case R.id.q4rb1: /** correct */
                q4Score = 25;
                break;
            case R.id.q4rb2: /** incorrect */
                q4Score = 0;
                break;
        }


        /** calc grade */
        grade = q1Score + q2Score + q3Score + q4Score;


        /**notify user of results */
        printScore();
    }

    /**
     * FORMAT AND DISPLAY SCORE
     */
    public void printScore() {

        /** get username */
        EditText nameField = findViewById(R.id.name);
        String name = nameField.getText().toString();

        /**
         * display points for each question
         */

        /** Update Points for Q2 */
        TextView scoreView = findViewById(R.id.q1Points);
        String s = "#1: " + String.valueOf(q1Score);
        scoreView.setText(s);
        if (q1Score < 25) {
            scoreView.setTextColor(this.getResources().getColor(R.color.colorAccent));
        }
        scoreView.setVisibility(View.VISIBLE);

        /** Update Points for Q2 */
        scoreView = findViewById(R.id.q2Points);
        s = "#2:  " + String.valueOf(q2Score);
        scoreView.setText(s);
        if (q2Score < 25) {
            scoreView.setTextColor(this.getResources().getColor(R.color.colorAccent));
        }
        scoreView.setVisibility(View.VISIBLE);

        /** Update Points for Q3 */
        scoreView = findViewById(R.id.q3Points);
        s = "#3 " + String.valueOf(q3Score);
        scoreView.setText(s);
        if (q3Score < 25) {
            scoreView.setTextColor(this.getResources().getColor(R.color.colorAccent));
        }
        scoreView.setVisibility(View.VISIBLE);

        /** Update Points for Q4 */
        scoreView = findViewById(R.id.q4Points);
        s = "#4: " + String.valueOf(q4Score);
        scoreView.setText(s);
        if (q4Score < 25) {
            scoreView.setTextColor(this.getResources().getColor(R.color.colorAccent));
        }
        scoreView.setVisibility(View.VISIBLE);


        /**standard grading scale despite quiz only having 5 possible grades */
        if (grade >= 100) {
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
        setContentView(R.layout.activity_main);     /**reload screen, resetting buttons */
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);     /**lock screen in portrait mode */
        numAnswered = 0;
        grade = 0;
        q1Score = 0;
        q2Score = 0;
        q3Score = 0;
        q4Score = 0;
        q1AnswerID = 0;
        q4AnswerID = 0;
        q3text = "";
        q2cb1Chk = false;
        q2cb2Chk = false;
        q2cb3Chk = false;
        q2cb4Chk = false;

        q1Answered = false;
        q2Answered = false;
        q3Answered = false;
        q4Answered = false;

    } /**end reset */

}