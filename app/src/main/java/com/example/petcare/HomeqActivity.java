package com.example.petcare;

import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class HomeqActivity extends AppCompatActivity {
    private TextView questionText;
    private RadioGroup radioGroup;
    private RadioButton answer1;
    private RadioButton answer2;
    private RadioButton answer3;
    private Button nextButton;
    private TextView correctAnswersText;
    private Button finishButton;
    private ImageView questionImage;


    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;

    // Definišite niz sa pitanjima
    private String[] questions = {
            "Koji glodar je često biran kao kućni ljubimac zbog svoje sposobnosti da brzo uči trikove?",
            " Koji papagaj je poznat po svojoj sposobnosti da imitira ljudski govor? ",
            "Koja riba je poznata po svom šarenom peraju i često se drži u akvarijumima? ",
            "Kako se naziva proces u kojem kućni ljubimci mijenjaju svoje krzno tokom promjene godišnjih doba?",
            "Koji insekt je često držan kao kućni ljubimac u staklenim terarijumima? ",
            "Koji pas ima reputaciju da je najmanji pas na svijetu? ",
            "Koja vrsta zmije je često odabrana kao kućni ljubimac zbog svoje mirne prirode? ",
            "Koja je najpopularnija vrsta kućnog ljubimca na svijetu, po broju populacije? ",
            "Koja životinja je često poznata kao \"čovjekov najbolji prijatelj\"?",
            "Koja hrana je najprikladnija za ishranu štenaca?"
    };

    // Definišite dvostruki niz sa odgovorima, gde za svako pitanje imate tri odgovora
    private String[][] answers = {
            {"Kunić ", "Kineski hrčak ", "Morsko prase"},
            {"Kanareski kanarinci ", "Zlatni retriveri ", "Afrički sivi papagaj"},
            {"Zlatna ribica ", "Som", "Haringa"},
            {"Mimikrija", "Mutacija", "Linjanje"},
            {"Pčela", "Skakavac", "Tarantula"},
            {"Doberman", "Čivava", "Labrador retriever"},
            {"Kobra", "Piton", "Žuta anaconda"},
            {"Mačka", "Pas", "Ptica"},
            {"Pas", "Papagaj", "Riba"},
            {"Mlijeko","Sirova hrana","Hrana za štence"}
    };

    // Definišite niz sa tačnim indeksima odgovora za svako pitanje
    private int[] correctAnswerIndexes = {1, 2, 0, 2, 2, 1, 1, 1, 0, 2  };

    private int[] questionImageIds = {
            R.drawable.slika1,
            R.drawable.slika2,
            R.drawable.slika3,
            R.drawable.slika4,
            R.drawable.slika5,
            R.drawable.slika6,
            R.drawable.slika7,
            R.drawable.slika8,
            R.drawable.slika9,
            R.drawable.slika10,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homeq_activity);

        questionText = findViewById(R.id.questionText);
        radioGroup = findViewById(R.id.radioGroup);
        answer1 = findViewById(R.id.answer1);
        answer2 = findViewById(R.id.answer2);
        answer3 = findViewById(R.id.answer3);
        nextButton = findViewById(R.id.nextButton);
        correctAnswersText = findViewById(R.id.correctAnswersText);
        finishButton = findViewById(R.id.finishButton);

        showQuestion(currentQuestionIndex);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(HomeqActivity.this, "Morate izabrati odgovor.", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton selectedAnswer = findViewById(selectedId);
                    checkAnswer(selectedAnswer);
                }
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeqActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void showQuestion(int index) {
        questionText.setText(questions[index]);
        answer1.setText(answers[index][0]);
        answer2.setText(answers[index][1]);
        answer3.setText(answers[index][2]);
        ImageView imageView = findViewById(R.id.questionImage);
        imageView.setImageDrawable(getResources().getDrawable(questionImageIds[index]));



        radioGroup.clearCheck();
    }

    private void checkAnswer(RadioButton selectedAnswer) {
        int index = radioGroup.indexOfChild(selectedAnswer);
        int correctAnswerIndex = correctAnswerIndexes[currentQuestionIndex];

        if (index == correctAnswerIndex) {
            correctAnswers++;
        }

        if (currentQuestionIndex < questions.length - 1) {
            currentQuestionIndex++;
            showQuestion(currentQuestionIndex);
        } else {
            showResults();
        }
    }

    private void showResults() {
        String resultMessage = "Broj tačnih odgovora: " + correctAnswers + " od " + questions.length;
        correctAnswersText.setText(resultMessage);
        correctAnswersText.setVisibility(View.VISIBLE);
        finishButton.setVisibility(View.VISIBLE);
        answer1.setEnabled(false);
        answer2.setEnabled(false);
        answer3.setEnabled(false);
        nextButton.setEnabled(false);
    }
}