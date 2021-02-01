package android.bignerdranch.geoquiz;

/** androidx.appcompat.app.AppCompatActivity
    android.support.v7.app.AppCompatActivity is listed in BNRG
    provides the compatibility support for older versions of Android.
 */

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

/**
 *
 */
public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    // Question Array
    private Question[] mQuestions = new Question[] {
            new Question(R.string.question_africa, false),
            new Question(R.string.question_australia,true),
            new Question(R.string.question_oceans,true),
            new Question(R.string.question_mideast,false),
            new Question(R.string.question_asia, true),
            new Question(R.string.question_americas,true)
    };

    private int mCurrentIndex = 0;

    // Used in multiple sections
    private void updateQuestion(){
        int question = mQuestions[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean UserPressed){
        boolean AnswerIsTrue = mQuestions[mCurrentIndex].isAnswerTrue();
        int MessageResId = 0;

        if (UserPressed)
            MessageResId = R.string.correct_toast;
        else
            MessageResId = R.string.incorrect_toast;

        Toast.makeText(this, MessageResId, Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            Wiring up the TextView
         */
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        updateQuestion();

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(
            /*
                - The argument passed to the listener is an anonymous inner class.
                - The implementation is passed as a whole because it is used only
                in this one place.
                - Because the class implements OnClickListener,
                it must implement the class' sole method.
             */
            new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    //Toast Method
                    checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(
            /*
                - The argument passed to the listener is an anonymous inner class.
                - The implementation is passed as a whole because it is used only
                in this one place.
                - Because the class implements OnClickListener,
                it must implement the class' sole method.
             */
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        //Toast Method
                        checkAnswer(false);
                    }
                });

        /*
            Wiring up the new button
         */
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;
                updateQuestion();
            }
        });
    }



}