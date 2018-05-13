package com.example.elena.a5quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //When the Submit Button is clicked, submitAnswers is called
    public void submitAnswers(View view){

        //Find the correct answers for questions 1 & 2 and check if they have been selected
        CheckBox firstCheckBox = (CheckBox)findViewById(R.id.checkbox1);
        boolean firstCheckBoxIsChecked = firstCheckBox.isChecked();

        CheckBox secondCheckBox = (CheckBox)findViewById(R.id.checkbox2);
        boolean secondCheckBoxIsChecked = secondCheckBox.isChecked();

        CheckBox thirdCheckBox = (CheckBox)findViewById(R.id.checkbox3);
        boolean thirdCheckBoxIsChecked = thirdCheckBox.isChecked();

        CheckBox fourthCheckBox = (CheckBox)findViewById(R.id.checkbox4);
        boolean fourthCheckBoxIsChecked = fourthCheckBox.isChecked();

        //Find the EditText field, find out what has been typed in the field and convert it into a string
        EditText questionFourAnswer = (EditText)findViewById(R.id.question_four_field);
        String questionFourAnswered = questionFourAnswer.getText().toString().toLowerCase();

        //Find the RadioGroup for  Question2 and find out which radioButton has been selected
        RadioGroup radioGroupQuestion2 = (RadioGroup) findViewById(R.id.radio_group_q2);
        int selectedIdQ2 = radioGroupQuestion2.getCheckedRadioButtonId();

        //Find the RadioGroup for  Question3 and find out which radioButton has been selected
        RadioGroup radioGroupQuestion3= (RadioGroup) findViewById(R.id.radio_group_q3);
        int selectedIdQ3 = radioGroupQuestion3.getCheckedRadioButtonId();

        //Calculate points, create a message to display users' score and display it as a toast message
        int points = calculatePoints(firstCheckBoxIsChecked, secondCheckBoxIsChecked, questionFourAnswered, selectedIdQ2, selectedIdQ3, thirdCheckBoxIsChecked, fourthCheckBoxIsChecked);
        String pointsMessage = createQuizSummary(points);
        Toast.makeText(getApplicationContext(), pointsMessage, Toast.LENGTH_SHORT).show();
    }

    //Creates a text message, displaying the total number of points gained
    private String createQuizSummary (int points) {
        if (points >= 4){
            String pointsMessage = getString(R.string.total_points) + " " + points + getString(R.string.good_job);
            return pointsMessage;
        }
        else {
            String pointsMessage = getString(R.string.total_points) + " " + points;
            return pointsMessage;
        }
    }

    //Calculates the total number of points
    private int calculatePoints(boolean firstCheckBoxIsChecked, boolean secondCheckBoxIsChecked, String questionFourAnswered, int selectedIdQ2, int selectedIdQ3, boolean thirdCheckBoxIsChecked, boolean fourthCheckBoxIsChecked) {
        int basePoints = 0;
        int correctIdQ2 = R.id.question_two_answer_c;
        int correctIdQ3 = R.id.question_three_answer_b;

        //On question 1, checkboxes 1 & 2 are correct. If the user gets 1 correct answer, they get 1 point. If they get
        //2 correct answers, they get 2 points. If they get 1 correct answer & 1 incorrect answer, they still get 1 point.
        //For 2 correct & 1 incorrect answer, two points are given. If all boxes are ticked, no points are given.
        if (firstCheckBoxIsChecked && secondCheckBoxIsChecked) {
            if (!thirdCheckBoxIsChecked && !fourthCheckBoxIsChecked) {
                basePoints = basePoints + 2;
            } else {
                return basePoints;
            }
        } else if (secondCheckBoxIsChecked) {
            if (!thirdCheckBoxIsChecked && !fourthCheckBoxIsChecked) {
                basePoints = basePoints + 1;
            } else if (thirdCheckBoxIsChecked) {
                basePoints = basePoints + 1;
            } else if (fourthCheckBoxIsChecked) {
                basePoints = basePoints + 1;
            } else {
                return basePoints;
            }
        } else if (firstCheckBoxIsChecked) {
            if (!thirdCheckBoxIsChecked && !fourthCheckBoxIsChecked) {
                basePoints = basePoints + 1;
            } else if (thirdCheckBoxIsChecked) {
                basePoints = basePoints + 1;
            } else if (fourthCheckBoxIsChecked) {
                return basePoints = basePoints + 1;
            } else {
                return basePoints;
            }
        }

        //If the content input in the EditText field is the same as the correct answer provided, the user gets 1 point
        if (questionFourAnswered.contentEquals(getString(R.string.question_4_answer))) {
            basePoints = basePoints + 1;
        }
        //if the user selects the answer which has got the same id as the correct answer, the user gets one point
        if (selectedIdQ2 == correctIdQ2) {
            basePoints = basePoints + 1;
        }
        if (selectedIdQ3 == correctIdQ3) {
            basePoints = basePoints + 1;
        }
        return basePoints;
    }
}
