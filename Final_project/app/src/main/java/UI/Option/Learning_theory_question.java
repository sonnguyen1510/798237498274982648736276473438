package UI.Option;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.final_project.R;

import java.util.List;

import Database.QuestionDatabase;
import Database.Table.Questions;

public class Learning_theory_question extends AppCompatActivity {
    private List<Questions> data;
    private Button Previous;
    private Button Next;
    //textview
    //cauhoi
    private TextView Question_Name;
    //answer
    private CheckBox AnswerA;
    private CheckBox AnswerB;
    private CheckBox AnswerC;
    private CheckBox AnswerD;
    //position
    private int position = 0;

    //go to
    private Button Goto;
    private TextView ErrorCheck;
    private EditText GotoQuestionNumber;
    private TextView GoToTV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_theory);
        //set view data
        Previous = findViewById(R.id.Learning_theory_previous_question);
        Next = findViewById(R.id.Learning_theory_Next_question);
        Question_Name = findViewById(R.id.Theory_Question_Name);

        GoToTV = findViewById(R.id.Theory_question_goto);
        GotoQuestionNumber = findViewById(R.id.Theory_question_InputQuestionNumber);
        ErrorCheck = findViewById(R.id.TheoryQuestion_ErrorCheck);
        Goto = findViewById(R.id.Theory_question_gotoButton);

        AnswerA = findViewById(R.id.Learning_theory_Answer_A);
        AnswerB = findViewById(R.id.Learning_theory_Answer_B);
        AnswerC = findViewById(R.id.Learning_theory_Answer_C);
        AnswerD = findViewById(R.id.Learning_theory_Answer_D);

        AnswerA.setEnabled(false);
        AnswerB.setEnabled(false);
        AnswerC.setEnabled(false);
        AnswerD.setEnabled(false);
        //action bar
        getSupportActionBar().setTitle("Học Lý Thuyết");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = QuestionDatabase.getInstance(this).question_query().getAllQuestion();
        Questions question1 = data.get(0);
        SetQuestionData(question1,position,this);
        //Set first data

        Previous.setEnabled(false);

        Previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ItemLocation;
                if(position == 1){
                    Previous.setEnabled(false);
                    position--;
                    ItemLocation = position;
                }
                else{
                    position--;
                    ItemLocation = position;
                    Next.setEnabled(true);
                }
                SetQuestionData(data.get(ItemLocation),position,view.getContext());
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ItemLocation;
                if(position == data.size()-2){
                    Next.setEnabled(false);
                    position++;
                    ItemLocation = position;
                }
                else{
                    position++;
                    ItemLocation = position;
                    Previous.setEnabled(true);
                }

                SetQuestionData(data.get(ItemLocation),position,view.getContext());
            }
        });

        Goto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GotoQuestionNumber.getText().toString().length() ==0){
                    ErrorCheck.setText("Vui lòng nhập câu bạn muốn xem");
                    ErrorCheck.setTextColor(Color.parseColor("#ff0000"));
                    GoToTV.setTextColor(Color.parseColor("#ff0000"));
                    return;
                }

                int QuestionNum = Integer.parseInt(GotoQuestionNumber.getText().toString()) - 1;
                if(QuestionNum <0 || QuestionNum > data.size()-1){
                    ErrorCheck.setText("Câu của bạn nhập không tồn tại");
                    ErrorCheck.setTextColor(Color.parseColor("#ff0000"));
                    GoToTV.setTextColor(Color.parseColor("#ff0000"));

                }
                else if(QuestionNum == 0 || QuestionNum == data.size()-1){
                    if(QuestionNum == 0){
                        Previous.setEnabled(false);
                        Next.setEnabled(true);
                    }
                    else{
                        Next.setEnabled(false);
                        Previous.setEnabled(true);
                    }


                    ErrorCheck.setText("");
                    ErrorCheck.setTextColor(Color.parseColor("#ffffff"));
                    GoToTV.setTextColor(Color.parseColor("#000000"));
                    position = QuestionNum;
                    GotoQuestionNumber.setText("");
                    SetQuestionData(data.get(position),position, view.getContext());
                }
                else{
                    Previous.setEnabled(true);
                    Next.setEnabled(true);
                    ErrorCheck.setText("");
                    ErrorCheck.setTextColor(Color.parseColor("#ffffff"));
                    GoToTV.setTextColor(Color.parseColor("#000000"));
                    position = QuestionNum;
                    GotoQuestionNumber.setText("");
                    SetQuestionData(data.get(position),position, view.getContext());
                }

            }
        });




    }
    //---------------------ACTION BAR-------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void SetQuestionData(Questions questions ,int position , Context context){
        Question_Name.setText("Câu "+(position+1)+": "+questions.getQuestionName());
        //Question A--------------------------------------
        if (!questions.getAnswerA().equalsIgnoreCase("null")){
            AnswerA.setVisibility(View.VISIBLE);
            AnswerA.setText(questions.getAnswerA());
        }
        else
            AnswerA.setVisibility(View.GONE);

        //Question B--------------------------------------
        if (!questions.getAnswerB().equalsIgnoreCase("null")){
            AnswerB.setVisibility(View.VISIBLE);
            AnswerB.setText(questions.getAnswerB());
        }
        else
            AnswerB.setVisibility(View.GONE);

        //Question C--------------------------------------

        if (!questions.getAnswerC().equalsIgnoreCase("null")){
            AnswerC.setVisibility(View.VISIBLE);
            AnswerC.setText(questions.getAnswerC());
        }
        else
            AnswerC.setVisibility(View.GONE);

        //Question D--------------------------------------
        if (!questions.getAnswerD().equalsIgnoreCase("null")){
            AnswerD.setVisibility(View.VISIBLE);
            AnswerD.setText(questions.getAnswerD());
        }
        else
            AnswerD.setVisibility(View.GONE);


        //set answer

        int AnswerKey = QuestionDatabase.getInstance(context)
                .questionAnswer_query()
                .getAnswerKey(questions.getQuestionID(),questions.getQuestionType());

        if(AnswerKey == 1){//question A true
            //true answer
            AnswerA.setBackgroundColor(Color.parseColor("#99FF99"));
            AnswerA.setTextColor(Color.parseColor("#339900"));
            //False answer
            AnswerB.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerB.setTextColor(Color.parseColor("#000000"));

            AnswerC.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerC.setTextColor(Color.parseColor("#000000"));

            AnswerD.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerD.setTextColor(Color.parseColor("#000000"));

        }
        else if (AnswerKey == 2){//question B true
            //true answer
            AnswerB.setBackgroundColor(Color.parseColor("#99FF99"));
            AnswerB.setTextColor(Color.parseColor("#339900"));
            //False answer
            AnswerA.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerA.setTextColor(Color.parseColor("#000000"));

            AnswerC.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerC.setTextColor(Color.parseColor("#000000"));

            AnswerD.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerD.setTextColor(Color.parseColor("#000000"));
        }
        else if(AnswerKey == 3){//question C true
            //true answer
            AnswerC.setBackgroundColor(Color.parseColor("#99FF99"));
            AnswerC.setTextColor(Color.parseColor("#339900"));
            //False answer
            AnswerB.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerB.setTextColor(Color.parseColor("#000000"));

            AnswerA.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerA.setTextColor(Color.parseColor("#000000"));

            AnswerD.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerD.setTextColor(Color.parseColor("#000000"));
        }
        else{//question D true
            //true answer
            AnswerD.setBackgroundColor(Color.parseColor("#99FF99"));
            AnswerD.setTextColor(Color.parseColor("#339900"));
            //False answer
            AnswerB.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerB.setTextColor(Color.parseColor("#000000"));

            AnswerC.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerC.setTextColor(Color.parseColor("#000000"));

            AnswerA.setBackgroundColor(Color.parseColor("#FFFFFF"));
            AnswerA.setTextColor(Color.parseColor("#000000"));
        }
    }
}