package com.example.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;

public class IndusQuizActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvTimer,tvResult;
    TextView tvPoints;
    int points;
    CountDownTimer countDownTimer;
    long millisUntilFinished;


    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indus_quiz);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.submit_btn);
        tvResult =findViewById(R.id.tvResult);
        tvTimer = findViewById(R.id.tvTimer);
        tvPoints = findViewById(R.id.tvPoints);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestionsTextView.setText("Total questions : " + totalQuestion);

        startGame();
    }

        private void startGame() {
            millisUntilFinished = 11000;
            tvTimer.setText("" + (millisUntilFinished / 1000) + "s");
            loadNewQuestion();
            countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    tvTimer.setText("" + (millisUntilFinished / 1000) + "s");

                }

                @Override
                public void onFinish() {
                    currentQuestionIndex++;
                    if (currentQuestionIndex >totalQuestion-1){

                        countDownTimer.cancel();
                        ansA.setVisibility(View.GONE);
                        ansB.setVisibility(View.GONE);
                        ansC.setVisibility(View.GONE);
                        ansD.setVisibility(View.GONE);
                        Intent intent = new Intent(IndusQuizActivity.this,GameOver.class);
                        intent.putExtra("points",points);
                        startActivity(intent);


                    }else {
                        startGame();
                    }

                }
            }.start();

        }



    @Override
    public void onClick(View view) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);
        countDownTimer.cancel();


tvResult.setText(QuestionAnswer.correctAnswers[currentQuestionIndex]);
        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])){
                points++;
                tvPoints.setText(this.points + "/" + this.totalQuestion);
                score++;
            }
            currentQuestionIndex++;
          startGame();


        }else{
            //choices button clicked
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);


        }

    }

    void loadNewQuestion(){
        tvResult.setText(null);

        if(currentQuestionIndex >totalQuestion-1 ){
            countDownTimer.cancel();

            finishQuiz();

        }

        try {
            questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
            ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
            ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
            ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
            ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    void finishQuiz() {
        countDownTimer.cancel();
        Intent intent = new Intent(IndusQuizActivity.this,GameOver.class);
        intent.putExtra("points",points);
        startActivity(intent);
        finish();
    }
}
