package UI.Option;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.final_project.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Database.QuestionDatabase;
import Database.Table.Questions;
import Database.Table.SA_photo_question;
import Database.Table.traffic_sign_questions;
import Object.DoExam.QuestionData;
import Object.Main.Learning_Base_On_QuestionType_OBJ;
import UI.DoExam.Do_exam;

public class LearningOrDoexam_base_on_questionType extends AppCompatActivity {
    public TextView LearningName;
    public LinearLayout LearningNameLayout;
    public TextView DoExamName;
    public LinearLayout DoExamNameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning_or_doexam_base_on_question_type);
        LearningName = findViewById(R.id.Ln_Or_DExam_Learning);
        DoExamName = findViewById(R.id.Ln_Or_DExam_DoExam);
        LearningNameLayout = findViewById(R.id.Ln_Or_DExam_Learning_layout);
        DoExamNameLayout = findViewById(R.id.Ln_Or_DExam_DoExam_layout);

        Learning_Base_On_QuestionType_OBJ data = (Learning_Base_On_QuestionType_OBJ)getIntent().getSerializableExtra("Go_to");
        //ActionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Chọn Chủ Đề");
        //
        if(data.go_to == Learning_theory_question.class){
            Set_Goto(this,
                    Learning_theory_question.class,
                    Do_exam.class,
                    "Học Lý Thuyết",
                    "Làm Lý Thuyết",
                    LoadQuestionData("Lythuyet",this));
        }
        else if(data.go_to == Learning_traffic_signs_question.class ){
            Set_Goto(this,
                    Learning_traffic_signs_question.class,
                    Do_exam.class,
                    "Học Biến Báo",
                    "Làm Biển Báo",
                    LoadQuestionData("BienBao",this));
        }
        else{
            Set_Goto(this,
                    Learning_SA_photo_question.class,
                    Do_exam.class,
                    "Học Sa Hình",
                    "Làm Sa Hình",
                    LoadQuestionData("SaHinh",this));
        }
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

    private List<QuestionData> LoadQuestionData(String QuestionType , Context context) {
        List<QuestionData> data = new ArrayList<>();
        if(QuestionType.equalsIgnoreCase("Lythuyet")){
            List<Questions>  Questiondata = QuestionDatabase.getInstance(context).question_query().getAllQuestion();
            int position = 0;
            for(Questions temp : Questiondata){
                data.add(new QuestionData(temp,"Lythuyet",position));
                position++;
            }
        }
        else if(QuestionType.equalsIgnoreCase("BienBao")){
            List<traffic_sign_questions>  Questiondata = QuestionDatabase.getInstance(context).traffic_sign_questions_query().getAllQuestion();
            int position = 0;
            for(traffic_sign_questions temp : Questiondata){
                data.add(new QuestionData(temp,"BienBao",position));
                position++;
            }
        }
        else{
            List<SA_photo_question>  Questiondata = QuestionDatabase.getInstance(context).sa_photo_question_query().getAllQuestion();
            int position = 0;
            for(SA_photo_question  temp : Questiondata){
                data.add(new QuestionData(temp,"SaHinh",position));
                position++;
            }
        }

        return data;
    }

    public void Set_Goto(Context context , Class Learning , Class DoExam, String Learningtext, String DoExamtext, List<QuestionData> questionData){
        LearningName.setText(Learningtext);
        LearningNameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),Learning);
                view.getContext().startActivity(intent);
            }
        });

        DoExamName.setText(DoExamtext);
        DoExamNameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),DoExam);
                intent.putExtra("QuestionList", (Serializable) questionData);
                intent.putExtra("DoType","Learning");
                view.getContext().startActivity(intent);
            }
        });
    }
}