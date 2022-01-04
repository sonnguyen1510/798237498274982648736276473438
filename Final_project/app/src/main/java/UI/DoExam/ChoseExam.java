package UI.DoExam;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.room.Database;

import android.content.Context;
import android.content.Intent;
import android.icu.text.ConstrainedFieldPosition;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.final_project.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import Database.Table.Questions;
import Database.Table.SA_photo_question;
import Database.Table.traffic_sign_questions;
import Object.DoExam.QuestionData;
import Database.QuestionDatabase;

public class ChoseExam extends AppCompatActivity {
    private TextView Exam1,Exam2,Exam3,Exam4,Exam5;
    private ConstraintLayout Exam6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_exam);

        Exam1 = findViewById(R.id.Exam1);
        Exam2 = findViewById(R.id.Exam2);
        Exam3 = findViewById(R.id.Exam3);
        Exam4 = findViewById(R.id.Exam4);
        Exam5 = findViewById(R.id.Exam5);
        Exam6 = findViewById(R.id.Exam6);

        //setup ActionBar

        getSupportActionBar().setTitle("Chọn mã đề");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Exam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent DoExam1 = new Intent(view.getContext(),Do_exam.class);
                List<QuestionData> data = createQuestionforExam1(view.getContext());
                DoExam1.putExtra("QuestionList", (Serializable) data);
                DoExam1.putExtra("DoType","DoExam");
                view.getContext().startActivity(DoExam1);
                finish();
            }
        });

        Exam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent DoExam2 = new Intent(view.getContext(),Do_exam.class);
                List<QuestionData> data = createQuestionforExam2(view.getContext());
                DoExam2.putExtra("QuestionList", (Serializable) data);
                DoExam2.putExtra("DoType","DoExam");
                view.getContext().startActivity(DoExam2);
                finish();
            }
        });

        Exam3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent DoExam3 = new Intent(view.getContext(),Do_exam.class);
                List<QuestionData> data = createQuestionforExam3(view.getContext());
                DoExam3.putExtra("QuestionList", (Serializable) data);
                DoExam3.putExtra("DoType","DoExam");
                view.getContext().startActivity(DoExam3);
                finish();
            }
        });

        Exam4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent DoExam4 = new Intent(view.getContext(),Do_exam.class);
                List<QuestionData> data = createQuestionforExam4(view.getContext());
                DoExam4.putExtra("QuestionList", (Serializable) data);
                DoExam4.putExtra("DoType","DoExam");
                view.getContext().startActivity(DoExam4);
                finish();
            }
        });

        Exam5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent DoExam5 = new Intent(view.getContext(),Do_exam.class);
                List<QuestionData> data = createQuestionforExam5(view.getContext());
                DoExam5.putExtra("QuestionList", (Serializable) data);
                DoExam5.putExtra("DoType","DoExam");
                view.getContext().startActivity(DoExam5);
                finish();
            }
        });

        Exam6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent DoExam6 = new Intent(view.getContext(),Do_exam.class);
                List<QuestionData> data = createRandomQuestion(view.getContext());
                DoExam6.putExtra("QuestionList", (Serializable) data);
                DoExam6.putExtra("DoType","DoExam");
                view.getContext().startActivity(DoExam6);
                finish();
            }
        });

    }

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

    static List<QuestionData> createQuestionforExam1(Context context){
        List<QuestionData> data = new ArrayList<>();

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("1"),
        "LyThuyet",1));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("4"),
                "LyThuyet",2));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("12"),
                "LyThuyet",3));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("14"),
                "LyThuyet",4));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("17"),
                "LyThuyet",5));


        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("22"),
                "LyThuyet",6));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("27"),
                "LyThuyet",7));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("34"),
                "LyThuyet",8));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("67"),
                "LyThuyet",9));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("51"),
                "LyThuyet",10));

        //bien bao

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("2"),
                "BienBao",11));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("3"),
                "BienBao",12));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("8"),
                "BienBao",13));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("11"),
                "BienBao",14));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("13"),
                "BienBao",15));

        //sa hinh

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("1"),
                "SaHinh",16));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("3"),
                "SaHinh",17));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("7"),
                "SaHinh",18));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("8"),
                "SaHinh",19));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("11"),
                "SaHinh",20));


        return data;
    }

    static List<QuestionData> createQuestionforExam2(Context context){
        List<QuestionData> data = new ArrayList<>();

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("6"),
                "LyThuyet",1));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("26"),
                "LyThuyet",2));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("33"),
                "LyThuyet",3));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("22"),
                "LyThuyet",4));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("24"),
                "LyThuyet",5));


        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("36"),
                "LyThuyet",6));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("39"),
                "LyThuyet",7));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("42"),
                "LyThuyet",8));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("49"),
                "LyThuyet",9));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("61"),
                "LyThuyet",10));

        //bien bao

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("7"),
                "BienBao",11));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("11"),
                "BienBao",12));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("13"),
                "BienBao",13));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("18"),
                "BienBao",14));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("22"),
                "BienBao",15));

        //sa hinh

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("5"),
                "SaHinh",16));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("7"),
                "SaHinh",17));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("12"),
                "SaHinh",18));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("15"),
                "SaHinh",19));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("21"),
                "SaHinh",20));


        return data;
    }

    static List<QuestionData> createQuestionforExam3(Context context){
        List<QuestionData> data = new ArrayList<>();

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("5"),
                "LyThuyet",1));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("16"),
                "LyThuyet",2));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("23"),
                "LyThuyet",3));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("27"),
                "LyThuyet",4));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("36"),
                "LyThuyet",5));


        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("42"),
                "LyThuyet",6));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("44"),
                "LyThuyet",7));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("51"),
                "LyThuyet",8));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("68"),
                "LyThuyet",9));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("70"),
                "LyThuyet",10));

        //bien bao

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("1"),
                "BienBao",11));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("7"),
                "BienBao",12));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("13"),
                "BienBao",13));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("16"),
                "BienBao",14));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("29"),
                "BienBao",15));

        //sa hinh

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("9"),
                "SaHinh",16));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("14"),
                "SaHinh",17));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("17"),
                "SaHinh",18));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("21"),
                "SaHinh",19));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("26"),
                "SaHinh",20));


        return data;
    }

    static List<QuestionData> createQuestionforExam4(Context context){
        List<QuestionData> data = new ArrayList<>();

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("16"),
                "LyThuyet",1));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("21"),
                "LyThuyet",2));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("26"),
                "LyThuyet",3));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("29"),
                "LyThuyet",4));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("32"),
                "LyThuyet",5));


        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("33"),
                "LyThuyet",6));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("38"),
                "LyThuyet",7));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("41"),
                "LyThuyet",8));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("59"),
                "LyThuyet",9));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("81"),
                "LyThuyet",10));

        //bien bao

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("4"),
                "BienBao",11));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("7"),
                "BienBao",12));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("17"),
                "BienBao",13));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("26"),
                "BienBao",14));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("33"),
                "BienBao",15));

        //sa hinh

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("8"),
                "SaHinh",16));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("15"),
                "SaHinh",17));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("19"),
                "SaHinh",18));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("25"),
                "SaHinh",19));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("32"),
                "SaHinh",20));


        return data;
    }

    static List<QuestionData> createQuestionforExam5(Context context){
        List<QuestionData> data = new ArrayList<>();

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("14"),
                "LyThuyet",1));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("25"),
                "LyThuyet",2));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("27"),
                "LyThuyet",3));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("33"),
                "LyThuyet",4));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("39"),
                "LyThuyet",5));


        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("58"),
                "LyThuyet",6));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("69"),
                "LyThuyet",7));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("74"),
                "LyThuyet",8));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("77"),
                "LyThuyet",9));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID("87"),
                "LyThuyet",10));

        //bien bao

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("4"),
                "BienBao",11));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("12"),
                "BienBao",12));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("18"),
                "BienBao",13));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("28"),
                "BienBao",14));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id("33"),
                "BienBao",15));

        //sa hinh

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("7"),
                "SaHinh",16));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("10"),
                "SaHinh",17));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("20"),
                "SaHinh",18));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("30"),
                "SaHinh",19));

        data.add(new QuestionData(
                QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id("34"),
                "SaHinh",20));


        return data;
    }

    static List<QuestionData> createRandomQuestion(Context context){
        List<QuestionData> data = new ArrayList<>();

        List<Questions> TheoryQuestion = QuestionDatabase.getInstance(context).question_query().getAllQuestion();
        List<traffic_sign_questions> traffic_sign_questions = QuestionDatabase.getInstance(context).traffic_sign_questions_query().getAllQuestion();
        List<SA_photo_question> SA_photo_questions = QuestionDatabase.getInstance(context).sa_photo_question_query().getAllQuestion();

        int[] TheoryQuestionRD = RandomInt(10,1,TheoryQuestion.size()+1);
        int[] traffic_sign_QuestionRD = RandomInt(5,1,traffic_sign_questions.size()+1);
        int[] SA_photo_RD = RandomInt(5,1,SA_photo_questions.size()+1);


        int Tcount = 1;
        for (int IDtemp : TheoryQuestionRD){
            data.add(new QuestionData(
                    QuestionDatabase.getInstance(context).question_query().getQuestions_by_ID(String.valueOf(IDtemp)),
                    "Lythuyet",Tcount));
            Tcount++;
        }


        for (int IDtemp : traffic_sign_QuestionRD){
            data.add(new QuestionData(
                    QuestionDatabase.getInstance(context).traffic_sign_questions_query().getQuestion_by_id(String.valueOf(IDtemp)),
                    "BienBao",Tcount));
            Tcount++;
        }


        for (int IDtemp : SA_photo_RD){
            data.add(new QuestionData(
                    QuestionDatabase.getInstance(context).sa_photo_question_query().getQuestion_by_id(String.valueOf(IDtemp)),
                    "SaHinh",Tcount));
            Tcount++;
        }



        return data;
    }

    private static int[] RandomInt(int size,int min , int max) {
        int Arr[] = new int[size];
        Arr[0] = ThreadLocalRandom.current().nextInt(min, max + 1);
        for(int i = 1 ; i < size ; i++) {
            int element = 0;
            for(; ;) {
                int rnd = ThreadLocalRandom.current().nextInt(min, max + 1);
                if(check(Arr,rnd)) {
                    element = rnd;
                    break;
                }

            }
            Arr[i] = element;
        }
        return Arr;


    }

    static boolean check(int[]arr , int x) {
        int count=0;
        for (int a : arr) {
            if(a == x)
                count++;
        }

        if(count == 0) return true;
        else return false;
    }
}