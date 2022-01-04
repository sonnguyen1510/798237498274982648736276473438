package UI.Option;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.final_project.R;

import java.util.ArrayList;
import java.util.List;

import Adapter.DoQuestion.LoadQuestionNameAdapter;
import Database.QuestionDatabase;
import Database.Table.FalseAnswer;
import Database.Table.Questions;
import Database.Table.SA_photo_question;
import Database.Table.traffic_sign_questions;
import Object.DoExam.QuestionData;

public class WrongAnswer extends AppCompatActivity {
    private List<FalseAnswer> data;
    private Button Previous;
    private Button Next;
    //textview
    //cauhoi
    LoadQuestionNameAdapter adapter;
    RecyclerView LoadQuestionName ;

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
        setContentView(R.layout.activity_wrong_answer);
        Previous = findViewById(R.id.WrongAnswer_previous_question);
        Next = findViewById(R.id.WrongAnswer_Next_question);

        LoadQuestionName = findViewById(R.id.WrongAnswer_LoadQuestionName);
        data = LoadData(this);

        GoToTV = findViewById(R.id.WrongAnswer_goto);
        GotoQuestionNumber = findViewById(R.id.WrongAnswer_InputQuestionNumber);
        ErrorCheck = findViewById(R.id.WrongAnswer_ErrorCheck);
        Goto = findViewById(R.id.WrongAnswer_gotoButton);

        AnswerA = findViewById(R.id.WrongAnswer_Answer_A);
        AnswerB = findViewById(R.id.WrongAnswer_Answer_B);
        AnswerC = findViewById(R.id.WrongAnswer_Answer_C);
        AnswerD = findViewById(R.id.WrongAnswer_Answer_D);

        AnswerA.setEnabled(false);
        AnswerB.setEnabled(false);
        AnswerC.setEnabled(false);
        AnswerD.setEnabled(false);

        //Action Bar
        getSupportActionBar().setTitle("Những Câu Hay Sai");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Previous.setEnabled(false);
        if(data.size() == 1)
            Next.setEnabled(false);
        SetQuestionData(data.get(position),position,this);

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
                    ErrorCheck.setText("Vui lòng nhập câu bạn muốn xem ");
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
                    //SetQuestionData(data.get(position),position, view.getContext());
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
    //------------------------------------------------------
    private List<FalseAnswer> LoadData(Context context){
        List<FalseAnswer> data = new ArrayList<>();
        for(FalseAnswer temp : QuestionDatabase.getInstance(context).falseAnswer_query().getFalseAnswerbyQuestionType("Lythuyet")){
            data.add(temp);
        }

        for(FalseAnswer temp : QuestionDatabase.getInstance(context).falseAnswer_query().getFalseAnswerbyQuestionType("BienBao")){
            data.add(temp);
        }

        for(FalseAnswer temp : QuestionDatabase.getInstance(context).falseAnswer_query().getFalseAnswerbyQuestionType("SaHinh")){
            data.add(temp);
        }

        return data;
    }

    private void SetQuestionData(FalseAnswer falseAnswer, int position, Context context) {
        AnswerA.setEnabled(false);
        AnswerB.setEnabled(false);
        AnswerC.setEnabled(false);
        AnswerD.setEnabled(false);

        //--------------------------------QUESTION NAME-----------------------------------
        List<QuestionData> questionData = new ArrayList<>();
        if(falseAnswer.getQuestionType().equalsIgnoreCase("LyThuyet")){
            Questions questions = QuestionDatabase.getInstance(context)
                    .question_query().getQuestions_by_ID(falseAnswer.getQuestionID());
            QuestionData dataTemp = new QuestionData(
                    questions,
                    falseAnswer.getQuestionType(),
                    position
            );

            if(!questions.getAnswerA().equalsIgnoreCase("null")){
                AnswerA.setVisibility(View.VISIBLE);
                AnswerA.setText(questions.getAnswerA());
            }
            else
                AnswerA.setVisibility(View.GONE);

            if(!questions.getAnswerB().equalsIgnoreCase("null")){
                AnswerB.setVisibility(View.VISIBLE);
                AnswerB.setText(questions.getAnswerB());
            }
            else
                AnswerB.setVisibility(View.GONE);

            if(!questions.getAnswerC().equalsIgnoreCase("null")){
                AnswerC.setVisibility(View.VISIBLE);
                AnswerC.setText(questions.getAnswerC());
            }
            else
                AnswerC.setVisibility(View.GONE);

            if(!questions.getAnswerD().equalsIgnoreCase("null")){
                AnswerD.setVisibility(View.VISIBLE);
                AnswerD.setText(questions.getAnswerD());
            }
            else
                AnswerD.setVisibility(View.GONE);


            questionData.add(dataTemp);

        }
        else if(falseAnswer.getQuestionType().equalsIgnoreCase("BienBao")){
            traffic_sign_questions questions = QuestionDatabase.getInstance(context)
                    .traffic_sign_questions_query().getQuestion_by_id(falseAnswer.getQuestionID());
            QuestionData dataTemp = new QuestionData(
                    questions,
                    falseAnswer.getQuestionType(),
                    position
            );
            if(!questions.getTraffic_sign_AnswerA().equalsIgnoreCase("null")){
                AnswerA.setVisibility(View.VISIBLE);
                AnswerA.setText(questions.getTraffic_sign_AnswerA());
            }
            else
                AnswerA.setVisibility(View.GONE);

            if(!questions.getTraffic_sign_AnswerB().equalsIgnoreCase("null")){
                AnswerB.setVisibility(View.VISIBLE);
                AnswerB.setText(questions.getTraffic_sign_AnswerB());
            }
            else
                AnswerB.setVisibility(View.GONE);

            if(!questions.getTraffic_sign_AnswerC().equalsIgnoreCase("null")){
                AnswerC.setVisibility(View.VISIBLE);
                AnswerC.setText(questions.getTraffic_sign_AnswerC());
            }
            else
                AnswerC.setVisibility(View.GONE);

            if(!questions.getTraffic_sign_AnswerD().equalsIgnoreCase("null")){
                AnswerD.setVisibility(View.VISIBLE);
                AnswerD.setText(questions.getTraffic_sign_AnswerD());
            }
            else
                AnswerD.setVisibility(View.GONE);
            questionData.add(dataTemp);
        }
        else{
            SA_photo_question questions = QuestionDatabase.getInstance(context)
                    .sa_photo_question_query().getQuestion_by_id(falseAnswer.getQuestionID());
            QuestionData dataTemp = new QuestionData(
                    questions,
                    falseAnswer.getQuestionType(),
                    position
            );
            if(!questions.getAnswerA().equalsIgnoreCase("null")){
                AnswerA.setVisibility(View.VISIBLE);
                AnswerA.setText(questions.getAnswerA());
            }
            else
                AnswerA.setVisibility(View.GONE);

            if(!questions.getAnswerB().equalsIgnoreCase("null")){
                AnswerB.setVisibility(View.VISIBLE);
                AnswerB.setText(questions.getAnswerB());
            }
            else
                AnswerB.setVisibility(View.GONE);

            if(!questions.getAnswerC().equalsIgnoreCase("null")){
                AnswerC.setVisibility(View.VISIBLE);
                AnswerC.setText(questions.getAnswerC());
            }
            else
                AnswerC.setVisibility(View.GONE);

            if(!questions.getAnswerD().equalsIgnoreCase("null")){
                AnswerD.setVisibility(View.VISIBLE);
                AnswerD.setText(questions.getAnswerD());
            }
            else
                AnswerD.setVisibility(View.GONE);
            questionData.add(dataTemp);
        }

        adapter = new LoadQuestionNameAdapter(questionData,context);
        LoadQuestionName.setAdapter(adapter);
        LoadQuestionName.setLayoutManager(new LinearLayoutManager(context));



        //--------------------------------ANSWER NAME-----------------------------------
        AnswerB.setBackgroundColor(Color.parseColor("#ffffff"));
        AnswerB.setTextColor(Color.parseColor("#000000"));
        AnswerC.setBackgroundColor(Color.parseColor("#ffffff"));
        AnswerC.setTextColor(Color.parseColor("#000000"));
        AnswerA.setBackgroundColor(Color.parseColor("#ffffff"));
        AnswerA.setTextColor(Color.parseColor("#000000"));
        AnswerD.setBackgroundColor(Color.parseColor("#ffffff"));
        AnswerD.setTextColor(Color.parseColor("#000000"));


        if(falseAnswer.getAnswerKey() == 1){
            AnswerA.setBackgroundColor(Color.parseColor("#99FF99"));
            //holder.AnswerA.setTextColor(Color.parseColor("#339900"));
            if(falseAnswer.getUserAnswerB() == 1){
                AnswerB.setBackgroundColor(Color.parseColor("#ff0000"));
                AnswerB.setTextColor(Color.parseColor("#000000"));
            }


            if(falseAnswer.getUserAnswerC() == 1){
                AnswerC.setBackgroundColor(Color.parseColor("#ff0000"));
                AnswerC.setTextColor(Color.parseColor("#000000"));
            }


            if(falseAnswer.getUserAnswerD() == 1){
                AnswerD.setBackgroundColor(Color.parseColor("#ff0000"));
                AnswerD.setTextColor(Color.parseColor("#000000"));
            }

        }

        if(falseAnswer.getAnswerKey() == 2){
            AnswerB.setBackgroundColor(Color.parseColor("#99FF99"));
            //holder.AnswerB.setTextColor(Color.parseColor("#339900"));
            if(falseAnswer.getUserAnswerA() == 1){
                AnswerA.setBackgroundColor(Color.parseColor("#ff0000"));
                AnswerA.setTextColor(Color.parseColor("#000000"));
            }


            if(falseAnswer.getUserAnswerC() == 1){
                AnswerC.setBackgroundColor(Color.parseColor("#ff0000"));
                AnswerC.setTextColor(Color.parseColor("#000000"));
            }


            if(falseAnswer.getUserAnswerD() == 1){
                AnswerD.setBackgroundColor(Color.parseColor("#ff0000"));
                AnswerD.setTextColor(Color.parseColor("#000000"));
            }


        }else



        if(falseAnswer.getAnswerKey() == 3){
            AnswerC.setBackgroundColor(Color.parseColor("#99FF99"));
            //holder.AnswerC.setTextColor(Color.parseColor("#339900"));
            if(falseAnswer.getUserAnswerB() == 1){
                AnswerB.setBackgroundColor(Color.parseColor("#ff0000"));
                AnswerB.setTextColor(Color.parseColor("#000000"));
            }



            if(falseAnswer.getUserAnswerA() == 1){
                AnswerA.setBackgroundColor(Color.parseColor("#ff0000"));
                AnswerA.setTextColor(Color.parseColor("#000000"));
            }


            if(falseAnswer.getUserAnswerD() == 1){
                AnswerD.setBackgroundColor(Color.parseColor("#ff0000"));
                AnswerD.setTextColor(Color.parseColor("#000000"));
            }


        }



        if(falseAnswer.getAnswerKey() == 4){
            AnswerD.setBackgroundColor(Color.parseColor("#99FF99"));
            //holder.AnswerD.setTextColor(Color.parseColor("#339900"));
            if(falseAnswer.getUserAnswerB() == 1){
                AnswerB.setBackgroundColor(Color.parseColor("#ff0000"));
                AnswerB.setTextColor(Color.parseColor("#000000"));
            }


            if(falseAnswer.getUserAnswerC() == 1){
                AnswerC.setBackgroundColor(Color.parseColor("#ff0000"));
                AnswerC.setTextColor(Color.parseColor("#000000"));
            }


            if(falseAnswer.getUserAnswerA() == 1){
                AnswerA.setBackgroundColor(Color.parseColor("#ff0000"));
                AnswerA.setTextColor(Color.parseColor("#000000"));
            }


        }

        //---------------------------SET CHECKED------------------------------
        if(falseAnswer.getUserAnswerA()==1)
            AnswerA.setChecked(true);
        else
            AnswerA.setChecked(false);

        if(falseAnswer.getUserAnswerB()==1)
            AnswerB.setChecked(true);
        else
            AnswerB.setChecked(false);

        if(falseAnswer.getUserAnswerC()==1)
            AnswerC.setChecked(true);
        else
            AnswerC.setChecked(false);

        if(falseAnswer.getUserAnswerD()==1)
            AnswerD.setChecked(true);
        else
            AnswerD.setChecked(false);

    }
}