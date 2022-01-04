package UI.Option;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.List;

import Database.QuestionDatabase;
import Database.Table.traffic_sign_questions;


public class Learning_traffic_signs_question extends AppCompatActivity {
    private List<traffic_sign_questions> data;
    private Button Previous;
    private Button Next;
    //textview
    //cauhoi
    private TextView Question_Name;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;

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
        setContentView(R.layout.activity_learning_traffic_signs);

        //set view data
        Previous = findViewById(R.id.Learning_traffic_signs_previous_question);
        Next = findViewById(R.id.Learning_traffic_signs_Next_question);
        Question_Name = findViewById(R.id.Learning_traffic_signs_QuestionName);
        img1 = findViewById(R.id.Learning_traffic_signs_img1);
        img2 = findViewById(R.id.Learning_traffic_signs_img2);
        img3 = findViewById(R.id.Learning_traffic_signs_img3);


        GoToTV = findViewById(R.id.Learning_traffic_signs_goto);
        GotoQuestionNumber = findViewById(R.id.Learning_traffic_signs_InputQuestionNumber);
        ErrorCheck = findViewById(R.id.Learning_traffic_signs_ErrorCheck);
        Goto = findViewById(R.id.Learning_traffic_signs_gotoButton);

        AnswerA = findViewById(R.id.Learning_traffic_signs_Answer_A);
        AnswerB = findViewById(R.id.Learning_traffic_signs_Answer_B);
        AnswerC = findViewById(R.id.Learning_traffic_signs_Answer_C);
        AnswerD = findViewById(R.id.Learning_traffic_signs_Answer_D);

        AnswerA.setEnabled(false);
        AnswerB.setEnabled(false);
        AnswerC.setEnabled(false);
        AnswerD.setEnabled(false);
        //Action bar
        getSupportActionBar().setTitle("Học Biển Báo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = QuestionDatabase.getInstance(this).traffic_sign_questions_query().getAllQuestion();
        traffic_sign_questions question1 = data.get(0);
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

    public void SetQuestionData(traffic_sign_questions questions ,int position , Context context){
        Question_Name.setText("Câu "+(position+1)+": "+questions.getTraffic_sign_QuestionName());

        //traffic signs 1
        if(!questions.getTraffic_sign_id1().equalsIgnoreCase("null")) {
            img1.setVisibility(View.VISIBLE);
            img1.setImageResource(QuestionDatabase.getInstance(this).traffic_signs_query().getTrafficSignlink(questions.getTraffic_sign_id1()));
        }else
        {
            img1.setVisibility(View.GONE);
        }

        //traffic signs 1
        if(!questions.getTraffic_sign_id2().equalsIgnoreCase("null")){
            img2.setVisibility(View.VISIBLE);
            img2.setImageResource(QuestionDatabase.getInstance(this).traffic_signs_query().getTrafficSignlink(questions.getTraffic_sign_id2()));
        }else
        {
            img2.setVisibility(View.GONE);
        }

        //traffic signs 1
        if(!questions.getTraffic_sign_id3().equalsIgnoreCase("null")){
            img3.setVisibility(View.VISIBLE);
            img3.setImageResource(QuestionDatabase.getInstance(this).traffic_signs_query().getTrafficSignlink(questions.getTraffic_sign_id3()));

        }
        else
        {
            img3.setVisibility(View.GONE);
        }


        //Question A--------------------------------------
        if (!questions.getTraffic_sign_AnswerA().equalsIgnoreCase("null")){
            AnswerA.setVisibility(View.VISIBLE);
            AnswerA.setText(questions.getTraffic_sign_AnswerA());
        }
        else
            AnswerA.setVisibility(View.GONE);

        //Question B--------------------------------------
        if (!questions.getTraffic_sign_AnswerB().equalsIgnoreCase("null")){
            AnswerB.setVisibility(View.VISIBLE);
            AnswerB.setText(questions.getTraffic_sign_AnswerB());
        }
        else
            AnswerB.setVisibility(View.GONE);

        //Question C--------------------------------------

        if (!questions.getTraffic_sign_AnswerC().equalsIgnoreCase("null")){
            AnswerC.setVisibility(View.VISIBLE);
            AnswerC.setText(questions.getTraffic_sign_AnswerC());
        }
        else
            AnswerC.setVisibility(View.GONE);

        //Question D--------------------------------------
        if (!questions.getTraffic_sign_AnswerD().equalsIgnoreCase("null")){
            AnswerD.setVisibility(View.VISIBLE);
            AnswerD.setText(questions.getTraffic_sign_AnswerD());
        }
        else
            AnswerD.setVisibility(View.GONE);


        //set answer

        int AnswerKey = QuestionDatabase.getInstance(context)
                .questionAnswer_query()
                .getAnswerKey(questions.getTraffic_sign_QuestionID(),questions.getQuestionType());

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